import RecordModels.IncomeRecord;
import RecordModels.OutgoingRecord;
import RecordModels.Record;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Biudzetas b1;
    private static Scanner scanner;
    private static int tableType = 0; //0 - bendras, 1 - pajamos, 2 - išlaidos
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {
        System.out.println(Categories.values().length);
        b1 = new Biudzetas();
        b1.fillData();
        scanner = new Scanner(System.in);
        String userChoice = "";
        while(!userChoice.equals("0")){
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
                case "0":
                    break;
                default:
                    System.out.println("Įvestas neatpažintas simbolis");;
                    break;
            }
        }
        scanner.close();
    }

    private static void showBalance() {
        double totalSum = 0;
        ArrayList<Record> records = getRecords();
        for (Record record : records) {
            totalSum += record.getAmount();
        }
        System.out.printf("Balansas %.2f%n", totalSum);
    }

    private static void printTable() {
        double totalSum = 0;
        if(tableType == 0){
            printTableTop("PAJAMOS ir IŠLAIDOS");
            ArrayList<Record> records = getRecords();
            for (Record record : records) {
                printDataLine(record);
                totalSum += record.getAmount();
            }
        }
        if(tableType == 1){
            printTableTop("PAJAMOS");
            ArrayList<IncomeRecord> incomeRecords = getIncomeRecords();
            for (IncomeRecord incomeRecord : incomeRecords) {
                printDataLine(incomeRecord);
                totalSum += incomeRecord.getAmount();
            }
        }
        if(tableType == 2){
            printTableTop("IŠLAIDOS");
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
            printTableTop("PAJAMOS ir IŠLAIDOS");
            for (Record record : records) {
                printDataLine(record);
                totalSum += record.getAmount();
            }
        }
        if(tableType == 1){
            printTableTop("PAJAMOS");
            for (Record record : records) {
                if(record instanceof IncomeRecord) {
                    printDataLine(record);
                    totalSum += record.getAmount();
                }
            }
        }
        if(tableType == 2){
            printTableTop("IŠLAIDOS");
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
            trueFalse = ((IncomeRecord) record).isTransferedToTheBank();
        }
        if(record instanceof OutgoingRecord){
            trueFalse = ((OutgoingRecord) record).isPaymentMethod();
        }
        return trueFalse;
    }

    private static void printTableTop(String name) {
        printVerticalLine();
        System.out.printf("| %64s %63s |%n", name, "");
        printVerticalLine();
        //System.out.println(String.format("| %5s |\t%-20s |\t%-20s |\t%-20s |\t%10s |\t%-30s |", "Nr", "Data", "Kategorija", "Kortele/pavedimu", "Suma", "Komentaras"));
        System.out.printf("| %5s |\t%-20s |\t%-20s |\t%-20s |\t%10s |\t%-30s |%n", "Nr", "Data ir laikas", "Kategorija", "Kortele/pavedimu", "Suma", "Komentaras");
        printVerticalLine();
    }

    private static void printTableBottom(double totalSum) {
        printVerticalLine();
        System.out.printf("| %62s %29.2f %35s |%n", "Viso", totalSum, "");
        printVerticalLine();
    }

    private static void printDataLine(Record record){
        //System.out.println(String.format("| %5d |\t%-20s |\t%-20s |\t%-20s |\t%10.2f |\t%-30s |", id, processDate, getCategoryName(category), booleanInLT(throughBank), amount, additionalInfo));
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

    private static void removeRecord(){
        System.out.println("Įveskite kurį įrašą ištrinti (00 - jei atšaukti)");
        String userChoise = getTableMenuUserChoice();
        if(!userChoise.equals("00")) {
            Record record = b1.removeRecord(Integer.parseInt(userChoise));
            if(record != null){
                System.out.println("Ištrintas įrašas:");
                printSmallTable(record);
            }
            else{
                System.out.println("Pagal įvestą numerį įrašas nerastas.");
            }
        }
        else{
            System.out.println("Ištrinimas atšauktas");
        }
        printTableMenu();
    }

    private static void updateRecord() {
        System.out.println("Įveskite kurį įrašą norite redaguoti (00 - jei atšaukti)");
        String userChoise = getTableMenuUserChoice();
        if(!userChoise.equals("00")) {
            Record record =  b1.updateRecord(Integer.parseInt(userChoise));
            if(record != null){
                System.out.println("Įrašas atnaujintas");
                printSmallTable(record);
            }
            else{
                System.out.println("Pagal įvestą numerį įrašas nerastas.");
            }
        }
        else{
            System.out.println("Atnaujinimas atšauktas");
        }
        System.out.println();
        printTableMenu();
    }

    private static String getTableMenuUserChoice(){
        String userChoise = "";
        while (!userChoise.equals("00")) {
            userChoise = scanner.nextLine();
            try {
                Integer.parseInt(userChoise);
                break;
            } catch (NumberFormatException ex) {
                System.out.println("Nuskaitymo klaida. Įveskite sveiką skaičių.");
            }
        }
        return userChoise;
    }

    private static void setFilter(){
        System.out.println("Filtro kūrimas: ");
        ArrayList<Record> filteredRecords = getFilteredRecord();
        printTable(filteredRecords);
    }

    private static ArrayList<Record> getFilteredRecord() {
        return b1.getFilteredRecords();
    }

    private static void printMainMenu(){
        System.out.println("Pasirinkite norimą operaciją:");
        System.out.println("\t1. Įvesti pajamas");
        System.out.println("\t2. Įvesti išlaidas");
        System.out.println("\t3. Spausdinti visą išrašą");
        System.out.println("\t4. Spausdinti pajamų išrašą");
        System.out.println("\t5. Spausdinti išlaidų išrašą");
        System.out.println("\t6. Balansas");
        System.out.println("\t0. Baigti darbą");
    }

    private static void printTableMenu(){
        System.out.print("Galimi veismai: ");
        System.out.print("1. Filtruoti \t\t");
        System.out.print("2. Redaguoti \t\t");
        System.out.print("3. Ištrinti \t\t");
        //System.out.print("4. Ištrinti įrašą ");
        //System.out.println("5. Balansas");
        System.out.println("00. Grįžti į pagrindinį meniu ");

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