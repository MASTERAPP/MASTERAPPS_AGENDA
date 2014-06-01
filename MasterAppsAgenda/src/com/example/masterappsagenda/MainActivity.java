/*
 * Activitat per llistar els contactes.
 * Funcionalitats:
 *   Pulsacio mantenida damunt d'un contacte: editar o esborrar contacte.
 *   Boto menu: afegir contacte.
 * La informacio no es guarda de forma persistent.
 */

package com.example.masterappsagenda;

import java.util.ArrayList;

import com.example.masterappsagenda.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	ListView listView;
	Contactes global;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);
        
        // Activem el context menu de la llista
        registerForContextMenu(listView);
        
        // Carguem els contactes globals
        global=((Contactes)getApplicationContext());
        // Afegim contactes inicials
        global.addContact("Joan","C/Av Lleida 22","973251010","688888888","1@correu.com",true,"Home","Amic");
        global.addContact("Alexia","C/Av Lleida 25","973252020","611111111","2@correu.com",true,"Dona","Familia");
        global.addContact("Marc","C/Av Lleida 40","973253030","622222222","3@correu.com",false,"Home","Treball");
        
        // Array dels elemets de la llista que es mostraran
        ArrayList<String> ar = global.getContactNames();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
          android.R.layout.simple_list_item_1, android.R.id.text1, ar);

        // Es mostra la vista de la llista
        // Assign adapter to ListView
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new OnItemClickListener() {
           @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                    long id) {
                Intent intent = new Intent(MainActivity.this, AfegirActivity.class);
                String entradaLV = listView.getItemAtPosition(position).toString();
                intent.putExtra("SELECTED_NAME", entradaLV);
                startActivity(intent);
            }
        });
	}
	
	private void refrescaLlistaContactes() {
		// Refresca el listView
        ArrayList<String> ar = global.getContactNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, ar);
        listView.setAdapter(adapter); 
	}
	
	// Quan tornem d'alguna altra activitat
	@Override
	protected void onResume() {
	    super.onResume();

	    refrescaLlistaContactes();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// Es crea el menu del boto menu
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Botons del menu principal
	    if (item.getItemId() == R.id.add_contact) {
            Intent myIntent = new Intent( listView.getContext(), AfegirActivity.class);
            startActivityForResult(myIntent, 0);
            return true;
	    }
	    else {
            return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
	                                ContextMenuInfo menuInfo)
	{
		// Creacio del menu contextual
	    super.onCreateContextMenu(menu, v, menuInfo);
	 
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.ctx_la, menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item.getMenuInfo();
		String entradaLV;
		// Botons del menu contextual del listView
	    switch (item.getItemId()) {
	        case R.id.ctxEdit:
	        	// Opcio editar entrada
	        	Intent intent = new Intent(MainActivity.this, AfegirActivity.class);
	        	entradaLV = listView.getItemAtPosition(menuInfo.position).toString();
                intent.putExtra("SELECTED_NAME", entradaLV);
                startActivity(intent);
	            return true;
	        case R.id.ctxDel:
	        	// Opcio esborrar entrada
	        	entradaLV = listView.getItemAtPosition(menuInfo.position).toString();
	        	global.delContact(entradaLV);
	        	Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.entry)+" "+entradaLV+" "+getApplicationContext().getString(R.string.deleted), Toast.LENGTH_LONG).show();
	            refrescaLlistaContactes();
	            return true;
	        default:
	            return super.onContextItemSelected(item);
	    }
	}
	
	@Override
    public void onBackPressed() {
    	// Mostrem un dialeg de sortida al apretar el boto fisic Back
		new AlertDialog.Builder(this)
        .setIcon(android.R.drawable.ic_dialog_alert)
        .setTitle(R.string.quit)
        .setMessage(R.string.really_quit)
        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            	// L'usuari ha premut l'opcio Si
                // Stop the activity
                MainActivity.this.finish();
            }

        })
        .setNegativeButton(R.string.no, null)
        .show();
    }
}
