// Classe Contacte que guardara la informacio de un contacte

public class Contacte {
	public String nom;
	public String direccio;
	public String fix;
	public String mobil;
	public String email;
	public Boolean facebook;
	public String genere;
	public String tipus;
	
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
	public Contacte getContacte() {
		return this;
	}
}
