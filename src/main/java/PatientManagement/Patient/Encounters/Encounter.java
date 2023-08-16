/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.Encounters;

import java.util.HashMap;
import PatientManagement.Catalogs.Limits;
import PatientManagement.Catalogs.VitalSignsCatalog;
import PatientManagement.Clinic.Clinic;
import PatientManagement.Clinic.Event;
import PatientManagement.Patient.Patient;
import ePatientSupportDatabase.VitalSignRanges;

/**
 *
 * @author kal bugrara
 * 
 */
// vorder = encounter.newVaccinationOrder();
// vacorder.newVaccination();

public class Encounter {
    

    Patient patient;
    

    String chiefComplaint;
    String date;

    

    Diagnosis diagnosis;
    Event event;
    VitalSigns vitalSigns;
    EncounterHistory encounterHistory;
    HashMap<String, Integer> vitalSignRecord;
    VitalSignRanges vitalNormal;

    // vital signs
    // orders: assessmentorders, ....

    public Encounter(Patient patient) {
        this.patient = patient;
        
    }

    public Encounter(Patient p, String cc, String d, EncounterHistory eh) { // event is the date when the check was made
        chiefComplaint = cc;
        date = d;
        patient = p;
        encounterHistory = eh;
        vitalSignRecord = new HashMap<String, Integer>();
        vitalNormal = new VitalSignRanges();
        vitalSigns = new VitalSigns(this);
    }

    
    
    public Encounter(Patient p, String cc, Event ev, EncounterHistory eh) { // event is the date when the check was made
        chiefComplaint = cc;
        event = ev;
        patient = p;
        encounterHistory = eh;
        vitalSigns = new VitalSigns(this);
    }

   

    public String getDate() {
        return date;
    }
    
    public void addCurrentVitalRecord(String name, int value){
        vitalSignRecord.put(name, value);
    }

    public HashMap<String, Integer> getVitalSignRecord() {
        return vitalSignRecord;
    }

    public Patient getPatient() {
        return patient;
    }


    

    public void newDiagnosis(String diseasetype, boolean confirmed) {
        diagnosis = new Diagnosis(diseasetype, confirmed);
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public Limits getVitalSignLimits(int age, String name) {
        Clinic clinic = encounterHistory.getPatient().getClinic();
        VitalSignsCatalog vsc = clinic.getVitalSignsCatalog();
        return vsc.findVitalSignLimits(age, name);
    }

    public VitalSignMetric addNewVitals(String name, int value) {
        return vitalSigns.addNewVitals(name, value);
    }

   

    public EncounterHistory getEncounterHistory() {
        return encounterHistory;
    }

    public boolean areVitalsNormal() {
        return vitalSigns.areNormal();
    }

    public void printEncounterInfo() {
        int age = patient.getPerson().getAge();
        System.out.println("\u001B[33m" + date + "\u001B[0m" + " \u001B[34m" + chiefComplaint +  "\u001B[0m" + " \u001B[35m||Diagnosis: "+ diagnosis.getCategory() + " Confirm status: " + diagnosis.isConfirmed() + "\u001B[0m");
        System.out.println("\t\u001B[35mVital Signs:\u001B[0m");
        for (String name: vitalSignRecord.keySet()){
            int value = vitalSignRecord.get(name);
            String analysis = vitalNormal.isHighOrLow(age, name, value);
            String range = vitalNormal.getVitalNormalRange(age,name);
            System.out.println("\t" + name + ":" + value + "  " + analysis + "\t(normal range)" + range);
        }
    }




}