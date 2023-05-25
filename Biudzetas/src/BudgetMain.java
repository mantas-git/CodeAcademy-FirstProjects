import enums.Strings;
import fileRedWrite.LoadFromFile;
import fileRedWrite.WriteToFile;
import models.Budget;
import models.Table;
import recordModels.IncomeRecord;
import recordModels.OutgoingRecord;
import recordModels.Record;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class BudgetMain {
    private static Budget b1;
    private static Scanner scanner;
    private static int tableType = 0; //0 - bendras, 1 - pajamos, 2 - išlaidos

    public static void main(String[] args) {
        b1 = new Budget();
        b1.fillData();
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
                    printTable(0);
                    break;
                case "4":
                    printTable(1);
                    break;
                case "5":
                    printTable(2);
                    break;
                case "6":
                    showBalance();
                    break;
                case "7":
                    saveDataToFile();
                    break;
                case "8":
                    loadDataFromFile();
                    break;
                case "00":
                    break;
                default:
                    System.out.println(Strings.ENTEREDUNKNOWNSYMBOL.getLabel());
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
        System.out.printf("%s Balansas: %.2f%n%n", LocalDateTime.now().format(getDateTimeFormatter()), totalSum);
    }

    private static void printTable(int tType) {
        tableType = tType;
        setFilteredRecords(null);
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

    private static void printTableTop(String name, String paymentMethod) {
        System.out.print(Table.getVerticalLine()
                + Table.getTableNames(name)
                + Table.getVerticalLine()
                + Table.getColumnNames(paymentMethod)
                + Table.getVerticalLine());
    }

    private static void printTableBottom(double totalSum) {
        System.out.print(Table.getVerticalLine()
                + Table.getBottomLine(totalSum)
                + Table.getVerticalLine());
    }

    private static void printDataLine(Record record){
        System.out.print(Table.getDataLine(b1, record));
    }

    private static void printSmallTable(Record record){
        System.out.print(Table.getVerticalLine()
                + Table.getDataLine(b1, record)
                + Table.getVerticalLine());
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
                case "4":
                    printDataTableToFile();
                    break;
                case "00":
                    break;
                default:
                    System.out.println(Strings.ENTEREDUNKNOWNSYMBOL.getLabel());
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
            Record record = removeRecord(Integer.parseInt(userChoice));
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
            Record record =  updateRecord(Integer.parseInt(userChoice));
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
        printTable(createFilteredRecord());
    }

    private static void saveDataToFile() {
        WriteToFile writeToFile = new WriteToFile();
        writeToFile.saveToFile(getRecords());
    }

    private static void loadDataFromFile(){
        LoadFromFile loadFromFile = new LoadFromFile(b1);
        loadFromFile.loadFromFile();
    }

    private static void printDataTableToFile(){
        WriteToFile writeToFile = new WriteToFile();
        if(getFilteredRecords() == null) {
            writeToFile.printToFile(b1, tableType, getRecords());
        }
        else{
            writeToFile.printToFile(b1, tableType, getFilteredRecords());
        }
    }

    private static ArrayList<Record> createFilteredRecord() {
        return b1.createFilteredRecords();
    }

    public static void setFilteredRecords(ArrayList<Record> records){
        b1.setFilteredRecords(records);
    }

    private static Record updateRecord(int i) {
        return b1.updateRecord(i);
    }

    private static Record removeRecord(int i) {
        return b1.removeRecord(i);
    }

    private static ArrayList<Record> getRecords(){
        return b1.getRecords();
    }

    private static ArrayList<Record> getFilteredRecords(){
        return b1.getFilteredRecords();
    }

    private static ArrayList<IncomeRecord> getIncomeRecords(){
        return b1.getIncomeRecords();
    }

    private static ArrayList<OutgoingRecord> getOutgoingStatements(){
        return b1.getOutgoingRecords();
    }

    private static DateTimeFormatter getDateTimeFormatter(){
        return b1.getDateTimeFormatter();
    }

}