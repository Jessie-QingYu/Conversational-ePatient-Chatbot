/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.Orders;

import PatientManagement.Patient.ClinicalHistory.Vaccination;
import PatientManagement.Patient.ClinicalHistory.Vaccine;
import PatientManagement.Patient.Patient;
import PatientManagement.Patient.ClinicalHistory.VaccinationHistory;

/**
 *
 * @author kal bugrara
 */
public class VaccinationOrder extends Order {
    Vaccine vaccine;
    Patient patient;
    String date;
    Vaccination vaccination;
    VaccinationHistory VaccinationHistory;

    public VaccinationOrder(Patient p, Vaccine v, String d) {
        super();
        vaccine = v;
        date = d;
        patient = p;
        newVaccination();
    }

    private void newVaccination() {
        Vaccination vaccination = new Vaccination(vaccine, date);
        patient.getVachistory().addVAccination(vaccination);
    }

}

