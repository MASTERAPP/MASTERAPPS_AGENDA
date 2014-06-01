// Activitat per afegir contactes i editar/mostrar la seva informacio

package com.example.masterappsagenda;

import com.example.masterappsagenda.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.content.Intent;

public class AfegirActivity extends Activity {

	Contactes global;
	String iName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_afegir);
		global=((Contactes)getApplicationContext());
		
		// Rebem dades a traves d'un intent
		Intent intent = getIntent();
		iName = intent.getStringExtra("SELECTED_NAME");
		if (iName != null) {
			// Venim d'haver clicat una entrada de la llista
			// Busquem el contacte en la variable global
			
			// Printem els resultats
			((EditText) findViewById(R.id.editTextNom)).setText(iName);
	    	((EditText) findViewById(R.id.editTextDireccio)).setText(global.getDireccio(iName));
	    	((EditText) findViewById(R.id.editTextFix)).setText(global.getFix(iName));
	    	((EditText) findViewById(R.id.editTextMobil)).setText(global.getMobil(iName));
	    	((EditText) findViewById(R.id.editTextEmail)).setText(global.getEmail(iName));
	    	((CheckBox) findViewById(R.id.checkBoxFacebook)).setChecked(global.getFacebook(iName));
	    	
	    	// Radio grup genere
	    	if (global.getGenere(iName).compareTo("Home") == 0) {
	    		((RadioButton) findViewById(R.id.radioButtonHome)).setChecked(true);
	    	}
	    	else { // Dona
	    		((RadioButton) findViewById(R.id.radioButtonDona)).setChecked(true);
	    	}
	    	
	    	// Radio grup tipus
	    	if (global.getTipus(iName).compareTo("Treball") == 0) {
	    		((RadioButton) findViewById(R.id.radioButtonTreball)).setChecked(true);
	    	}
	    	else {
		    	if (global.getTipus(iName).compareTo("Amic") == 0) {
		    		((RadioButton) findViewById(R.id.radioButtonAmic)).setChecked(true);
		    	}
		    	else { // Familia
		    		((RadioButton) findViewById(R.id.radioButtonFamilia)).setChecked(true);
		    	}
	    	}
	    	
	    	// Evitem que es pugui canviar el nom
	    	((EditText) findViewById(R.id.editTextNom)).setEnabled(false);
	    	
	    	// Canviem el text del boto
	    	((Button)findViewById(R.id.buttonAfegir)).setText(R.string.update_entry);
	    	// Situem el focus en el camp de direccio
	    	((EditText) findViewById(R.id.editTextDireccio)).requestFocus();
		}
		
		// Codi del boto afegir contacte
	    Button buttonAfegir = (Button)findViewById(R.id.buttonAfegir);
	    buttonAfegir.setOnClickListener(new OnClickListener(){
		    @Override
		    public void onClick(View arg0) {
		    	// Al fer click al boto
		    	// Llegim les dades del formulari
		    	String nom = ((EditText) findViewById(R.id.editTextNom)).getText().toString();
		    	String direccio = ((EditText) findViewById(R.id.editTextDireccio)).getText().toString();
		    	String fix = ((EditText) findViewById(R.id.editTextFix)).getText().toString();
		    	String mobil = ((EditText) findViewById(R.id.editTextMobil)).getText().toString();
		    	String email = ((EditText) findViewById(R.id.editTextEmail)).getText().toString();
		    	Boolean facebook = ((CheckBox) findViewById(R.id.checkBoxFacebook)).isChecked();
		    	
		    	RadioGroup radioGenereGroup = (RadioGroup) findViewById(R.id.radioGenereGroup);
		    	int selectedId = radioGenereGroup.getCheckedRadioButtonId();
		    	RadioButton radioGenereButton = (RadioButton) findViewById(selectedId);
		    	String genere = (String) radioGenereButton.getText().toString();
		    	
		    	RadioGroup radioTipusGroup = (RadioGroup) findViewById(R.id.radioTipusGroup);
		    	selectedId = radioTipusGroup.getCheckedRadioButtonId();
		    	RadioButton radioTipusButton = (RadioButton) findViewById(selectedId);
		    	String tipus = (String) radioTipusButton.getText().toString();
		    	
		    	// Commit contact data
		    	if (global.existContact(nom) == false || iName != null) {
		    		if (global.addContact(nom, direccio, fix, mobil, email, facebook, genere, tipus)) {
				    	if (iName != null) {
				    		// Updating contact
					    	Toast.makeText(getApplicationContext(), R.string.msg_updated, Toast.LENGTH_LONG).show();
				    	}
				    	else {
				    		// New contact
					    	Toast.makeText(getApplicationContext(), R.string.msg_added, Toast.LENGTH_LONG).show();
				    	}			
				    	finish();
		    		}
		    		else {
		    			Toast.makeText(getApplicationContext(), R.string.msg_incorrect, Toast.LENGTH_LONG).show();
		    		}
		    	}
		    	else {
		    		// New contact and it already exists
		    		Toast.makeText(getApplicationContext(), R.string.msg_exist, Toast.LENGTH_LONG).show();
		    	}
		    }
	    });   
	}
}
