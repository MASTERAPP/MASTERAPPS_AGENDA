// Activitat per afegir contactes i editar/mostrar la seva informacio

package com.example.masterappsagenda;

import com.example.masterappsagenda.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

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
			Contacte c = global.getContacteByName(iName);
			
			// Printem els resultats
			((EditText) findViewById(R.id.editText1)).setText(c.nom);
	    	((EditText) findViewById(R.id.editText2)).setText(c.direccio);
	    	((EditText) findViewById(R.id.editText3)).setText(c.fix);
	    	((EditText) findViewById(R.id.editText4)).setText(c.mobil);
	    	((EditText) findViewById(R.id.editText5)).setText(c.email);
	    	((CheckBox) findViewById(R.id.checkBox1)).setChecked(c.facebook);
	    	
	    	// Radio grup genere
	    	if (c.genere.compareTo("Home") == 0) {
	    		((RadioButton) findViewById(R.id.radioButton1)).setChecked(true);
	    	}
	    	if (c.genere.compareTo("Dona") == 0) {
	    		((RadioButton) findViewById(R.id.radioButton2)).setChecked(true);
	    	}
	    	
	    	// Radio grup tipus
	    	if (c.tipus.compareTo("Treball") == 0) {
	    		((RadioButton) findViewById(R.id.radioButton3)).setChecked(true);
	    	}
	    	if (c.tipus.compareTo("Amic") == 0) {
	    		((RadioButton) findViewById(R.id.radioButton4)).setChecked(true);
	    	}
	    	if (c.tipus.compareTo("Familia") == 0) {
	    		((RadioButton) findViewById(R.id.radioButton5)).setChecked(true);
	    	}
	    	
	    	// Evitem que es pugui canviar el nom
	    	((EditText) findViewById(R.id.editText1)).setEnabled(false);
	    	
	    	// Canviem el text del boto
	    	((Button)findViewById(R.id.button1)).setText(R.string.update_entry);
	    	// Situem el focus en el camp de direccio
	    	((EditText) findViewById(R.id.editText2)).requestFocus();
		}
		
		// Codi del boto afegir contacte
	    Button button1 = (Button)findViewById(R.id.button1);
	    button1.setOnClickListener(new OnClickListener(){
		    @Override
		    public void onClick(View arg0) {
		    	// Al fer click al boto
		    	// Llegim les dades del formulari
		    	String nom = ((EditText) findViewById(R.id.editText1)).getText().toString();
		    	String dir = ((EditText) findViewById(R.id.editText2)).getText().toString();
		    	String fix = ((EditText) findViewById(R.id.editText3)).getText().toString();
		    	String mobil = ((EditText) findViewById(R.id.editText4)).getText().toString();
		    	String email = ((EditText) findViewById(R.id.editText5)).getText().toString();
		    	Boolean facebook = ((CheckBox) findViewById(R.id.checkBox1)).isChecked();
		    	
		    	RadioGroup radioGenereGroup = (RadioGroup) findViewById(R.id.radioGenereGroup);
		    	int selectedId = radioGenereGroup.getCheckedRadioButtonId();
		    	RadioButton radioGenereButton = (RadioButton) findViewById(selectedId);
		    	String genere = (String) radioGenereButton.getText().toString();
		    	
		    	RadioGroup radioTipusGroup = (RadioGroup) findViewById(R.id.radioTipusGroup);
		    	selectedId = radioTipusGroup.getCheckedRadioButtonId();
		    	RadioButton radioTipusButton = (RadioButton) findViewById(selectedId);
		    	String tipus = (String) radioTipusButton.getText().toString();
		    	
		    	// Intentem actualitzar les dades en la variable global
		    	if (global.isValidContact(new Contacte(nom,dir,fix,mobil,email,facebook,genere,tipus)) == true) {
		    		if (global.existContactByName(nom) == false || (iName != null && nom.compareTo(iName) == 0)) {
				    	if (iName != null) {
				    		// Estem actualitzant un usuari
					    	global.addContact(new Contacte(nom,dir,fix,mobil,email,facebook,genere,tipus));
					    	Toast.makeText(getApplicationContext(), "Entrada actualitzada", Toast.LENGTH_LONG).show();
					    	finish();
				    	}
				    	else {
				    		// Estem afegint un nou usuari
					    	global.addContact(new Contacte(nom,dir,fix,mobil,email,facebook,genere,tipus));
					    	Toast.makeText(getApplicationContext(), "Entrada afegida", Toast.LENGTH_LONG).show();
				    		finish();
				    	}
		    		}
		    		else {
		    			Toast.makeText(getApplicationContext(), "El nom ja existeix", Toast.LENGTH_LONG).show();
		    		}
		    	}
		    	else {
		    		Toast.makeText(getApplicationContext(), "Algun camp conte dades incorrectes", Toast.LENGTH_LONG).show();
		    	}
	    }});   
	}
}
