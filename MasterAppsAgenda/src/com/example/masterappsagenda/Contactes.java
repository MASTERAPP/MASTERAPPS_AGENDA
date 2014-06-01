// Classe que guardara en un array els contactes

package com.example.masterappsagenda;

import java.util.ArrayList;
import java.util.Hashtable;

import android.app.Application;

public  class  Contactes extends Application {
    private Hashtable<String,Contacte> data = new Hashtable<String,Contacte>();

    public Boolean existContact(String iNom) {
    	return data.containsKey(iNom);
    }
    
    public Boolean addContact(String nom2, String direccio2, String fix2, String mobil2, String email2, Boolean facebook2, String genere2, String tipus2) {
    	// Si l'entrada ja existeix, la sobreescriura
    	// Si l'entrada conte informacio incorrecta, tornara fals
    	// Si afegeix l'entrada tornara cert
    	Boolean res = false;
    	Contacte con = new Contacte(nom2, direccio2, fix2, mobil2, email2, facebook2, genere2, tipus2);
    	if (con.isValidContact()) {
    		data.remove(nom2);
    		data.put(nom2, con);
    		res = true;
    	}
    	return res;
    }

    public Boolean delContact(String iNom) {
    	Boolean res = data.containsKey(iNom);
    	data.remove(iNom);
    	return res;
    }
    public ArrayList<String> getContactNames() {
    	return new ArrayList<String>(data.keySet());
    }
    
    public String getDireccio(String iNom) {
    	return data.get(iNom).getDireccio();
    }
    public String getFix(String iNom) {
    	return data.get(iNom).getFix();
    }
    public String getMobil(String iNom) {
    	return data.get(iNom).getMobil();
    }
    public String getEmail(String iNom) {
    	return data.get(iNom).getEmail();
    }
    public Boolean getFacebook(String iNom) {
    	return data.get(iNom).getFacebook();
    }
    public String getGenere(String iNom) {
    	return data.get(iNom).getGenere();
    }
    public String getTipus(String iNom) {
    	return data.get(iNom).getTipus();
    }
}