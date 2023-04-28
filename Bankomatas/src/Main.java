import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static float accountBalance = 456.45f;
    private static ArrayList<BalanceHistory> balanceHistoryList;

    private static long itemId = 0;


    public static void main(String[] args) {
        System.out.println("Labas.");
        welcome();
    }

    private static void welcome(){
        System.out.println("Norint atlikti operaciją, reikia įvesti PIN...");
        if(pinCheck()){
            System.out.println("PIN OK");
            System.out.println();
            balanceHistoryList = new ArrayList<>();
            logBalanceHistory("PRADŽIOS LIKUTIS     ", 0, 0);
            menuOptions();

        }
        else{
            System.out.println("BLOGAS PIN");
            welcome();
        }
    }

    private static void logBalanceHistory(String itemType, double amount, double fee) {
        BalanceHistory balanceHistory = new BalanceHistory();
        balanceHistory.setItemId(++itemId);
        balanceHistory.setCurrentBalance(accountBalance);
        balanceHistory.setItemType(itemType);
        balanceHistory.setAmount(amount);
        balanceHistory.setFee(fee);
        balanceHistory.setDateTime(LocalDateTime.now());
        balanceHistoryList.add(balanceHistory);
    }

    private static boolean pinCheck(){
        Scanner sc = new Scanner(System.in);
        String enteredPin = sc.nextLine();
        if(enteredPin.equals(TextConstants.UNIVERSAL_PIN.getUniversalText())){
            return true;
        }
        else{
            return false;
        }
    }

    private static void menuOptions(){
        printMenu();
        Scanner scanner = new Scanner(System.in);
        int userChoice = scanner.nextInt();
        while(userChoice != 5) {
            runUserChoice(userChoice,scanner);
            printMenu();
            userChoice = scanner.nextInt();
        }
        scanner.close();
        exitAccount();
    }

    private static void printMenu(){
        System.out.println("Pasirinkite norimą paslaugą:");
        System.out.println("1. Išsiimti pinigus");
        System.out.println("2. Įnešti pinigus");
        System.out.println("3. Sąskaitos balansas");
        System.out.println("4. Spausdinti operacijų išrašą");
        System.out.println("5. Baigti darbą");
    }

    private static void runUserChoice(int userChoice, Scanner scanner) {
        switch (userChoice){
            case 1: takeOut(scanner);
                break;
            case 2: takeIn(scanner);
                break;
            case 3: showBalance();
                break;
            case 4: printOperationList();
                break;
            default: wrongChoice();
                break;
        }
    }

    private static void exitAccount() {
        System.out.println("Ačiū, kad naudojates mūsų paslaugomis.");
        System.out.println("Viso.");
        System.out.println();
    }

    private static void wrongChoice() {
        System.out.println("!!! Nerastas toks menu punktas !!!");
        System.out.println();
    }

    private static void printOperationList() {
        System.out.println(TextConstants.LINES.getUniversalText());
        System.out.println("|               Atliktų veiksmų ataskiata                |");
        System.out.println(TextConstants.LINES.getUniversalText());
        System.out.println(" " + balanceHistoryList.get(0).getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ": " + balanceHistoryList.get(0).getItemType() + " " + balanceHistoryList.get(0).getCurrentBalance() + " Eur ");
        System.out.println();
        for(int i = 1; i < balanceHistoryList.size(); i++){
            System.out.println(" " + balanceHistoryList.get(i).getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ": " + balanceHistoryList.get(i).getItemType() + " " + balanceHistoryList.get(i).getAmount() + " Eur ");
            if(balanceHistoryList.get(i).getFee() > 0){
                System.out.println(TextConstants.FEE_FEE.getUniversalText() +  balanceHistoryList.get(i).getFee() + " Eur ");
            }
        }
        System.out.println();
        System.out.println(" " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ": PABAIGOS LIKUTIS      " + accountBalance  + " Eur ");
        System.out.println(TextConstants.LINES.getUniversalText());
        System.out.println();
    }

    private static void showBalance() {
        System.out.println(">>> Likutis sąskaitoje: " + accountBalance + " Eur");
        System.out.println();
    }

    private static void takeIn(Scanner scanner) {
        System.out.println("Galima įdėti tik 20 Eur ir 50 Eur vertės kupiūras. ");
        System.out.print("Įveskite įdedamų banknotų vertes: ");
        String intLine = scanner.nextLine();
        if(intLine.equals("")){
            intLine = scanner.nextLine();
        }

        String[] intInString = intLine.split(" ");
        int banknoteLits[] = new int[intInString.length];
        for (int i = 0; i < intInString.length; i++) {
            banknoteLits[i] = Integer.parseInt(intInString[i]);
        }
        int sumOfMoney = 0;
        boolean valid = true;
        for(int i = 0; i < banknoteLits.length; i++){
            if(banknoteLits[i] != 20 && banknoteLits[i] != 50){
                System.out.println("!!! Įdėtas netinkamos vertės banknotas !!!");
                System.out.println();
                valid = false;
                takeIn(scanner);
            }
            else {
                sumOfMoney += banknoteLits[i];
            }
        }
        if(valid) {
            increseBalance(sumOfMoney);
        }
    }

    private static void increseBalance(int sumOfMoney) {
        accountBalance += sumOfMoney;
        logBalanceHistory(TextConstants.CASH_IN.getUniversalText(), sumOfMoney, 0);
        System.out.println(">>> Įnešta pinigų suma: " + sumOfMoney + " Eur");
        System.out.println();
    }

    private static void takeOut(Scanner scanner) {
        System.out.print("Įveskite norima išsiimti pinigų sumą: ");
        int moneyTakeOut = scanner.nextInt();
        if(accountBalance >= moneyTakeOut) {
            if ((((moneyTakeOut - 50) % 20 == 0)||(moneyTakeOut % 50 == 0)
                    || (moneyTakeOut % 20 == 0)) && (moneyTakeOut != 10)
                    && (moneyTakeOut != 30)){

                double fee = 0;
                if(moneyTakeOut > 100){
                    fee = moneyTakeOut * 0.01;
                }
                accountBalance -= moneyTakeOut;
                accountBalance -= fee;
                logBalanceHistory(TextConstants.CASH_OU.getUniversalText(), moneyTakeOut, fee);
                System.out.println(">>> Parošme paimti pinigus " + moneyTakeOut
                        + " Eur (paslaugos mokestis " + fee + " Eur).");
                System.out.println();
            } else {
                System.out.println("!!! Bankomate yra tik 20 Eur ir 50 Eur kupiūros !!!");
                System.out.println();
                takeOut(scanner);
            }
        }
        else{
            System.out.println("!!! Sąskaitos likutis nepakankamas !!!");
            System.out.println();
            takeOut(scanner);
        }
    }
}