// // // package ePatientSupportDatabase;
// // // import com.github.javafaker.Faker;
// // // public class test {
    

// // //     public static void main(String[] args) {
// // //         Faker faker = new Faker();

// // //         // // 生成随机的州缩写
// // //         // String stateAbbr = faker.address().stateAbbr();

// // //         // // 生成相应州的随机邮政编码
// // //         // String zipCode = faker.address().zipCodeByState(stateAbbr);

// // //         //String state = faker.address().state();
// // //     //     String zipCode = faker.address().zipCode();

// // //     //     System.out.println("State: " + state);
// // //     //     System.out.println("Zipcode: " + zipCode);
// // //     // }

// // //         // System.out.println("State abbreviation: " + stateAbbr);
// // //         // System.out.println("Zipcode: " + zipCode);
// // //         // 生成随机的州缩写
// // //         String stateAbbr = "TX";

// // //         // 生成相应州的随机邮政编码
// // //         // String zipCode = faker.numerify("#####-####");

// // //         // System.out.println("State abbreviation: " + stateAbbr);
// // //         // System.out.println("Zipcode: " + zipCode);

// // //         // String stateAbbr = "TX";

// // //         // // 生成相应州的随机邮政编码
// // //         // String zipCode = faker.address().zipCodeByState(stateAbbr);
// // //         // String randomZipCode = faker.numerify("#####-####");
// // //         int k = 1;
// // //         String locationName = "CA";
// // //         String siteName = locationName + k;
// // //         System.out.println(siteName);

// // //     }
// // // }
// // System.out.println("Please enter your basic vital sign values: RespiratoryRate ");
// // int inputRespiratoryRate = scanner.nextInt();
// // scanner.nextLine(); // 读取并丢弃回车符
// // boolean isRRNormal = vitalNormal.report(age, "RR", inputRespiratoryRate);
// // currEncounter.addNewVitals("RR", inputRespiratoryRate);
      
 
// // patein1.getEncounterHistory().getSingleVitalSignTrendByName（"HR"）；


// // public void getSingleVitalSignTrendByName(String vitalName) {
// //     // Find all encounters for the patient
    

// //     // Filter encounters based on whether they contain the given vital sign
// //     ArrayList<Encounter> relevantEncounters = new ArrayList<>();
// //     for (Encounter encounter : encounters) {
// //         if (encounter.containsVitalSign(vitalName)) {
// //             relevantEncounters.add(encounter);
// //         }
// //     }

//     // Sort relevant encounters by date (in ascending order)
//     Collections.sort(relevantEncounters, new Comparator<Encounter>() {
//         @Override
//         public int compare(Encounter e1, Encounter e2) {
//             return e1.getEncounterDate().compareTo(e2.getEncounterDate());
//         }
//     });

//     // Collect the vital sign data and dates
//     ArrayList<Integer> vitalData = new ArrayList<>();
//     ArrayList<Date> vitalDates = new ArrayList<>();
//     for (Encounter encounter : relevantEncounters) {
//         VitalSign vitalSign = encounter.getVitalSignByName(vitalName);
//         vitalData.add(vitalSign.getValue());
//         vitalDates.add(encounter.getEncounterDate());
//     }

//     // Create a line chart of the vital sign data over time
//     CategoryAxis xAxis = new CategoryAxis();
//     xAxis.setLabel("Date");
//     xAxis.setTickLabelRotation(90);
//     xAxis.setCategories(FXCollections.observableArrayList(vitalDates.stream().map(Date::toString).collect(Collectors.toList())));

//     NumberAxis yAxis = new NumberAxis();
//     yAxis.setLabel(vitalName);

//     LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);

//     XYChart.Series<String, Number> series = new XYChart.Series<>();
//     series.setName(vitalName);
//     for (int i = 0; i < vitalData.size(); i++) {
//         series.getData().add(new XYChart.Data<>(vitalDates.get(i).toString(), vitalData.get(i)));
//     }

//     lineChart.getData().add(series);

//     // Display the chart in a new window
//     Stage stage = new Stage();
//     Scene scene = new Scene(lineChart, 800, 600);
//     stage.setScene(scene);
//     stage.show();
// }
