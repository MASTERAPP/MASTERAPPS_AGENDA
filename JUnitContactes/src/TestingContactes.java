// En aquesta classe de testing, s'analitzarà la classe Contactes,
// la qual emmagatzema contactes utilitzant un ArrayList.
// La classe de testing realitza test de tots els metodes de la classe
// objectiu.

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


public class TestingContactes {
		
	public TestingContactes() {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetContacts() {
		// Aquest metode retorna tots els contactes en un ArrayList
		Contactes tester = new Contactes();
		Contacte c1 = new Contacte("Joan","C/Av Lleida 22","973251010","688888888","1@correu.com",true,"Home","Amic");
        Contacte c2 = new Contacte("Alexia","C/Av Lleida 25","973252020","611111111","2@correu.com",true,"Dona","Familia");
        Contacte c3 = new Contacte("Marc","C/Av Lleida 40","973253030","622222222","3@correu.com",false,"Home","Treball");
		tester.addContact(c1);
        tester.addContact(c2);
        tester.addContact(c3);
        // Estan tots els contactes?
		assertEquals("3 contacts added, size of array is 3", 3, tester.getContacts().size());
		// Els contactes tenen els valors correctes?
		assertEquals("contact 1", c1, tester.getContacts().get(0));
		assertEquals("contact 2", c2, tester.getContacts().get(1));
		assertEquals("contact 3", c3, tester.getContacts().get(2));
	}

	@Test
	public void testIsNumeric() {
		// Aquest metode comprova si el string entrat compte un numero natural
		Contactes tester = new Contactes();
		// Cadena que conte tots els caracters valids
		assertEquals("numero correcte", true, tester.isNumeric("0123456789"));
		assertEquals("numero negatiu", false, tester.isNumeric("-2"));
		assertEquals("caracters", false, tester.isNumeric("1a"));
		assertEquals("espai", false, tester.isNumeric("1 1"));
	}
	
	@Test
	public void testIsValidEmailAddress() {
		// Aquest metode comprova si el string entrat compte un correu valid
		Contactes tester = new Contactes();
		assertEquals("email valid", true, tester.isValidEmailAddress("1@correu.com"));
		assertEquals("email invalid 1", false, tester.isValidEmailAddress("1@@correu.com"));
		assertEquals("email invalid 2", false, tester.isValidEmailAddress("@correu.com"));
		assertEquals("email invalid 3", false, tester.isValidEmailAddress("1@correu"));
		assertEquals("email invalid 4", false, tester.isValidEmailAddress("1@correu."));
		assertEquals("email invalid 5", false, tester.isValidEmailAddress("1@.com"));
		assertEquals("email invalid 6", false, tester.isValidEmailAddress("correu.com"));
	}
	
	@Test
	public void testIsValidContact() {
		// Aquesta funcio comprova si un contacte compte valors correctes
		// Aquesta funcio tambe realitza implicitament el tests dels metodes privats
		// isNumeric() i isValidEmailAddress()
		Contactes tester = new Contactes();
		assertEquals("contacte valid", true, tester.isValidContact(new Contacte("Joan","C/Av Lleida 22","973251010","688888888","1@correu.com",true,"Home","Amic")));
		assertEquals("email invalid 1", false, tester.isValidContact(new Contacte("Joan","C/Av Lleida 22","973251010","688888888","1@@correu.com",true,"Home","Amic")));
		assertEquals("email invalid 2", false, tester.isValidContact(new Contacte("Joan","C/Av Lleida 22","973251010","688888888","@correu.com",true,"Home","Amic")));
		assertEquals("email invalid 3", false, tester.isValidContact(new Contacte("Joan","C/Av Lleida 22","973251010","688888888","1@correu",true,"Home","Amic")));
		assertEquals("email invalid 4", false, tester.isValidContact(new Contacte("Joan","C/Av Lleida 22","973251010","688888888","1@correu.",true,"Home","Amic")));
		assertEquals("email invalid 5", false, tester.isValidContact(new Contacte("Joan","C/Av Lleida 22","973251010","688888888","1@.com",true,"Home","Amic")));
		assertEquals("email invalid 6", false, tester.isValidContact(new Contacte("Joan","C/Av Lleida 22","973251010","688888888","correu.com",true,"Home","Amic")));
		assertEquals("mobil amb lletres, invalid", false, tester.isValidContact(new Contacte("Joan","C/Av Lleida 22","973251010","688888888a","1@correu.com",true,"Home","Amic")));
		assertEquals("fix amb lletres, invalid", false, tester.isValidContact(new Contacte("Joan","C/Av Lleida 22","973251010aa","688888888","1@correu.com",true,"Home","Amic")));
		assertEquals("sense nom, invalid", false, tester.isValidContact(new Contacte("","C/Av Lleida 22","973251010","688888888","1@correu.com",true,"Home","Amic")));
	}

	@Test
	public void testExistContactByName() {
		// Aquest metode comprova si existeix un contacte amb el nom entrat
		Contactes tester = new Contactes();
		tester.addContact(new Contacte("Joan","C/Av Lleida 22","973251010","688888888","1@correu.com",true,"Home","Amic"));
		assertEquals("exist", true, tester.existContactByName("Joan"));
		assertEquals("not exist", false, tester.existContactByName("Maria"));
	}

	@Test
	public void testAddContact() {
		// Aquest metode afegeix un contacte
		Contactes tester = new Contactes();
		tester.addContact(new Contacte("Joan","C/Av Lleida 22","973251010","688888888","1@correu.com",true,"Home","Amic"));
		tester.addContact(new Contacte("Joan","C/Av Lleida 40","973253030","622222222","3@correu.com",false,"Home","Treball"));
		tester.addContact(new Contacte("Alexia","C/Av Lleida 25","973252020","611111111","2@correu.com",true,"Dona","Familia"));
        // Les entrades amb el mateix nom, se sobreescriuen
		assertEquals("2 contactes afegits", 2, tester.getSize());
	}

	@Test
	public void testDelContactByName() {
		// Aquest metode esborra un contacte si esta en la llista
		Contactes tester = new Contactes();
		tester.addContact(new Contacte("Joan","C/Av Lleida 22","973251010","688888888","1@correu.com",true,"Home","Amic"));
		tester.addContact(new Contacte("Joan","C/Av Lleida 40","973253030","622222222","3@correu.com",false,"Home","Treball"));
		tester.addContact(new Contacte("Alexia","C/Av Lleida 25","973252020","611111111","2@correu.com",true,"Dona","Familia"));
		tester.delContactByName("Joan");
		tester.delContactByName("Marc"); // Intentem esborrar un usuari que no existeix
        // Les entrades amb el mateix nom, se sobreescriuen
		assertEquals("1 contacte esborrat", 1, tester.getSize());
	}

	@Test
	public void testGetContacteByName() {
		// Aquest metode obte un contacte si el troba en la llista
		Contactes tester = new Contactes();
		Contacte c1 = new Contacte("Joan","C/Av Lleida 22","973251010","688888888","1@correu.com",true,"Home","Amic");
		tester.addContact(c1);
		assertEquals("nom existent", c1, tester.getContacteByName("Joan"));
		assertFalse("nom no existent", c1.equals(tester.getContacteByName("Marc"))); // Aquest contacte no existeix
	}

	@Test
	public void testGetContactNames() {
		// Aquest metode retorna un ArrayList de string amb els noms dels contactes
		Contactes tester = new Contactes();
		Contacte c1 = new Contacte("Joan","C/Av Lleida 22","973251010","688888888","1@correu.com",true,"Home","Amic");
        Contacte c2 = new Contacte("Alexia","C/Av Lleida 25","973252020","611111111","2@correu.com",true,"Dona","Familia");
        Contacte c3 = new Contacte("Marc","C/Av Lleida 40","973253030","622222222","3@correu.com",false,"Home","Treball");
		tester.addContact(c1);
        tester.addContact(c2);
        tester.addContact(c3);
        ArrayList<String> noms = tester.getContactNames();
        // Estan tots els contactes?
		assertEquals("3 names", 3, noms.size());
		// Els contactes tenen els valors correctes?
		assertEquals("contact 1", c1.nom, noms.get(0));
		assertEquals("contact 2", c2.nom, noms.get(1));
		assertEquals("contact 3", c3.nom, noms.get(2));
	}

}
