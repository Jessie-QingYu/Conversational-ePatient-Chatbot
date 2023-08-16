/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient;

import PatientManagement.Clinic.Clinic;
import PatientManagement.Patient.ClinicalHistory.AlergyHistory;
import PatientManagement.Patient.ClinicalHistory.Vaccination;
import PatientManagement.Patient.ClinicalHistory.VaccinationHistory;
import PatientManagement.Patient.Encounters.Diagnosis;
import PatientManagement.Patient.Encounters.Encounter;
import PatientManagement.Patient.Encounters.EncounterHistory;
import PatientManagement.Persona.Person;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author kal bugrara
 */
public class Patient {
    Clinic clinic;
    EncounterHistory encounterhistory;
    VaccinationHistory vachistory;
    Person person;
    AlergyHistory alergyhistory;
    
    public Patient(Person p, Clinic clinic) {
        encounterhistory = new EncounterHistory(this); // link this patient object back
        vachistory =new VaccinationHistory();
        person = p;
        this.clinic = clinic;
    }

    public EncounterHistory getEncounterHistory() {
        return encounterhistory;
    }

    public VaccinationHistory getVachistory() {
        return vachistory;
    }

    public Encounter getEncounterByDate(String date){
        Encounter encounter = encounterhistory.getEncounterByDate(date);
        return encounter;
    }
    
    
    // the below method will return the encounter where the infection occured
    // from the returned encounter you pull the event, the site, etc.

    public Encounter getConfirmedEncounter() {
        ArrayList<Encounter> patientencounterlist = encounterhistory.getEncounterList();

        for (Encounter currentencounter : patientencounterlist) {
            Diagnosis diag = currentencounter.getDiagnosis();
            if (diag.isConfirmed()) {
                return currentencounter;// send back the whole encounter so we extract event and site
            }
        }
        return null;
    }

    public boolean isConfirmedPositive() {

        ArrayList<Encounter> patientencounterlist = encounterhistory.getEncounterList();

        for (Encounter currentencounter : patientencounterlist) {
            Diagnosis diag = currentencounter.getDiagnosis();
            return diag.isConfirmed();

        }
        return false;
    }

    public Person getPerson() {
        return person;
    }

    public Encounter newEncounter(String chiefcomplaint, String data) {
        return encounterhistory.newEncounter(chiefcomplaint, data);
    }

    


    public Clinic getClinic() {
        return clinic;
    }

    public int getVitalTrend(String vitalName){
        ArrayList<Integer> singleVitalHistory = encounterhistory.getVitalSignsCollectionArrayList(vitalName);
        int trend = encounterhistory.getTrend(singleVitalHistory);
        return trend;
    }

    public void printPatientInfo() {
        String name = person.getPersonId();
        int age = person.getAge();
        String clinicName = clinic.getName();
        System.out.println("\u001B[32mHere is your personal clinical history: \u001B[0m");
        System.out.println("\u001B[33mPatient Name: \u001B[0m" + name);
        System.out.println("\u001B[33mPatient Age: \u001B[0m" + age);
        System.out.println("\u001B[33mClinic Name: \u001B[0m" + clinicName);
        ArrayList<Encounter> encounters = encounterhistory.getEncounterList();
        int ehNum = encounters.size();
        System.out.println();
        System.out.println("\u001B[32mEcounter History: \u001B[0m"  + "\u001B[35m\tTotal Times: \u001B[0m" + ehNum);
        for (Encounter e : encounters){
            e.printEncounterInfo();
        }
        ArrayList<Vaccination> vaccinations = vachistory.getVaccination();
        System.out.println();
        System.out.println("\u001B[32mVaccine History: \u001B[0m");
        for (Vaccination v : vaccinations){
            v.printVaccinationInfo();
        }
        System.out.println("\u001B[32mVaccine History Summary: \u001B[0m");
        HashMap<String, Integer> vacDoseCount = vachistory.countVaccineDose();
        for (String v: vacDoseCount.keySet()){
            int missDose = vachistory.numOfMissingDose(v);
            System.out.printf("%15s Current Dose: %d Missing Dose: %d\n", v, vacDoseCount.get(v), missDose);
        }

         
        

    }

}
