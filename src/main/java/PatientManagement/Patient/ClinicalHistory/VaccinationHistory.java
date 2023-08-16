/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.ClinicalHistory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import PatientManagement.Catalogs.VaccineCatalog;

import java.util.Collections;


/**
 *
 * @author kal bugrara
 */
public class VaccinationHistory {
    private ArrayList<Vaccination> vaccinations;
    private VaccineCatalog vaccineCatalog;
    private HashMap<String, Integer> countVaccineDose;

    public VaccinationHistory(){
        vaccinations = new ArrayList<Vaccination>();
        countVaccineDose = new HashMap<String, Integer>();
        vaccineCatalog = new VaccineCatalog();
    }


    public void addVAccination(Vaccination vaccination){
        vaccinations.add(vaccination);
    }

    public ArrayList<Vaccination> getVaccination(){
        Collections.sort(vaccinations, new Comparator<Vaccination>() {
            public int compare(Vaccination v1, Vaccination v2) {
                return v1.getDate().compareTo(v2.getDate());
            }
        });
        return vaccinations;
    }

    public HashMap<String, Integer> countVaccineDose(){
        ArrayList<Vaccine> vaccines = vaccineCatalog.getVaccines();
        for (Vaccine v: vaccines){
            String vacName = v.getName();
            countVaccineDose.put(vacName,0);
        }
        for (Vaccination v : vaccinations){
            String vacName = v.getVaccine().getName();
            if (countVaccineDose.containsKey(vacName)){
                int currNum = countVaccineDose.get(vacName) + 1;
                countVaccineDose.put(vacName, currNum);
            }
            
        }
        return countVaccineDose;
    }

    public boolean isFullyVaccinated(String vaccinename ) {
        int currDose = countVaccineDose.get(vaccinename);
        int totalDose = vaccineCatalog.getVaccine(vaccinename).getDose();
        if (currDose < totalDose){
            return false;
        }
        return true;
    }

    public int numOfMissingDose(String vaccinename ) {
        int currDose = countVaccineDose.get(vaccinename);
        int totalDose = vaccineCatalog.getVaccine(vaccinename).getDose();
        return totalDose - currDose;
    }


}
    // ArrayList<Vaccination> vaccinations;

    // public void addVaccination(Vaccination v) {
    //     vaccinations.add(v);
    // }
    // public boolean hasVaccine(String name){
    //     for(Vaccine)

    // }

