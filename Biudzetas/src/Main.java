import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Biudzetas b1;
    private static Scanner scanner;
    static boolean activeIncomeStatement = true;
    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {
        b1 = new Biudzetas();
        b1.fillData();
        scanner = new Scanner(System.in);
        String userChoice = "";
        while(!userChoice.equals("0")){
            printMainMenu();
            userChoice = scanner.next();

            switch (userChoice){
                case "1":
                    b1.addIncomeStatement();
                    break;
                case "2":
                    b1.addOutgoingStatement();
                    break;
                case "3":
                    //printIncomeStatement();
                    activeIncomeStatement = true;
                    printTable();
                    break;
                case "4":
                    //printOutgoingStatement();
                    activeIncomeStatement = false;
                    printTable();
                    break;
                case "5":
                    //System.out.println("Balansas");
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
            ArrayList<IncomeStatement> incomeStatements = getIncomeStatement();
            for (IncomeStatement incomeStatement : incomeStatements) {
                totalSum += incomeStatement.getAmount();
            }
            ArrayList<OutgoingStatement> outgoingStatements = getOutgoingStatements();
            for (OutgoingStatement outgoingStatement : outgoingStatements) {
                totalSum -= outgoingStatement.getAmount();
            }

        System.out.println("Balansas " + totalSum);
        System.out.printf("Balansas %.2f%n", totalSum);
    }

    private static void printTable() {
        double totalSum = 0;
        if(activeIncomeStatement){
            printTableTop("PAJAMOS");
            ArrayList<IncomeStatement> incomeStatements = getIncomeStatement();
//            for (IncomeStatement incomeStatement : incomeStatements) {
//                printDataLine(incomeStatement.getId(), incomeStatement.getProcessDate().format(dateTimeFormatter),
//                        incomeStatement.getCategory(), incomeStatement.isTransferedToTheBank(),
//                        incomeStatement.getAmount(), incomeStatement.getAdditionalInfo());
//                totalSum += incomeStatement.getAmount();
//            }
            for (int i = 0; i < incomeStatements.size(); i++){
                printDataLine(i+1, incomeStatements.get(i).getProcessDate(),
                        incomeStatements.get(i).getCategory(), incomeStatements.get(i).isTransferedToTheBank(),
                        incomeStatements.get(i).getAmount(), incomeStatements.get(i).getAdditionalInfo());
                totalSum += incomeStatements.get(i).getAmount();
            }
        }
        else{
            printTableTop("IŠLAIDOS");
            ArrayList<OutgoingStatement> outgoingStatements = getOutgoingStatements();
//            for (OutgoingStatement outgoingStatement : outgoingStatements) {
//                printDataLine(outgoingStatement.getId(), outgoingStatement.getProcessDate().format(dateTimeFormatter),
//                        outgoingStatement.getCategory(), outgoingStatement.isPaymentMethod(),
//                        (outgoingStatement.getAmount() * -1), outgoingStatement.getAdditionalInfo());
//                totalSum -= outgoingStatement.getAmount();
//            }
            for (int i = 0; i < outgoingStatements.size(); i++){
                printDataLine(i+1, outgoingStatements.get(i).getProcessDate(),
                        outgoingStatements.get(i).getCategory(), outgoingStatements.get(i).isPaymentMethod(),
                        outgoingStatements.get(i).getAmount(), outgoingStatements.get(i).getAdditionalInfo());
                totalSum += outgoingStatements.get(i).getAmount();
            }
        }
        printTableBottom(totalSum);
        runTableMenu();

    }

    private static void printTable(ArrayList<IncomeStatement> incomeStatements) {
        double totalSum = 0;
        if(activeIncomeStatement){
            printTableTop("PAJAMOS");
//            ArrayList<IncomeStatement> incomeStatements = getIncomeStatement();
//            for (IncomeStatement incomeStatement : incomeStatements) {
//                printDataLine(incomeStatement.getId(), incomeStatement.getProcessDate().format(dateTimeFormatter),
//                        incomeStatement.getCategory(), incomeStatement.isTransferedToTheBank(),
//                        incomeStatement.getAmount(), incomeStatement.getAdditionalInfo());
//                totalSum += incomeStatement.getAmount();
//            }
            for (int i = 0; i < incomeStatements.size(); i++){
                printDataLine(i+1, incomeStatements.get(i).getProcessDate(),
                        incomeStatements.get(i).getCategory(), incomeStatements.get(i).isTransferedToTheBank(),
                        incomeStatements.get(i).getAmount(), incomeStatements.get(i).getAdditionalInfo());
                totalSum += incomeStatements.get(i).getAmount();

            }
        }
        else{
            printTableTop("IŠLAIDOS");
            ArrayList<OutgoingStatement> outgoingStatements = getOutgoingStatements();
            for (OutgoingStatement outgoingStatement : outgoingStatements) {
                printDataLine(outgoingStatement.getId(), outgoingStatement.getProcessDate(),
                        outgoingStatement.getCategory(), outgoingStatement.isPaymentMethod(),
                        (outgoingStatement.getAmount() * -1), outgoingStatement.getAdditionalInfo());
                totalSum -= outgoingStatement.getAmount();
            }
        }
        printTableBottom(totalSum);
        runTableMenu();

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

    private static void printDataLine(int id, LocalDateTime processDate, int category, boolean throughBank, double amount, String additionalInfo){
        //System.out.println(String.format("| %5d |\t%-20s |\t%-20s |\t%-20s |\t%10.2f |\t%-30s |", id, processDate, getCategoryName(category), booleanInLT(throughBank), amount, additionalInfo));
        System.out.printf("| %5d |\t%-20s |\t%-20s |\t%-20s |\t%10.2f |\t%-30s |%n", id, processDate.format(dateTimeFormatter), getCategoryName(category), booleanInLT(throughBank), amount, additionalInfo);
    }

    private static void printVerticalLine(){
        //System.out.println(String.format("%-"+131+"s", "|").replaceAll("\\s(?=\\s+$|$)", "-")+"|");  //pasinagrineti, kaip veikia
        System.out.println(String.format("%-131s", "|").replaceAll("\\s(?=\\s+$|$)", "-")+"|");
    }

    private static String getCategoryName(int category){
        String name = "";
        switch(category){
            case 1:
                name = Categories.FOOD.getCategorie();
                break;
            case 2:
                name = Categories.TRANSPORT.getCategorie();
                break;
            case 3:
                name = Categories.CLOTHING.getCategorie();
                break;
            case 4:
                name = Categories.ENTERTAIMENT.getCategorie();
                break;
            case 5:
                name = Categories.HOUSEHOLD.getCategorie();
                break;
            case 6:
                name = Categories.HEALTH.getCategorie();
                break;
            case 7:
                name = Categories.SALES.getCategorie();
                break;
            case 8:
                name = Categories.MAIN_JOB.getCategorie();
                break;
            case 9:
                name = Categories.EXTRA_JOB.getCategorie();
                break;
            case 0:
                name = Categories.OTHER.getCategorie();
                break;
        }
        return name;
    }

    private static String booleanInLT(boolean trueFalse){
        if(trueFalse){
            return "TAIP";
        }
        else{
            return "NE";
        }
    }

    private static void runTableMenu() {
        printTableMenu();
        String userChoice = scanner.next();
        while(!userChoice.equals("00")){
            switch (userChoice){
                case "1":
                    //System.out.println("Filtruoti");
                    setFilter();
                    break;
                case "2":
                    System.out.println("Redaguoti"); //dar nera
                    break;
                case "3":
                    //System.out.println("Ištrinti");
                    removeStatement();
                    break;
                case "00":
                    break;
                default:
                    System.out.println("Įvestas neatpažintas simbolis");;
                    break;
            }
            userChoice = scanner.next();
        }
    }
    private static void removeStatement(){
        System.out.println("Įveskite kurį įrašą ištrinti (00 - jei atšaukti)");
        String userChoise = "";
        int deleteID = 0;
        while (!userChoise.equals("00")) {
            userChoise = scanner.nextLine();
            try {
                deleteID = Integer.parseInt(userChoise);
                if (deleteID > 0) {
                    break;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Nuskaitymo klaida. Įveskite sveiką skaičių.");
            }
        }
        if(!userChoise.equals("00")) {
            if (activeIncomeStatement) {
                b1.removeIncomeStatement(deleteID - 1);
            } else {
                b1.removeOutgoingStatement(deleteID - 1);
            }
            System.out.println("Įrašas ištrintas");
            printTable();
        }
        else{
            System.out.println("Atšaukta");
        }


    }

    private static void setFilter(){
        System.out.println("Įveskite norima filtruoti kategorią ");
        int filteredCategory;
        while(true) {
            try {
                filteredCategory = Integer.parseInt(scanner.nextLine());
                if(filteredCategory >= 0) {
                    break;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Nuskaitymo klaida. Įveskite sveiką skaičių.");
            }
        }
//        b1.getIncomeStatements(null, null, filteredCategory, false);
        printTable(b1.getIncomeStatements(null, null, filteredCategory, false));
    }

    private static void printMainMenu(){
        System.out.println("Pasirinkite norimą operaciją:");
        System.out.println("\t1. Įvesti pajamas");
        System.out.println("\t2. Įvesti išlaidas");
        System.out.println("\t3. Gauti pajamų irašą");
        System.out.println("\t4. Gauti išlaidų irašą");
        System.out.println("\t5. Balansas");
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

    private static ArrayList<IncomeStatement> getIncomeStatement(){
        return b1.getIncomeStatements();
    }

    private static ArrayList<OutgoingStatement> getOutgoingStatements(){
        return b1.getOutgoingStatements();
    }

}