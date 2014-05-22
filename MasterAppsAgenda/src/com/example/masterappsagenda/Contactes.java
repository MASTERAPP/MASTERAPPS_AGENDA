// Classe que guardara en un array els contactes

package com.example.masterappsagenda;

import java.util.ArrayList;

import android.app.Application;

public  class  Contactes extends Application {
    private ArrayList<Contacte> cs = new ArrayList<Contacte>();

    public ArrayList<Contacte> getContacts() {
    	return cs;
    }
    
    private static boolean isNumeric(String str)
    {
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
    private boolean isValidEmailAddress(String email) {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
    
    public boolean isValidContact(Contacte c) {
		if (c.nom.compareTo("") != 0 && ((isNumeric(c.fix) == true) ||
				(c.fix.compareTo("") == 0)) &&
				((isNumeric(c.mobil) == true) || (c.mobil.compareTo("") == 0)) &&
				((isValidEmailAddress(c.email) == true) || (c.email.compareTo("") == 0))) {
			return true;
		}
		else {
			return false;
		}
	}
    
    public boolean existContactByName(String iNom) {
		boolean trobat = false;
		
    	// Mirem si el nom esta a l'array actual
    	for (int i = 0; i < cs.size() && !trobat; i++) {
			if (cs.get(i).nom.compareTo(iNom) == 0) {
				trobat = true;
			}
		}
    	return trobat;
    }
    
    public Boolean addContact(Contacte c) {
    	// Si l'entrada ja existeix, la sobreescriura
    	// Si l'entrada conte informacio incorrecta, tornara fals
    	// Si afegeix l'entrada tornara cert
    	Boolean trobat = false;
    	Boolean res = false;
    	ArrayList<Contacte> ar = new ArrayList<Contacte>();
    	
    	// Comprovacio dels camp
    	if (isValidContact(c)) {
    		// Mirem si el nom esta a l'array actual
	    	for (int i = 0; i < cs.size(); i++) {
				if (cs.get(i).nom.compareTo(c.nom) == 0) {
					trobat = true;
					ar.add(c);
				}
				else {
					ar.add(cs.get(i));
				}
			}
	    	if (!trobat) {
	    		ar.add(c);
	    	}
	    	this.cs = ar;
	    	res = true;
    	}
    	return res;
    }

    public Boolean delContactByName(String iNom) {
    	Boolean trobat = false;
    	Boolean res = false;
    	ArrayList<Contacte> ar = new ArrayList<Contacte>();
    	// Comprovacio dels camp
    	if (iNom.compareTo("") != 0) {
    		// Mirem si el nom esta a l'array actual
	    	for (int i = 0; i < cs.size(); i++) {
				if (cs.get(i).nom.compareTo(iNom) == 0) {
					trobat = true;
				}
				else {
					ar.add(cs.get(i));
				}
			}
	    	this.cs = ar;
	    	res = trobat;
    	}
    	return res;
    }
    
    public Contacte getContacteByName(String iNom) {
		Boolean trobat = false;
		Contacte res = new Contacte();
		// Mirem si el nom esta a l'array actual
    	for (int i = 0; i < cs.size() && !trobat; i++) {
			if (cs.get(i).nom.compareTo(iNom) == 0) {
				trobat = true;
				res = cs.get(i);
			}
		}
    	return res;
    }
    
    public ArrayList<String> getContactNames() {
    	ArrayList<String> ar = new ArrayList<String>();
    	for (int i = 0; i < cs.size(); i++) {
			ar.add(cs.get(i).nom);
		}
    	return ar;
    }
}