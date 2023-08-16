package ePatientSupportDatabase;

// VitalSign Normal Ranges data
// method : report


import java.util.ArrayList;
import java.util.HashMap;

import PatientManagement.Catalogs.AgeGroup;
import PatientManagement.Catalogs.VitalSignLimits;
import PatientManagement.Catalogs.VitalSignsCatalog;

public class VitalSignRanges {
    private VitalSignsCatalog vsCatalog;
    private ArrayList<String> vitalNames;
    private HashMap<String, HashMap<Integer, String>> vitalAnalysis;

    public VitalSignRanges() {
        vsCatalog = new VitalSignsCatalog();
        vitalNames = new ArrayList<String>();
        vitalAnalysis = new HashMap<String, HashMap<Integer, String>>();
        initializeRange();
        initializeAnalysis();
    }

    private void initializeRange(){

        AgeGroup newborn = vsCatalog.newAgeGroup("Newborn 0", 0, 0);
        AgeGroup infant = vsCatalog.newAgeGroup("Infant <1", 1, 1);
        AgeGroup toddler = vsCatalog.newAgeGroup("Toddler 2-3", 3, 2);
        AgeGroup preschooler = vsCatalog.newAgeGroup("Preschooler 4-5", 5, 4);
        AgeGroup schoolAge = vsCatalog.newAgeGroup("SchoolAge 6-12", 12, 6);
        AgeGroup adolescent = vsCatalog.newAgeGroup("Adolescent 13-18", 18, 13);
        AgeGroup adult = vsCatalog.newAgeGroup("Adult 19-120", 120, 19);

        vitalNames.add("RR");
        vitalNames.add("HR");
        vitalNames.add("BP");
        vitalNames.add("BG");

        VitalSignLimits respiratoryRate = vsCatalog.newVitalSignLimits("RR");
        VitalSignLimits heartRate = vsCatalog.newVitalSignLimits("HR");
        VitalSignLimits bloodPressure = vsCatalog.newVitalSignLimits("BP");
        VitalSignLimits bloodGlucose = vsCatalog.newVitalSignLimits("BG");
        
        respiratoryRate.addLimits(newborn, 50, 30);
        respiratoryRate.addLimits(infant, 30, 20);
        respiratoryRate.addLimits(toddler, 30, 20);
        respiratoryRate.addLimits(preschooler, 30, 20);
        respiratoryRate.addLimits(schoolAge, 30, 20);
        respiratoryRate.addLimits(adolescent, 20, 12);
        respiratoryRate.addLimits(adult, 20, 12);

        heartRate.addLimits(newborn, 160, 120);
        heartRate.addLimits(infant, 140, 80);
        heartRate.addLimits(toddler, 130, 80);
        heartRate.addLimits(preschooler, 120, 80);
        heartRate.addLimits(schoolAge, 110, 70);
        heartRate.addLimits(adolescent, 105, 60);
        heartRate.addLimits(adult, 100, 55);

        bloodPressure.addLimits(newborn, 70, 50);
        bloodPressure.addLimits(infant, 100, 70);
        bloodPressure.addLimits(toddler, 110, 80);
        bloodPressure.addLimits(preschooler, 110, 80);
        bloodPressure.addLimits(schoolAge, 120, 95);
        bloodPressure.addLimits(adolescent, 120, 100);
        bloodPressure.addLimits(adult, 135, 110);

        bloodGlucose.addLimits(newborn, 100, 80);
        bloodGlucose.addLimits(infant, 100, 80);
        bloodGlucose.addLimits(toddler, 100, 80);
        bloodGlucose.addLimits(preschooler, 100, 80);
        bloodGlucose.addLimits(schoolAge, 100, 80);
        bloodGlucose.addLimits(adolescent, 100, 80);
        bloodGlucose.addLimits(adult, 100, 80);
    }

    public void initializeAnalysis(){
        HashMap<Integer, String> RRAnalysis = new HashMap<Integer, String>();
        RRAnalysis.put(0," \u27A1 Unstable respiratory rate! Possible reasons include asthma, chronic obstructive pulmonary disease (COPD)\n\uD83C\uDF52If you have any diseases, such as asthma, heart disease, stroke, etc.,\n\uD83D\uDCAAplease follow the doctor's treatment plan and report to the doctor in a timely manner if you experience unstable respiratory rates.\n\uD83C\uDF31If you are a healthy person but experience unstable breathing, consider undergoing regular physical check-ups to understand your health status and seek medical advice.\nAdopt healthy lifestyles, including healthy diets, moderate exercise, and adequate sleep, to help prevent and manage potential health problems.\n\uD83E\uDD73If you have bad habits such as smoking and drinking, you should quit immediately to reduce the risk of respiratory and heart diseases.\n");
        RRAnalysis.put(1, "  \u27A1 Increasing respiratory rate! It may be due to respiratory system diseases such as infections, pneumonia\n\uD83C\uDF52If you have been diagnosed with one of the above diseases, please follow the doctor's treatment plan and promptly report any increase in respiratory rate to the doctor.\n\uD83D\uDCAAIf you have not been diagnosed with one of the above diseases, consider undergoing regular physical examinations to understand your health status and seek advice from a doctor.\n\uD83C\uDF31Adopting a healthy lifestyle, including a balanced diet, moderate exercise, and adequate sleep, can help prevent and manage potential health problems.\n\uD83E\uDD73If you have unhealthy habits such as smoking and drinking, you should quit immediately to reduce the risk of respiratory and cardiovascular diseases.\n");
        RRAnalysis.put(-1, "  \u27A1 Decreasing respiratory rate! It may be due to respiratory failure, central nervous system diseases\n\uD83C\uDF52If you have already been diagnosed with one of the above diseases, please follow your doctor's treatment plan and report any changes in respiratory rate to your doctor in a timely manner.\n\uD83D\uDCAAIf you have not been diagnosed with any of the above diseases, consider scheduling regular physical checkups to understand your health condition and seek medical advice.\n\uD83C\uDF31Maintaining a healthy lifestyle, including a balanced diet, moderate exercise, and adequate sleep, can prevent and manage potential health problems.\n\uD83E\uDD73If you have unhealthy habits such as smoking and drinking alcohol, it is recommended that you quit immediately to reduce the risk of respiratory and cardiovascular diseases.\n");
        RRAnalysis.put(3, "  \u27A1 Sorry! You are a new patient, but we will have your vital signs trend next\n");
        vitalAnalysis.put("RR",RRAnalysis);
        
        HashMap<Integer, String> HRAnalysis = new HashMap<Integer, String>();
        HRAnalysis.put(0, "  \u27A1 Unstable Heart Rate! It may be due to conditions such as arrhythmia and supraventricular tachycardia\n\uD83C\uDF52If you have already been diagnosed with any of the above conditions, please follow your doctor's treatment plan and report any changes in heart rate to your doctor in a timely manner.\n\uD83D\uDCAAIf you have not been diagnosed with any of the above conditions, consider scheduling regular physical checkups to understand your health condition and seek medical advice.\n\uD83C\uDF31Avoid or reduce the consumption of stimulants such as caffeine, nicotine, and alcohol, as they can increase heart rate and exacerbate irregular heart rhythms.\n\uD83E\uDD73Manage your stress levels with relaxation techniques such as meditation, deep breathing exercises, or yoga.\nIf necessary, your doctor may recommend medication, medical procedures, or surgery to control and regulate your heart rate.\n");
        HRAnalysis.put(1, "  \u27A1 Increasing Heart Rate! It may be due to reasons such as infections, anemia, hyperthyroidism, medication\n\uD83C\uDF52If you have already been diagnosed with any of the above conditions, please follow your doctor's treatment plan and report any changes in heart rate to your doctor in a timely manner.\n\uD83D\uDCAAIf you have not been diagnosed with any of the above conditions, consider scheduling regular physical checkups to understand your health condition and seek medical advice.\n\uD83C\uDF31Avoid or reduce the consumption of stimulants such as caffeine, nicotine, and alcohol, as they can increase heart rate.\nPractice stress-reducing techniques such as meditation, yoga, or deep breathing exercises to manage anxiety and stress.\n\uD83E\uDD73Maintain a healthy lifestyle, including a balanced diet, regular exercise, and adequate sleep, to prevent and manage potential health problems.\n");
        HRAnalysis.put(-1, "  \u27A1 Decreasing Heart Rate ! It may be due to reasons such as cardiac conduction system disease, medication\n\uD83C\uDF52If you have already been diagnosed with any of the above conditions, \n\uD83D\uDCAAplease follow your doctor's treatment plan and report any changes in heart rate to your doctor in a timely manner.\n\uD83D\uDCAAIf you have not been diagnosed with any of the above conditions, consider scheduling regular physical checkups to understand your health condition and seek medical advice.\n\uD83C\uDF31Maintaining a healthy lifestyle, including a balanced diet, regular exercise, and adequate sleep, can prevent and manage potential health problems.\n\uD83E\uDD73If you have unhealthy habits such as smoking and drinking alcohol, it is recommended that you quit immediately to reduce the risk of cardiovascular diseases.\n");
        HRAnalysis.put(3, "  \u27A1 Sorry! You are a new patient, we will have your vital signs trend next\n");
        vitalAnalysis.put("HR",HRAnalysis);
        
        HashMap<Integer, String> BPAnalysis = new HashMap<Integer, String>();
        BPAnalysis.put(0, "  \u27A1Unstable Blood Pressure! It may be due to hypertension, hypotension\n\uD83C\uDF52If you have already been diagnosed with any of the above conditions, please follow your doctor's treatment plan and report any changes in blood pressure to your doctor in a timely manner.\n\uD83D\uDCAAIf you have not been diagnosed with any of the above conditions, consider scheduling regular physical checkups to understand your health condition and seek medical advice.\n\uD83D\uDCAAMaintain a healthy lifestyle with regular exercise, a balanced diet, and adequate hydration.\n\uD83C\uDF31Reduce your intake of salt and alcohol, as they can increase blood pressure.\n\uD83E\uDD73If necessary, your doctor may recommend medication or other treatments to control and regulate your blood pressure.\n");
        BPAnalysis.put(1, "  \u27A1Increasing Blood Pressure! It may be due to hypertension, with risks including cardiovascular and cerebrovascular diseases\n\uD83C\uDF52If you have already been diagnosed with any of the above conditions, please follow your doctor's treatment plan and monitor your blood pressure regularly.\n\uD83D\uDCAAIf you have not been diagnosed with any of the above conditions, consider scheduling regular physical checkups to understand your health condition and seek medical advice.\n\uD83D\uDCAAMaintain a healthy lifestyle with regular exercise, a balanced diet, and adequate hydration.\n\uD83C\uDF31Reduce your intake of salt and alcohol, as they can increase blood pressure.\n\uD83E\uDD73If necessary, your doctor may recommend medication or other treatments to control and regulate your blood pressure.\n");
        BPAnalysis.put(-1, "  \u27A1Decreasing Blood Pressure! It may be due to causes such as bleeding, shock, and neurological diseases.\n\uD83C\uDF52If you have already been diagnosed with any of the above conditions, please follow your doctor's treatment plan and monitor your blood pressure regularly.\n\uD83D\uDCAAIf you have not been diagnosed with any of the above conditions, consider scheduling regular physical checkups to understand your health condition and seek medical advice.\n\uD83D\uDCAADrink plenty of fluids to avoid dehydration, but avoid excessive consumption of alcohol.\n\uD83C\uDF31Avoid sudden changes in position, such as standing up quickly, which can cause dizziness and a drop in blood pressure.\n\uD83E\uDD73If necessary, your doctor may recommend medication or other treatments to increase and regulate your blood pressure.\n");
        BPAnalysis.put(3, "  \u27A1Sorry! You are a new patient, we will have your vital signs trend next\n");
        vitalAnalysis.put("BP",BPAnalysis);

        HashMap<Integer, String> BGAnalysis = new HashMap<Integer, String>();
        BGAnalysis.put(0, "   \u27A1Unstable blood glucose! It may be associated with various diseases, including diabetesWe suggest that you take the following steps:\n\uD83C\uDF52Make an appointment with a doctor immediately for examination and diagnosis.\n\uD83D\uDCAABefore seeing the doctor, try to avoid high-sugar or low-blood-sugar foods, such as candy, cookies, chocolate, etc.\n\uD83C\uDF31If you have been diagnosed with diabetes, excessive insulin injection, pancreatic tumor, liver disease, etc., please follow your doctor's treatment plan.\n\uD83E\uDD73If you have not been diagnosed with any disease, but continuous instability of fasting blood glucose has affected your quality of life, please have regular physical examinations to ensure your physical health. \n\uD83E\uDD73In addition, you can help control fasting blood glucose levels by eating a balanced diet, exercising moderately, and reducing stress.\n");
        BGAnalysis.put(1, "   \u27A1Increasing blood glucose! It can lead to diabetes, with associated risks including cardiovascular and neurological diseases\n\uD83C\uDF52We suggest you take the following steps:\nMake an appointment with a doctor immediately for examination and diagnosis.\n\uD83D\uDCAABefore seeing the doctor, try to avoid eating high-sugar foods and drinks to prevent further elevation of blood sugar levels.\n\uD83C\uDF31If you have already been diagnosed with diabetes, please follow the doctor's treatment plan, including dietary control, medication, and exercise.\n\uD83E\uDD73If you have not been diagnosed with diabetes, adopt a healthy lifestyle, including a balanced diet, moderate exercise, and maintaining a healthy weight. Regularly undergo physical check-ups to ensure good health.\n");
        BGAnalysis.put(-1, "  \u27A1Decreasing blood glucose! It can lead to hypoglycemia, with symptoms such as fainting and seizures when severeWe suggest you take the following steps:\n\uD83C\uDF52Make an appointment with a doctor immediately for examination and diagnosis.\n\uD83D\uDCAABefore seeing the doctor, try to avoid low blood sugar foods such as candy, cookies, chocolate, etc.\n\uD83C\uDF31If you have already been diagnosed with insulin overdose, pancreatic tumor, liver disease, or any other disease, please follow the doctor's treatment plan.\n\uD83E\uDD73If you have not been diagnosed with any disease, but the continuous decrease in fasting blood glucose has already affected your quality of life, regularly undergo physical check-ups to ensure good health.\n");
        BGAnalysis.put(3, "  \u27A1Sorry! You are a new patient, we will have your vital signs trend next\n");
        vitalAnalysis.put("BG",BGAnalysis);

    }

    public ArrayList<String> getVitalNames() {
        return vitalNames;
    }

    public HashMap<String, HashMap<Integer, String>> getVitalAnalysis() {
        return vitalAnalysis;
    }

    public boolean report(int age, String name, int value){
        boolean judge = false;
        for (String n : vitalNames){
            if (name == n){
                judge = vsCatalog.findVitalSignLimitsByName(name).isNormal(age, value);
                if (judge == true){
                    System.out.println("\u001B[32mCongratulations! Your " + name + " is in normal ranges! Keeping your health~\u001B[0m");
                }
                else {
                    System.out.println("\u001B[31mSorry! Your " + name + " is out of ranges! You should immediately take actions to get health!\u001B[0m");
                }
                break;
            }
        }
        return judge;
    }

    public String isHighOrLow(int age, String name, int value){
        String analysis = "";
        for (String n : vitalNames){
            if (name == n){
                analysis = vsCatalog.findVitalSignLimitsByName(name).isHighOrDown(age, value);
                break;
            }
        }
        return analysis;
    }

    public String getVitalNormalRange(int age, String name){
        String normalRange = "";
        for (String n : vitalNames){
            if (name == n){
                normalRange = vsCatalog.findVitalSignLimitsByName(name).getRange(age, name);
                break;
            }
        }
        return normalRange;




    }

    
    


    
    

    
}
