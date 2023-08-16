/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Catalogs;

import java.util.ArrayList;

import PatientManagement.Patient.ClinicalHistory.Vaccine;

/**
 *
 * @author kal bugrara
 */
public class VaccineCatalog {
    



    private ArrayList<Vaccine> vaccines;

    public VaccineCatalog(){
        vaccines = new ArrayList<>();
        vaccines.add(new Vaccine("flu", 1));
        vaccines.add(new Vaccine("varicella", 2));
        vaccines.add(new Vaccine("pertussis",5));
        vaccines.add(new Vaccine("mmr", 2));
        vaccines.add(new Vaccine("pneumococcal", 4));
        vaccines.add(new Vaccine("hpv", 3));
        vaccines.add(new Vaccine("hepatitis B", 3));
    }

    public ArrayList<Vaccine> getVaccines() {
        return vaccines;
    }


    
    public Vaccine getVaccine(String name ){
        for (Vaccine vaccine : vaccines){
            if (vaccine.getName().equals(name)){
                return vaccine;
            }
        }
        return null;
    }

   


}


