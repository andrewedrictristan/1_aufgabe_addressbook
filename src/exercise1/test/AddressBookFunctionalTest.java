/*
 * Example code used in exercises for lecture "Grundlagen des Software-Testens"
 * Created and given by Ina Schieferdecker, Theo Vassiliou and Diana Serbanescu
 * Technische Universität Berlin
 */
package exercise1.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exercise1.addressbook.model.AddressBook;
import exercise1.addressbook.model.EmailAddress;
import exercise1.addressbook.model.Entry;
import exercise1.addressbook.model.Gender;
import exercise1.addressbook.model.PhoneNumber;
import exercise1.addressbook.model.SizeLimitReachedException;

/**
 * Uebung 1 - Grundlagen des Testens mit JUnit
 * 
 * Bitte Nummer der Gruppe eintragen: 9
 * 
 * Bitte Gruppenmitglieder eintragen:
 * 
 * @author Andrew Edric Tristan
 * @author Duc Hieu Dang	
 * @author Felix Kybranz
 * @author Hyerim Hwang
 * @author Philipp Christian Nickel
 */
public class AddressBookFunctionalTest {

	// The component under test
	private AddressBook addressBook;
	Entry aet, dhd,fk,hh,pcn;
	/*
	 * Aufgabe 3a) Schreiben Sie eine Methode zum Aufsetzen der Testumgebung
	 * ("Fixture"). Diese Methode soll automatisch vor jedem einzelnen JUnit
	 * Testfall ausgeführt werden. Innerhalb der Methode soll mindestens ein
	 * neues AddressBook Objekt angelegt und im Attribut "addressBook"
	 * gepeichert werden.
	 */
	@Before
	public void setUp() {
		//Speichern neues Objekt von AddressBook 
		 addressBook = new AddressBook();
		 
		//Entries Erstellen
		 aet = new Entry("Tristan", "Andrew Edric", Gender.Male, new PhoneNumber(0176626));
		 dhd = new Entry("Dang", "Duc Hieu", Gender.Male, new PhoneNumber(017342));
		 fk = new Entry("Kybranz", "Felix", Gender.Male, new EmailAddress("felixkyb@gmail.com"));
		 hh = new Entry("Hwang", "Hyerim", Gender.Female, new EmailAddress("hyerimhwang@yahoo.com"));
		 pcn = new Entry("Nickel", "Philipp Christian", Gender.Male, new PhoneNumber(0175752));
		
	}

	
	
	/*
	 * Aufgabe 3b) Schreiben Sie einen JUnit Testfall zum überprüfen der
	 * Funktionalität der addEntry() Methode.
	 */
	
	/*
	 * Die folgende Methode testet ob Entries am Anfang wirklich leer ist 
	 */
	@Test
	public void getSizeOfAddressBook() {
		assertEquals(0,addressBook.getEntries().size());
	}
	
	@Test
	public void testAddEntries() throws SizeLimitReachedException {
		//test addEntries
	
		assertTrue(addressBook.addEntry(aet));
		assertTrue(addressBook.addEntry(dhd));
		assertTrue(addressBook.addEntry(fk));
		assertTrue(addressBook.addEntry(hh));
		assertTrue(addressBook.addEntry(pcn));
		
		// Es wird erwartet, dass sich die Größe von Entries nach dem Einfügen vom "eintrag" um 5 sich vergrößert hat
		assertEquals(5,addressBook.getEntries().size());
		
		// Der folgende Befehl, checkt ob der contact schon in addressbook existiert, es wird erwartet, dass False zurückgibt
		assertFalse("The file ist existiert",addressBook.addEntry(dhd));
		
		
	}
	
	
	
	
	

	/*
	 * Aufgabe 3c) Schreiben Sie einen JUnit Testfall zum überprüfen der
	 * Funktionalität der getEntry() Methode.
	 */

	@Test
	public void testGetEntries() throws SizeLimitReachedException {
		assertTrue(addressBook.addEntry(fk));
		assertTrue(addressBook.addEntry(hh));
		
		//In diesem Befehl wird validiert, dass der Eintrag durch die Eingabe vom Namen gefunden wird und als Ausgabe herauskommt 
		assertEquals(fk, addressBook.getEntry("Kybranz", "Felix"));
		
		// Hier wird validiert, dass getEntry richtige FirstName liefert
		assertEquals("Hyerim",addressBook.getEntry("Hwang","Hyerim").getFirstName());
		
		// Wird ein in Entries nicht vorhandener  Eintrag gesucht dann soll ein Null herauskommen.
		assertNull(addressBook.getEntry("Philipp Christian","Nickel"));
	}
}
