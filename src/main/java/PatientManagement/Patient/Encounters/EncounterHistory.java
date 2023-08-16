/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.Encounters;

import PatientManagement.Patient.Patient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 *
 * @author kal bugrara
 */
public class EncounterHistory {
    ArrayList<Encounter> encounters;
    Patient patient;
    HashMap<String, Integer> singleVitalSignTrend;

    public EncounterHistory(Patient p) {
        patient = p;
        encounters = new ArrayList<Encounter>();
        singleVitalSignTrend = new HashMap<String, Integer>();
    }

    // each encounter must link to the event at the site
    public Encounter newEncounter(String chiefcomplaint, String data) {
        Encounter e = new Encounter(patient, chiefcomplaint, data, this);
        encounters.add(e);
        return e;
    }

    public ArrayList<Encounter> getEncounterList() {
        Collections.sort(encounters, new Comparator<Encounter>() {
            public int compare(Encounter e1, Encounter e2) {
                return e1.getDate().compareTo(e2.getDate());
            }
        });
        return encounters;
    }

    public Patient getPatient() {
        return patient;
    }

    public Encounter getEncounterByDate(String date) {
        for (Encounter e: encounters){
            String currDate = e.getDate();
            if (currDate.equals(date)){
                return e;
            }

        }
        return null;
    }

    public HashMap<String, Integer> getSingleVitalSignCollectionByName(String vitalName){
        for (Encounter e: encounters){
            HashMap<String, Integer> vitalSignRecord = e.getVitalSignRecord();
            String date = e.getDate();
            int vitalValue = vitalSignRecord.get(vitalName);
            singleVitalSignTrend.put(date, vitalValue);
        }
        return singleVitalSignTrend;
    }



    
    //ArrayList<VitalSignMetric> vitalSigns;
    //<123,24,35,>

    public ArrayList<Integer> getVitalSignsCollectionArrayList (String vitalName){
        ArrayList<Integer> getVitalSignsTrend = new ArrayList<Integer>();
        for (Encounter e: encounters){
            HashMap<String,Integer> vitalMap = e.getVitalSignRecord();
            int vitalValue = vitalMap.get(vitalName);
            getVitalSignsTrend.add(vitalValue);
        }
        return getVitalSignsTrend;
    }

    public int getTrend(ArrayList<Integer> nums) {
        if (nums.size() < 2) {
        return 3; // 无法确定趋势
        }
        int trend = 0; // 0: 波动, 1: 全增, -1: 全减， 3  无法确定趋势
        int prev = nums.get(0);
        for (int i = 1; i < nums.size(); i++) {
            int curr = nums.get(i);
            if (curr < prev) { // 减少趋势
                if (trend == 0) {
                    trend = -1; // 设置趋势为减少
                } else if (trend == 1) {
                    return 0; // 出现不符合的数字，重置趋势
                }
            } else if (curr > prev) { // 增加趋势
                if (trend == 0) {
                    trend = 1; // 设置趋势为增加
                } else if (trend == -1) {
                    return 0; // 出现不符合的数字，重置趋势
                }
            }
            prev = curr;
        }
        return trend;
    }
        
    
    
   

}








