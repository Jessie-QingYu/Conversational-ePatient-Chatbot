package ePatientSupportDatabase;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// store patient data by name-patient map
// method : reportHistoryByName

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import com.github.javafaker.Faker;
import PatientManagement.Catalogs.VaccineCatalog;
import PatientManagement.Clinic.Clinic;
import PatientManagement.Clinic.Location;
import PatientManagement.Clinic.Site;
import PatientManagement.Patient.Patient;
import PatientManagement.Patient.ClinicalHistory.Vaccination;
import PatientManagement.Patient.ClinicalHistory.Vaccine;
import PatientManagement.Patient.Encounters.Encounter;
import PatientManagement.Patient.Encounters.EncounterHistory;
import PatientManagement.Persona.Person;


public class patientData {

    

    private HashMap<String, Patient> patients;
    private HashMap<String, Clinic> clinics;
    private HashMap<Patient, Encounter> encounters;
    private VaccineCatalog vaccineCatalog;
    private HashMap<String, ArrayList<Site>> sitesOnLocation;
    private ArrayList<String> diseasetypes;
    private ArrayList<Boolean> confirms;
    
    public patientData() {
        patients = new HashMap<String, Patient>();
        clinics = new HashMap<String, Clinic>();
        encounters = new HashMap<Patient, Encounter>();
        sitesOnLocation = new HashMap<String, ArrayList<Site>>();
        vaccineCatalog = new VaccineCatalog();
        diseasetypes = new ArrayList<String>();
        confirms = new ArrayList<Boolean>();
        initializeDiseaseType();
        initializeConfirms();
        initializePatientData();
    }

    public void initializeDiseaseType(){
        diseasetypes.add("Common");
        diseasetypes.add("Infectious");
        diseasetypes.add("Hereditary");
    }

    public void initializeConfirms(){
        confirms.add(false);
        confirms.add(true);
    }

    public HashMap<String, Patient> getPatients() {
        return patients;
    }

    public HashMap<String, Clinic> getClinics() {
        return clinics;
    }

    public HashMap<Patient, Encounter> getEncounters() {
        return encounters;
    }

    public VaccineCatalog getVaccineCatalog() {
        return vaccineCatalog;
    }

    public HashMap<String, ArrayList<Site>> getSitesOnLocation() {
        return sitesOnLocation;
    }

    

    private void initializePatientData() {
        
        Faker faker = new Faker();
        // generate 10 location with random num (<= 3) of sites
        while (sitesOnLocation.size() < 10){
            String locationName = faker.address().state();
            Location location = new Location(locationName);
            Random random = new Random();
            int randomNumOfSite = random.nextInt(5);
            ArrayList<Site> sites = new ArrayList<Site>();
            for (int k = 1; k < randomNumOfSite; k++){
                Site site = new Site(location);
                String siteName = locationName + k;
                site.setName(siteName);
                sites.add(k-1, site);
            }
            sitesOnLocation.put(locationName, sites);
        }
        
        // generate 10 clinics with random hospitalNames, assign a random location to a clinic, store in hashmap locationname-clinic.
        while (clinics.size() < 10){
            String clinicName = faker.medical().hospitalName();
            Clinic  clinic = new Clinic(clinicName);
            ArrayList<String> addedlocation = new ArrayList<String>();
            String location = randomMapKey(sitesOnLocation);
            while(addedlocation.contains(location)){
                location = randomMapKey(sitesOnLocation);
            }
            addedlocation.add(location);
            clinic.getLocationList().newLocation(location);
            clinics.put(location, clinic); 
        }

        // generate 50 patients with random names, and assign a random clinic for each patient, store in hashmap name-patient.
        for (int i = 0; i < 50 ; i++){
            String patientName = faker.name().firstName();
            System.out.println(patientName);
            Random random = new Random();
            int patientAge = random.nextInt(121);
            Person person = new Person(patientName, patientAge);
            Clinic clinic = clinics.get(randomMapKey(clinics));
            Patient patient = clinic.addNewPatient(person);
            patients.put(patientName, patient);
        }

        // generate 200 encounters with random complaints, random vitalsigns, and assign a random patient for each encounter.
        for (int i = 0; i < 200; i++){
            String chiefComplaint = faker.medical().symptoms();
            Patient patient = patients.get(randomMapKey(patients));
            int age = patient.getPerson().getAge();
            Date date = faker.date().birthday(0, age);
            String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
            Encounter encounter = patient.newEncounter(chiefComplaint, dateString);
            Random random = new Random();
            encounter.addCurrentVitalRecord("RR", random.nextInt(41)+10);
            encounter.addCurrentVitalRecord("HR", random.nextInt(111)+50);
            encounter.addCurrentVitalRecord("BP", random.nextInt(91)+50);
            encounter.addCurrentVitalRecord("BG", random.nextInt(21)+80);
            encounter.newDiagnosis(diseasetypes.get(random.nextInt(3)), confirms.get(random.nextInt(2)));
            encounters.put(patient, encounter);
        }
        
        // add random number of vaccine to each patient.
        for (String name: patients.keySet()){
            Patient patient = patients.get(name);
            Random random = new Random();
            int vacNum = random.nextInt(8);
            //ArrayList<Vaccine> addedVac = new ArrayList<Vaccine>();
            for (int i = 0; i < vacNum; i++){
                // while(addedVac.contains(vaccine)){
                //     vacIndex = random.nextInt(7);
                //     vaccine = vaccineCatalog.getVaccines().get(vacIndex);
                // }
                int vacIndex = random.nextInt(7);
                Vaccine vaccine = vaccineCatalog.getVaccines().get(vacIndex);
                //addedVac.add(vaccine);
                int age = patient.getPerson().getAge();
                Date date = faker.date().birthday(0, age);
                String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
                Vaccination vaccination = new Vaccination(vaccine, dateString);
                patient.getVachistory().addVAccination(vaccination);
            }
        }


    }

    // random method for pick a key in HashMap.
    private String randomMapKey(HashMap<String, ?> hashmap){
        Set<String> keySet = hashmap.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]); 
        int randomIndex = new Random().nextInt(keyArray.length);
        String randomKey = keyArray[randomIndex];
        return randomKey;
    }

    // generate patient history data by name.
    public boolean searchPatientInfo(String patientName){
        Patient p = patients.get(patientName);
        if (p == null){
            System.out.println("\u001B[33mWelcom! It is your first time using our amazing ePatientChatBot!\u001B[0m");
            return false;
        }
        else{
            System.out.println("\u001B[33mWelcom Back!\u001B[0m");
            return true;
        }
    }

    public void printPatientReport(String patientName){
        Patient p = patients.get(patientName);
        if (p == null){
            System.out.println("\u26A0\u26A0\u26A0Not added succefully!");
        }
        else{
            System.out.println("\u001B[32mFollowing are your patient data in our ePatient System!\u001B[0m");
            p.printPatientInfo();
        }
    }

    public void addNewPatientToDatabase(String patientName, int age){
        Person person = new Person(patientName, age);
        Clinic clinic = clinics.get(randomMapKey(clinics));
        patients.put(patientName, new Patient(person, clinic));
        System.out.println("\uD83D\uDC4D\uD83D\uDC4D\uD83D\uDC4DYou are succefully added to our ePatient System! Keep going......");
    }

    public Patient findPatientByname(String name){
        Patient patientFind = patients.get(name);
        return patientFind;
    }

    public Encounter generateCurrEncounter(String patientName, String chiefComplain){
        Patient patient = findPatientByname(patientName);
        EncounterHistory eh = patient.getEncounterHistory();
        LocalDate currDate = LocalDate.now();
        String dateString = currDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Encounter currEncounter = eh.newEncounter(chiefComplain, dateString);
        encounters.put(patient,currEncounter);
        return currEncounter;
    }

    public void addVitalToEncounter (String patientName, String vitalName, int value){
        Patient patient  = findPatientByname(patientName);
        LocalDate currDate = LocalDate.now();
        String dateString = currDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Encounter currEncounter = patient.getEncounterHistory().getEncounterByDate(dateString);
        currEncounter.addCurrentVitalRecord(vitalName, value);
    }

    public void printAllClinicSummary(){
        // int locNum = sitesOnLocation.size();
        // System.out.println("Total location number: " + locNum);
        // System.out.println(sitesOnLocation.keySet());
        int clinicNum = clinics.size();
        System.out.println("Total clinic number: " + clinicNum);
        // System.out.println(clinics.keySet());
        for(String n: clinics.keySet()){
            Clinic clinic = clinics.get(n);
            System.out.println("This is a clinic located in " + n);
            clinic.printClinicSummary();
        }

    }

    public int analyzeVitalTrend(Patient patient, String vitalName){
        int trend = patient.getVitalTrend(vitalName);
        return trend;
    }

    

    
    
}
