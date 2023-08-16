/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.ClinicalHistory;
// this class is for vaccinating the patient for missing vaccination.
/**
 *
 * @author kal bugrara
 */
public class Vaccination {
    private Vaccine vaccine;
    private String date;

    public Vaccination(Vaccine vaccine, String date){
        this.vaccine = vaccine;
        this.date = date;
    }
    
    public Vaccine getVaccine(){
        return vaccine;
    }

    public String getDate(){
        return date;
    }
        // 将疫苗名称和接种日期转化为一个字符串，比如：“flu on 2022-03-15”
    // public String toString(){
    //     return String.format("%s on $%s", vaccine.getName(), date);
    // }

    


    public void printVaccinationInfo() {
        String vacName = vaccine.getName();
        System.out.println(date + "\t" + vacName);
    }
}
