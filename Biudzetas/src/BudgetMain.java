import Enums.Categories;
import Enums.Strings;
import RecordModels.IncomeRecord;
import RecordModels.OutgoingRecord;
import RecordModels.Record;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class BudgetMain {
    private static Budget b1;
    private static Scanner scanner;
    private static int tableType = 0; //0 - bendras, 1 - pajamos, 2 - išlaidos
    private static DateTimeFormatter dateTimeFormatter;

    public static void main(String[] args) {
        System.out.println(Categories.values().length);
        b1 = new Budget();
        b1.fillData();
        dateTimeFormatter = b1.getDateTimeFormatter();
        scanner = new Scanner(System.in);
        String userChoice = "";
        while(!userChoice.equals("00")){
            printMainMenu();
            userChoice = scanner.nextLine();
            switch (userChoice){
                case "1":
                    b1.addIncomeStatement();
                    break;
                case "2":
                    b1.addOutgoingStatement();
                    break;
                case "3":
                    tableType = 0;
                    printTable();
                    break;
                case "4":
                    tableType = 1;
                    printTable();
                    break;
                case "5":
                    tableType = 2;
                    printTable();
                    break;
                case "6":
                    showBalance();
                    break;
                case "7":
                    saveDataToFile();
                    break;
                case "8":
                    loadDataToFile();
                    break;
                case "00":
                    break;
                default:
                    System.out.println("Įvestas neatpažintas simbolis");;
                    break;
            }
        }
        scanner.close();
    }

    private static void printMainMenu(){
        System.out.println("Pasirinkite norimą operaciją:\n" +
                "\t1. Įvesti pajamas\n" +
                "\t2. Įvesti išlaidas\n" +
                "\t3. Spausdinti visą išrašą\n" +
                "\t4. Spausdinti pajamų išrašą\n" +
                "\t5. Spausdinti išlaidų išrašą\n" +
                "\t6. Balansas\n" +
                "\t7. Įšsaugoti duomenis faile\n" +
                "\t8. Įkelti duomenis iš failo\n" +
                "\t00. Baigti darbą\n");
    }

    private static void showBalance() {
        double totalSum = 0;
        ArrayList<Record> records = getRecords();
        for (Record record : records) {
            totalSum += record.getAmount();
        }
        System.out.printf("%s Balansas: %.2f%n%n", LocalDateTime.now().format(dateTimeFormatter), totalSum);
    }

    private static void printTable() {
        double totalSum = 0;
        if(tableType == 0){
            printTableTop(String.format("%s ir %s", Strings.INCOME.getLabel(), Strings.OUTCOME.getLabel()), Strings.PAYMENTBOTH.getLabel());
            ArrayList<Record> records = getRecords();
            for (Record record : records) {
                printDataLine(record);
                totalSum += record.getAmount();
            }
        }
        if(tableType == 1){
            printTableTop(Strings.INCOME.getLabel(), Strings.PAYMENTBANK.getLabel());
            ArrayList<IncomeRecord> incomeRecords = getIncomeRecords();
            for (IncomeRecord incomeRecord : incomeRecords) {
                printDataLine(incomeRecord);
                totalSum += incomeRecord.getAmount();
            }
        }
        if(tableType == 2){
            printTableTop(Strings.OUTCOME.getLabel(), Strings.PAYMENTCARD.getLabel());
            ArrayList<OutgoingRecord> outgoingRecords = getOutgoingStatements();
            for (OutgoingRecord outgoingRecord : outgoingRecords) {
                printDataLine(outgoingRecord);
                totalSum += outgoingRecord.getAmount();
            }
        }
        printTableBottom(totalSum);
        runTableMenu();
    }

    private static void printTable(ArrayList<Record> records) {
        double totalSum = 0;
        if(tableType == 0){
            printTableTop(String.format("%s ir %s", Strings.INCOME.getLabel(), Strings.OUTCOME.getLabel()), Strings.PAYMENTBOTH.getLabel());
            for (Record record : records) {
                printDataLine(record);
                totalSum += record.getAmount();
            }
        }
        if(tableType == 1){
            printTableTop(Strings.INCOME.getLabel(), Strings.PAYMENTBANK.getLabel());
            for (Record record : records) {
                if(record instanceof IncomeRecord) {
                    printDataLine(record);
                    totalSum += record.getAmount();
                }
            }
        }
        if(tableType == 2){
            printTableTop(Strings.OUTCOME.getLabel(), Strings.PAYMENTCARD.getLabel());
            for (Record record : records) {
                if(record instanceof OutgoingRecord) {
                    printDataLine(record);
                    totalSum += record.getAmount();
                }
            }
        }
        printTableBottom(totalSum);
        printTableMenu();
    }

    private static int getGeneralCategory(Record record){
        int category = 0;
        if(record instanceof IncomeRecord){
            category = ((IncomeRecord) record).getIncomeCategory();
        }
        if(record instanceof OutgoingRecord){
            category = ((OutgoingRecord) record).getOutgoingCategory();
        }
        return category;
    }

    private static boolean getGeneralType(Record record){
        boolean trueFalse = false;
        if(record instanceof IncomeRecord){
            trueFalse = ((IncomeRecord) record).isTransferredToTheBank();
        }
        if(record instanceof OutgoingRecord){
            trueFalse = ((OutgoingRecord) record).isPaymentMethod();
        }
        return trueFalse;
    }

    private static void printTableTop(String name, String paymentMethod) {
        printVerticalLine();
        System.out.printf("| %64s %63s |%n", name, "");
        printVerticalLine();
        System.out.printf("| %5s |\t%-20s |\t%-20s |\t%-20s |\t%10s |\t%-30s |%n", "Nr", "Data ir laikas", "Kategorija", paymentMethod, "Suma", "Komentaras");
        printVerticalLine();
    }

    private static void printTableBottom(double totalSum) {
        printVerticalLine();
        System.out.printf("| %62s %29.2f %35s |%n", "Viso", totalSum, "");
        printVerticalLine();
    }

    private static void printDataLine(Record record){
        System.out.printf("| %5d |\t%-20s |\t%-20s |\t%-20s |\t%10.2f |\t%-30s |%n", record.getId(),
                record.getProcessDate().format(dateTimeFormatter), Categories.values()[getGeneralCategory(record)].getCategorie(),
                b1.booleanInLT(getGeneralType(record)), record.getAmount(), record.getAdditionalInfo());
    }

    private static void printVerticalLine(){
        //System.out.println(String.format("%-"+131+"s", "|").replaceAll("\\s(?=\\s+$|$)", "-")+"|");  //pasinagrineti, kaip veikia
        System.out.println(String.format("%-131s", "|").replaceAll("\\s(?=\\s+$|$)", "-")+"|");
    }

    private static void printSmallTable(Record record){
        printVerticalLine();
        printDataLine(record);
        printVerticalLine();
    }

    private static void runTableMenu() {
        printTableMenu();
        String userChoice = "";
        while(!userChoice.equals("00")){
            userChoice = scanner.nextLine();
            switch (userChoice){
                case "1":
                    setFilter();
                    break;
                case "2":
                    updateRecord();
                    break;
                case "3":
                    removeRecord();
                    break;
                case "00":
                    break;
                default:
                    System.out.println("Įvestas neatpažintas simbolis");;
                    break;
            }
        }
    }

    private static void printTableMenu(){
        System.out.print("Galimi veismai: " +
                "1. Filtruoti \t\t" +
                "2. Redaguoti \t\t" +
                "3. Ištrinti \t\t" +
                "4. Spausdinti \t\t" +
                "00. Grįžti į pagrindinį meniu " +
                "\n");

    }

    private static void removeRecord(){
        System.out.println("Įveskite kurį įrašą ištrinti (00 - jei atšaukti)");
        String userChoice = getTableMenuUserChoice();
        if(!userChoice.equals("00")) {
            Record record = b1.removeRecord(Integer.parseInt(userChoice));
            if(record != null){
                System.out.println("Ištrintas įrašas:");
                printSmallTable(record);
            }
            else{
                System.out.println(Strings.RECORDNOTFOUND.getLabel());
            }
        }
        else{
            System.out.println("Ištrinimas atšauktas");
        }
        printTableMenu();
    }

    private static void updateRecord() {
        System.out.println("Įveskite kurį įrašą norite redaguoti (00 - jei atšaukti)");
        String userChoice = getTableMenuUserChoice();
        if(!userChoice.equals("00")) {
            Record record =  b1.updateRecord(Integer.parseInt(userChoice));
            if(record != null){
                System.out.println("Įrašas atnaujintas");
                printSmallTable(record);
            }
            else{
                System.out.println(Strings.RECORDNOTFOUND.getLabel());
            }
        }
        else{
            System.out.println("Atnaujinimas atšauktas");
        }
        System.out.println();
        printTableMenu();
    }

    private static String getTableMenuUserChoice(){
        String userChoice = "";
        while (!userChoice.equals("00")) {
            userChoice = scanner.nextLine();
            try {
                Integer.parseInt(userChoice);
                break;
            } catch (NumberFormatException ex) {
                System.out.println("Nuskaitymo klaida. Įveskite sveiką skaičių.");
            }
        }
        return userChoice;
    }

    private static void setFilter(){
        System.out.println("Filtro kūrimas: ");
        ArrayList<Record> filteredRecords = getFilteredRecord();
        printTable(filteredRecords);
    }

    private static void saveDataToFile(){
        System.out.println("Save to file");
    }

    private static void loadDataToFile(){
        System.out.println("Load from file");
    }

    private static ArrayList<Record> getFilteredRecord() {
        return b1.getFilteredRecords();
    }

    private static ArrayList<Record> getRecords(){
        return b1.getRecords();
    }

    private static ArrayList<IncomeRecord> getIncomeRecords(){
        return b1.getIncomeRecords();
    }

    private static ArrayList<OutgoingRecord> getOutgoingStatements(){
        return b1.getOutgoingRecords();
    }

}