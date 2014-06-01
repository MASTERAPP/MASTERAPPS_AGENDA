// Classe Contacte que guardara la informacio de un contacte

package com.example.masterappsagenda;

public class Contacte {
	private String nom;
	private String direccio;
	private String fix;
	private String mobil;
	private String email;
	private Boolean facebook;
	private String genere;
	private String tipus;
	
	public Contacte() {
		nom="";
		direccio="";
		fix="";
		mobil="";
		email="";
		facebook=false;
		genere="";
		tipus="";
    }
	public Contacte(String nom2, String direccio2, String fix2, String mobil2, String email2, Boolean facebook2, String genere2, String tipus2) {
		setContacte(nom2, direccio2, fix2, mobil2, email2, facebook2, genere2, tipus2);
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
    
    public boolean isValidContact() {
		if (nom.compareTo("") != 0 && ((isNumeric(fix) == true) ||
				(fix.compareTo("") == 0)) &&
				((isNumeric(mobil) == true) || (mobil.compareTo("") == 0)) &&
				((isValidEmailAddress(email) == true) || (email.compareTo("") == 0))) {
			return true;
		}
		else {
			return false;
		}
	}
    
	public void setContacte(String nom2, String direccio2, String fix2, String mobil2, String email2, Boolean facebook2, String genere2, String tipus2) {
		nom=nom2;
		direccio=direccio2;
		fix=fix2;
		mobil=mobil2;
		email=email2;
		facebook=facebook2;
		genere=genere2;
		tipus=tipus2;
    }
	
	//Getters
	public String getNom() {
		return nom;
	}
	public String getDireccio() {
		return direccio;
	}
	public String getFix() {
		return fix;
	}
	public String getMobil() {
		return mobil;
	}
	public String getEmail() {
		return email;
	}
	public Boolean getFacebook() {
		return facebook;
	}
	public String getGenere() {
		return genere;
	}
	public String getTipus() {
		return tipus;
	}
}
