import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Biudzetas {
    private ArrayList<IncomeStatement> incomeStatements = new ArrayList<>();
    int incomeStCounter = 1;
    private ArrayList<OutgoingStatement> outgoingStatements = new ArrayList<>();
    int outgoingStCounter = 1;

    Scanner scannerB = new Scanner(System.in);

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Biudzetas() {
    }

    public ArrayList<IncomeStatement> getIncomeStatements() {
        return incomeStatements;
    }

    public ArrayList<IncomeStatement> getIncomeStatements(LocalDateTime dateFrom, LocalDateTime dateTill, int category, boolean transactionType) {
        ArrayList<IncomeStatement> filteredIncomeStatements = new ArrayList<>();
        for(int i = 0; i < incomeStatements.size(); i++){
            if(incomeStatements.get(i).getCategory() == category){
                filteredIncomeStatements.add(incomeStatements.get(i));
            }
        }
        return filteredIncomeStatements;
    }

    public void setIncomeStatements(ArrayList<IncomeStatement> incomeStatements) {
        this.incomeStatements = incomeStatements;
    }

    public ArrayList<OutgoingStatement> getOutgoingStatements() {
        return outgoingStatements;
    }

    public ArrayList<OutgoingStatement> getOutgoingStatements(LocalDateTime dateFrom, LocalDateTime dateTill, int category, boolean transactionType) {
        return outgoingStatements;
    }

    public void setOutgoingStatements(ArrayList<OutgoingStatement> outgoingStatements) {
        this.outgoingStatements = outgoingStatements;
    }

    public int getIncomeStCounter() {
        return incomeStCounter;
    }

    public int getOutgoingStCounter() {
        return outgoingStCounter;
    }

    public void addIncomeStatement(){
        IncomeStatement incomeStatement = new IncomeStatement();
        String type = "pajamų";
        incomeStatement.setId(incomeStCounter);
        incomeStatement.setProcessDate(addDateTime(type));
        incomeStatement.setCategory(addCategory(type));
        incomeStatement.setAmount(addAmount(type));
        System.out.println("Ar pinigai pervesti į banką? (T/N) ");
        incomeStatement.setTransferedToTheBank(addTransactionType());
        incomeStatement.setAdditionalInfo(addComment());
        //incomeStatements[incomeStCounter] = incomeStatement;
        incomeStatements.add(incomeStatement);
        incomeStCounter++;
        System.out.println();
    }

    public void removeIncomeStatement(int deleteID){
        incomeStatements.remove(deleteID);
    }

    public void removeOutgoingStatement(int deleteID){
        outgoingStatements.remove(deleteID);
    }

    public void addOutgoingStatement(){
        OutgoingStatement outgoingStatement = new OutgoingStatement();
        String type = "išlaidų";
        outgoingStatement.setId(outgoingStCounter);
        outgoingStatement.setProcessDate(addDateTime(type));
        outgoingStatement.setCategory(addCategory(type));
        outgoingStatement.setAmount(addAmount(type));
        System.out.println("Ar atsiskaityta grynais ? (T/N) ");
        outgoingStatement.setPaymentMethod(addTransactionType());
        outgoingStatement.setAdditionalInfo(addComment());
        //outgoingStatements[outgoingStCounter] = outgoingStatement;
        outgoingStatements.add(outgoingStatement);
        outgoingStCounter++;
    }

    private LocalDateTime addDateTime(String type) {
        System.out.printf("Įvesti %s datą ir laiką%n", type);
        LocalDateTime dateTime;
        while(true) {
            try {
                dateTime = LocalDateTime.parse(scannerB.nextLine(), dateTimeFormatter);
                break;
            } catch (DateTimeParseException ex) {
                System.out.println("Nuskaitymo klaida. Įveskite operacijos laiką (yyyy-MM-dd HH:mm).");
            }
        }
        return dateTime;
    }

    private int addCategory(String type) {
        System.out.printf("Įvesti %s kategoriją: %n", type);
        System.out.println("1 - " + Categories.FOOD.getCategorie()
                + "; 2 - " + Categories.TRANSPORT.getCategorie()
                + "; 3 - " + Categories.CLOTHING.getCategorie()
                + "; 4 - " + Categories.ENTERTAIMENT.getCategorie()
                + "; 5 - " + Categories.HOUSEHOLD.getCategorie()
                + "; 6 - " + Categories.HEALTH.getCategorie()
                + "; 7 - " + Categories.SALES.getCategorie()
                + "; 8 - " + Categories.MAIN_JOB.getCategorie()
                + "; 9 - " + Categories.EXTRA_JOB.getCategorie()
                + "; 0 - " + Categories.OTHER.getCategorie()
                + ".");
        int category;
        while(true) {
            try {
                category = Integer.parseInt(scannerB.nextLine());
                if(category >= 0 && category < 10) {
                    break;
                }
                else{
                    System.out.println("Tokia kategorija nerasta. Įveskite kategorijos numerį.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Nuskaitymo klaida. Įveskite kategorijos numerį.");
            }
        }
        return category;
    }

    private double addAmount(String type) {
        System.out.printf("Įvesti %s sumą: %n", type);
        double sum;
        while(true) {
            try {
                sum = Double.parseDouble(scannerB.nextLine());
                break;
            } catch (NumberFormatException ex) {
                System.out.println("Nuskaitymo klaida. Įveskite sumą, centus atskiriant tašku (.) .");
            }
        }
        return sum;
    }

    private boolean addTransactionType() {
        boolean trueFalse;
        while(true){
            String entered = scannerB.nextLine();
            if(entered.equalsIgnoreCase("T") || entered.equalsIgnoreCase("Y") || entered.equals("1") ){
                trueFalse = true;
                break;
            }
            if(entered.equalsIgnoreCase("N") || entered.equals("0") ){
                trueFalse = false;
                break;
            }
            System.out.println("Nuskaitymo klaida. Įveskite tinkamą atsakymą.");
        }
        return trueFalse;
    }

    private String addComment() {
        System.out.println("Papildoma infomracija: ");
        return scannerB.nextLine();
    }

    public void fillData(){
        incomeStatements.add(new IncomeStatement(incomeStCounter, LocalDateTime.parse("2023-05-01 12:15", dateTimeFormatter), 8, true, 1000.00,"darbo uzmokestis"));
        incomeStCounter++;
        incomeStatements.add(new IncomeStatement(incomeStCounter, LocalDateTime.parse("2023-05-01 13:12", dateTimeFormatter), 7, false, 3.15,"uz lipdukus"));
        incomeStCounter++;
        incomeStatements.add(new IncomeStatement(incomeStCounter, LocalDateTime.parse("2023-05-02 18:00", dateTimeFormatter), 9, false, 50.00,"sukapotos malkos"));
        incomeStCounter++;
        incomeStatements.add(new IncomeStatement(incomeStCounter, LocalDateTime.parse("2023-05-03 08:03", dateTimeFormatter), 0, true, 20.00,"uz pavezima"));
        incomeStCounter++;
        incomeStatements.add(new IncomeStatement(incomeStCounter, LocalDateTime.parse("2023-05-03 12:15", dateTimeFormatter), 7, true, 5.5,"uz laida"));
        incomeStCounter++;
        incomeStatements.add(new IncomeStatement(incomeStCounter, LocalDateTime.parse("2023-05-03 17:00", dateTimeFormatter), 9, false, 25.00,"pakeiciau plokste"));
        incomeStCounter++;
        System.out.println("IncoemStatments loaded...");

        outgoingStatements.add(new OutgoingStatement(outgoingStCounter, LocalDateTime.parse("2023-05-01 12:30", dateTimeFormatter), 4, true, 4.50, "pietūs"));
        outgoingStCounter++;
        outgoingStatements.add(new OutgoingStatement(outgoingStCounter, LocalDateTime.parse("2023-05-01 17:30", dateTimeFormatter), 1, false, 111.32, "maistas"));
        outgoingStCounter++;
        outgoingStatements.add(new OutgoingStatement(outgoingStCounter, LocalDateTime.parse("2023-05-02 18:00", dateTimeFormatter), 2, false, 120.46, "automobilio remontas"));
        outgoingStCounter++;
        outgoingStatements.add(new OutgoingStatement(outgoingStCounter, LocalDateTime.parse("2023-05-03 08:03", dateTimeFormatter), 6, true, 32.68, "vitaminiai"));
        outgoingStCounter++;
        outgoingStatements.add(new OutgoingStatement(outgoingStCounter, LocalDateTime.parse("2023-05-03 12:15", dateTimeFormatter), 5, true, 25.80, "mikseris"));
        outgoingStCounter++;
        outgoingStatements.add(new OutgoingStatement(outgoingStCounter, LocalDateTime.parse("2023-05-03 17:00", dateTimeFormatter), 3, false, 68.00, "batai"));
        outgoingStCounter++;
        System.out.println("OutgoingStatements loaded...");

    }
}
