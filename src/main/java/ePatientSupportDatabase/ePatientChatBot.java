package ePatientSupportDatabase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

// populate the chat process

import java.util.Scanner;

import com.github.javafaker.Faker;

import PatientManagement.Catalogs.VaccineCatalog;
import PatientManagement.Clinic.Clinic;
import PatientManagement.Patient.Patient;
import PatientManagement.Patient.ClinicalHistory.Vaccine;
import PatientManagement.Patient.Encounters.Encounter;
import PatientManagement.Patient.Orders.VaccinationOrder;

public class ePatientChatBot {

    private patientData patientData;
    private VitalSignRanges vitalNormal;
    private VaccineCatalog vaccineCatalog;
    

    public ePatientChatBot() {
        patientData = new patientData();
        vitalNormal = new VitalSignRanges();
        vaccineCatalog = new VaccineCatalog();
    }

    public void run(){
        System.out.println("=============================Welcome to your ePatientChatBot!=============================");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your first name: ");
        String name = scanner.nextLine();
        int age = -1;
        boolean isOld = patientData.searchPatientInfo(name);
        if (!isOld) {
            System.out.println("You the new patient, let us build your profile。。。");
            System.out.println("Please enter your age: ");
            age = scanner.nextInt();
            patientData.addNewPatientToDatabase(name, age); 
        }
        else {
            age = patientData.findPatientByname(name).getPerson().getAge();
            System.out.println("Do you want to print your current personal report in our ePatient System: y/n ");
            String answer = scanner.nextLine();
            scanner.nextLine();
            if (answer.equals("y")) patientData.printPatientReport(name);
        }

        System.out.println("\uD83D\uDE01\uD83D\uDE01\uD83D\uDE01Now is your new Encounter : ");
        Patient currPatient = patientData.findPatientByname(name);
        ArrayList<Vaccine> vaccines = vaccineCatalog.getVaccines();
        LocalDate currDate = LocalDate.now();
        String dateString = currDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.println("\u001B[34mPlease enter your chief complain: ");
        String chiefComplain = scanner.nextLine();
        scanner.nextLine();
        Encounter currEncounter = patientData.generateCurrEncounter(name, chiefComplain);
       
        System.out.println("Please enter your basic vital sign values: RespiratoryRate ");
        int inputRespiratoryRate = scanner.nextInt();
        scanner.nextLine();
        boolean isRRNormal = vitalNormal.report(age, "RR", inputRespiratoryRate);
        currEncounter.addCurrentVitalRecord("RR", inputRespiratoryRate);
        
        System.out.println("please enter your HeartRate ");
        int inputHeartRate = scanner.nextInt();
        scanner.nextLine();
        boolean isHRNormal = vitalNormal.report(age, "HR", inputHeartRate);
        currEncounter.addCurrentVitalRecord("HR", inputHeartRate);

        System.out.println("please enter your Systolic Blood pressure");
        int inputSystolicBlood = scanner.nextInt();
        scanner.nextLine();
        boolean isBPNormal = vitalNormal.report(age, "BP", inputSystolicBlood);
        currEncounter.addCurrentVitalRecord("BP", inputSystolicBlood);

        System.out.println("please enter your Fasting Blood Glucose value:");
        int inputBloodGlucose = scanner.nextInt();
        scanner.nextLine();
        boolean isBGNormal = vitalNormal.report(age, "BG", inputBloodGlucose);
        currEncounter.addCurrentVitalRecord("BG", inputBloodGlucose);




        // check your vital signs trending and tell you about your health condition.
        System.out.println("\u001B[31mNow, do you want to check your vital signs trending? y/n\u001B[0m");
        String checkOrNot = scanner.nextLine();
        if(checkOrNot.equals("y")){
            for (String n : vitalNormal.getVitalNames()){
                int trend = patientData.analyzeVitalTrend(currPatient, n);
                String anaResult = vitalNormal.getVitalAnalysis().get(n).get(trend);
                System.out.println("According to your " + n + "history trend analisis" + anaResult);
            }
        }
                
        




        








        System.out.println("\u001B[31mNow is vaccination time!");
        System.out.println("\u001B[32mDo you want to get vaccination this time: y/n ");
        String vacOrNot = scanner.nextLine();
        
        if (vacOrNot.equals("y")){
            for (Vaccine v: vaccines){
                String vacName = v.getName();
                int index = vaccines.indexOf(v)+1;
                System.out.println(index + "." + vacName);
            }
            System.out.println("\u001B[33mWhich vaccine do you want, please enter the number: ");
            int vacType = scanner.nextInt();
            scanner.nextLine();
            Vaccine targetVac = vaccines.get(vacType-1);
            VaccinationOrder vo = new VaccinationOrder(currPatient,targetVac,dateString);
            System.out.println("\uD83D\uDC4D\uD83D\uDC4D\uD83D\uDC4DCongratulations! You are successfully vaccined!");
        }
        else{
            System.out.println("\uD83C\uDF39\uD83C\uDF39\uD83C\uDF39It is your personal choice whether or not to get vaccinated,\nbut it is always a good idea to make an informed decision based on reliable information and advice from healthcare professionals.");
        }
        
        
        

            
        
        
        




        System.out.println("Do you want to print your personal final report: y/n ");
        String printOrNot = scanner.nextLine();
        scanner.nextLine();
        if (printOrNot.equals("y")) patientData.printPatientReport(name);
        
        
        
        scanner.close();
        
    }

    public void reportLocationSumamry(){
        Faker faker = new Faker();
        String targetLocation = faker.address().state();
        Clinic clinic = patientData.getClinics().get(targetLocation);
        while (clinic == null){
            targetLocation = faker.address().state();
            clinic = patientData.getClinics().get(targetLocation);
        }
        System.out.println("This is a clinic located in " + targetLocation);
        clinic.printClinicSummary();
    }

    public void reportAllClinicSummary(){
        patientData.printAllClinicSummary();
    }

    
    
    
    // public void old() {
        
    //     while (true) {
    //         // prompt the user for input
    //         System.out.println("1. How are you feeling today? ");
    //         // System.out.println("2. Why am I not feeling well?");
    //         // System.out.println("3. What should I do about it?");
    //         // System.out.println("4. Exit");
    //         // System.out.print("Enter your choice (1-4): ");
    //         String input = scanner.nextLine();

    //         // analyze the user input and respond accordingly
    //         if (input.equals("1")) {
    //             System.out.println("1. not feeling well 2. I am feeling good ");
    //             String input1 = scanner.nextLine();
    //             if(input1.equals("1")){ 
    //                 System.out.println("please enter your RespiratoryRate");
    //                 int inputRespiratoryRate =  Integer.parseInt(scanner.nextLine());
                  
                  
    //                 System.out.println("please enter your HeartRate ");
    //                 int inputHeartRate =  Integer.parseInt(scanner.nextLine());
    //                 System.out.println("please enter your Systolic Blood ");
    //                 int inputSystolicBlood =  Integer.parseInt(scanner.nextLine());

    //                 System.out.println("please enter your weight in kilos ");
    //                 int inputWeight =  Integer.parseInt(scanner.nextLine());





    //             }
    //             else 
    //                 System.out.println(" have a nice day ");
    //                 return;


    //             System.out.println("ePatient: I am not feeling well.");
    //         } else if (input.equals("2")) {
    //             System.out.println("ePatient: Your blood pressure is " + bloodPressure + " and it has been rising over the last few months. You also continue to be pre-diabetic, which is not good. You need to take some action to address these issues. Will you?");
    //         } else if (input.equals("3")) {
    //             System.out.println("1. Schedule an appointment with your doctor.");
    //             System.out.println("2. Try to eat a healthy diet and get regular exercise.");
    //             System.out.print("Enter your choice (1-2): ");
    //             input = scanner.nextLine();
    //             if (input.equals("1")) {
    //                 System.out.println("ePatient: You should schedule an appointment with your doctor to discuss your pre-diabetes and high blood pressure.");
    //             } else if (input.equals("2")) {
    //                 System.out.println("ePatient: You should try to eat a healthy diet and get regular exercise to help manage your pre-diabetes and high blood pressure.");
    //             } else {
    //                 System.out.println("ePatient: I'm sorry, I didn't understand. Please try again.");
    //             }
    //         } else if (input.equals("4")) {
    //             System.out.println("ePatient: Goodbye!");
    //             break;
    //         } else {
    //             System.out.println("ePatient: I'm sorry, I didn't understand. Please try again.");
    //         }
    //     }
    //     scanner.close();
    // }
}
