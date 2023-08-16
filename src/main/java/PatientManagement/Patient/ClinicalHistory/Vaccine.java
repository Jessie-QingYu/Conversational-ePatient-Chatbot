/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.ClinicalHistory;

/**
 *
 * @author kal bugrara
 */
public class Vaccine {
    private String name;
    private int dose;

    public Vaccine(String name, int dose){
        this.name = name;
        this.dose = dose;
    }
    public String getName(){
        return name;
    }
    public int getDose(){
        return dose;
    }

}
