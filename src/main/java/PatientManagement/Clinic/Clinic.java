/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Clinic;

import PatientManagement.Catalogs.DrugCatalog;
import PatientManagement.Catalogs.VitalSignsCatalog;
import PatientManagement.Patient.Patient;
import PatientManagement.Persona.Person;
import PatientManagement.Persona.PersonDirectory;

/**
 *
 * @author kal bugrara
 */
public class Clinic {
    PatientDirectory patientdirectory;
    PersonDirectory persondirectory;
    SiteCatalog sitelist;
    LocationList locationlist;
    DrugCatalog drugcatalog;
    EventSchedule eventschedule;
    VitalSignsCatalog vitalSignsCatalog;
    String name; // name of the clinic

   

    public Clinic(String n) {
        eventschedule = new EventSchedule();
        sitelist = new SiteCatalog();
        locationlist = new LocationList();
        persondirectory = new PersonDirectory();
        patientdirectory = new PatientDirectory(this);
        vitalSignsCatalog = new VitalSignsCatalog();
        name = n;
    }

    public String getName() {
        return name;
    }

    public SiteCatalog getSiteCatalog() {
        return sitelist;
    }

    public LocationList getLocationList() {
        return locationlist;
    }

    public Site newSite(Location location) {

        Site s = sitelist.newSite(location);
        return s;
    }

    public VitalSignsCatalog getVitalSignsCatalog() {
        return vitalSignsCatalog;
    }

    public PersonDirectory getPersonDirectory() {
        return persondirectory;
    }

    public PatientDirectory getPatientDirectory() {
        return patientdirectory;
    }

    public Patient addNewPatient(Person person){
        Patient patient = patientdirectory.newPatient(person);
        return patient;
    
    }

    public void printClinicSummary() { 
        int totalPatient = patientdirectory.getPatients().size();
        
        System.out.println(name + "\tTotal Patient  = " + totalPatient);

    }
}
