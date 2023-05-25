package models;

import enums.Categories;
import enums.Strings;
import recordModels.IncomeRecord;
import recordModels.OutgoingRecord;
import recordModels.Record;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Budget {
    private ArrayList<Record> records = new ArrayList<>();
    private ArrayList<Record> filteredRecords = new ArrayList<>();
    private int counter = 1;
    private final Scanner scannerB = new Scanner(System.in);
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Strings.DATETIMEFORMAT.getLabel());

    public Budget() {
    }

    public int getCounter() {
        return counter;
    }

    public ArrayList<Record> getRecords() {
        return records;
    }

    public ArrayList<Record> getRecords(LocalDateTime dateFrom, LocalDateTime dateTill, Set<Integer> categories, int transactionType) {
        filteredRecords = new ArrayList<>();

        filteredRecords = filterByDate(dateFrom, dateTill);

        if(transactionType != 0) {
            filteredRecords = filterByTransaction(filteredRecords, transactionType);
        }

        if(categories.size() > 0) {
            filteredRecords = filterByCategories(filteredRecords, categories);
        }

        return filteredRecords;
    }

    public void setFilteredRecords(ArrayList<Record> filteredRecords) {
        this.filteredRecords = filteredRecords;
    }

    public ArrayList<Record> getFilteredRecords(){
        return filteredRecords;
    }

    private ArrayList<Record> filterByDate(LocalDateTime dateFrom, LocalDateTime dateTill) {
        ArrayList<Record> filteredRecords = new ArrayList<>();
        for (Record record : records) {
            if (record.getProcessDate().isAfter(dateFrom) && record.getProcessDate().isBefore(dateTill)) {
                filteredRecords.add(record);
            }
        }
        return filteredRecords;
    }

    private ArrayList<Record> filterByCategories(ArrayList<Record> records, Set<Integer> categories) {
        ArrayList<Record> filteredRecords = new ArrayList<>();
        for (Record record : records) {
            for (Integer category : categories) {
                if (record instanceof IncomeRecord && ((IncomeRecord) record).getIncomeCategory() == category) {
                    filteredRecords.add(record);
                }
                if (record instanceof OutgoingRecord && ((OutgoingRecord) record).getOutgoingCategory() == category) {
                    filteredRecords.add(record);
                }
            }
        }
        return filteredRecords;
    }

    private ArrayList<Record> filterByTransaction(ArrayList<Record> records, int transactionType){
        ArrayList<Record> filteredRecords = new ArrayList<>();
        if(transactionType == 1){
            for (Record record : records) {
                if (record instanceof IncomeRecord && ((IncomeRecord) record).isTransferredToTheBank()) {
                    filteredRecords.add(record);
                }
                if (record instanceof OutgoingRecord && ((OutgoingRecord) record).isPaymentMethod()) {
                    filteredRecords.add(record);
                }
            }
        }
        if(transactionType == 2){
            for (Record record : records) {
                if (record instanceof IncomeRecord && !((IncomeRecord) record).isTransferredToTheBank()) {
                    filteredRecords.add(record);
                }
                if (record instanceof OutgoingRecord && !((OutgoingRecord) record).isPaymentMethod()) {
                    filteredRecords.add(record);
                }
            }
        }
        return filteredRecords;
    }

    public ArrayList<IncomeRecord> getIncomeRecords() {
        ArrayList<IncomeRecord> incomeRecords = new ArrayList<>();
        for (Record record : records) {
            if (record instanceof IncomeRecord) {
                incomeRecords.add((IncomeRecord) record);
            }
        }
        return incomeRecords;
    }

    public ArrayList<OutgoingRecord> getOutgoingRecords() {
        ArrayList<OutgoingRecord> outgoingRecords = new ArrayList<>();
        for (Record record : records) {
            if (record instanceof OutgoingRecord) {
                outgoingRecords.add((OutgoingRecord) record);
            }
        }
        return outgoingRecords;
    }

    public DateTimeFormatter getDateTimeFormatter() {
        return dateTimeFormatter;
    }

    public void addIncomeStatement() {
        String type = Strings.INCOMINGS.getLabel();
        IncomeRecord incomeRecord = new IncomeRecord(counter, addDateTime(type, null), addCategory(type),
                addAmount(type), addTransactionType("Ar pinigai pervesti į banką? (T/N) "), addComment());
        addRecord(incomeRecord);
        System.out.println(incomeRecord);
        System.out.println();
    }

    public void addOutgoingStatement() {
        String type = Strings.OUTGOINGS.getLabel();
        OutgoingRecord outgoingRecord = new OutgoingRecord(counter, addDateTime(type, null), addCategory(type),
                addAmount(type) * -1, addTransactionType("Ar atsiskaityta grynais ? (T/N) "), addComment());
        System.out.println(outgoingRecord);
        addRecord(outgoingRecord);
    }

    public void addRecord(Record record){
        records.add(record);
        counter++;
    }

    public Record removeRecord(int deleteID) {
        Record record;
        Iterator<Record> recordIterator = records.iterator();
        while (recordIterator.hasNext()) {
            record = recordIterator.next();
            if(record.getId() == (deleteID)){
                recordIterator.remove();
                return record;
            }
        }
        return null;
    }

    public Record updateRecord(int updateID) {
        Record updatedRecord = null;
        for (Record record : records) {
            if (record.getId() == updateID) {
                updatedRecord = record;
            }
        }
        if(updatedRecord != null){
            updatedRecord = updateRecord(updatedRecord);
        }
        return updatedRecord;
    }

    public Record updateRecord(Record record){
        String editOrSkip = "\t[1] - redaguoti\t[enter] - praleisti";
        String type = ((record instanceof IncomeRecord) ? Strings.INCOMINGS.getLabel() : Strings.OUTGOINGS.getLabel());
        System.out.printf("Dabartinis įvestas laikas: %s %n" , record.getProcessDate().format(dateTimeFormatter));
        System.out.println(editOrSkip);
        String userChoise = scannerB.nextLine();
        if(userChoise.equals("1")){
            record.setProcessDate(addDateTime(type, null));
        }

        System.out.printf("Dabartinė %s kategorija %s %n", type, getRecordCategory(record));
        System.out.println(editOrSkip);
        userChoise = scannerB.nextLine();
        if(userChoise.equals("1")){
            int categoryId = addCategory(type);
            if ((record instanceof IncomeRecord)) {
                ((IncomeRecord) record).setIncomeCategory(categoryId);
            } else {
                ((OutgoingRecord) record).setOutgoingCategory(categoryId);
            }
        }

        System.out.printf("Dabar atsiskaityta kortele/bankiniu pavdeimu: %s %n" , getRecordMethod(record));
        System.out.println(editOrSkip);
        userChoise = scannerB.nextLine();
        if(userChoise.equals("1")){
            if ((record instanceof IncomeRecord)) {
                ((IncomeRecord) record).setTransferredToTheBank(addTransactionType("Įvesti naują reikšmę"));
            } else {
                ((OutgoingRecord) record).setPaymentMethod(addTransactionType("Įvesti naują reikšmę"));
            }
        }

        System.out.printf("Dabartinė suma: %s %n" , record.getAmount());
        System.out.println(editOrSkip);
        userChoise = scannerB.nextLine();
        if(userChoise.equals("1")){
            if(record instanceof IncomeRecord) {
                record.setAmount(addAmount(type));
            }
            else{
                record.setAmount(addAmount(type) * -1);
            }
        }

        System.out.printf("Dabartinis komentaras: %s %n" , record.getAdditionalInfo());
        System.out.println(editOrSkip);
        userChoise = scannerB.nextLine();
        if(userChoise.equals("1")){
            record.setAdditionalInfo(addComment());
        }

        return record;

    }

    private String getRecordCategory(Record record) {
        int i = (record instanceof IncomeRecord) ? ((IncomeRecord) record).getIncomeCategory() : ((OutgoingRecord) record).getOutgoingCategory();
        return Categories.values()[i].getCategorie();
    }

    private String getRecordMethod(Record record) {
        boolean i = (record instanceof IncomeRecord) ? ((IncomeRecord) record).isTransferredToTheBank() : ((OutgoingRecord) record).isPaymentMethod();
        return booleanInLT(i);
    }

    private LocalDateTime addDateTime(String type, String defaultTime) {
        String defaultTimeString = (defaultTime == null) ? "dabartinis" : defaultTime;
        System.out.printf("Įvesti %s datą ir laiką (praleisti, jei laikas %s)%n", type, defaultTimeString);
        LocalDateTime dateTime;
        while (true) {
            try {
                String string = scannerB.nextLine();
                if(string.equals("")){
                    if(defaultTime == null){
                        dateTime = LocalDateTime.now();
                    }
                    else {
                        dateTime = LocalDateTime.parse(defaultTime, dateTimeFormatter);;
                    }
                    break;
                }
                else{
                    dateTime = LocalDateTime.parse(string, dateTimeFormatter);
                }
                break;
            } catch (DateTimeParseException ex) {
                System.out.println("Nuskaitymo klaida. Įveskite operacijos laiką (yyyy-MM-dd HH:mm).");
            }
        }
        return dateTime;
    }

    private int addCategory(String type) {
        System.out.printf("Įvesti %s kategoriją: %n", type);
        printCategories();
        int category;
        while (true) {
            try {
                category = Integer.parseInt(scannerB.nextLine());
                if (category > 0 && category < Categories.values().length + 1) {
                    break;
                } else {
                    System.out.println("Tokia kategorija nerasta. Įveskite kategorijos numerį.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Nuskaitymo klaida. Įveskite kategorijos numerį.");
            }
        }
        return category - 1;
    }

    public void printCategories() {
        for (int i = 0; i < Categories.values().length; i++) {
            System.out.print((i + 1) + " - " + Categories.values()[i].getCategorie() + "; ");
        }
        System.out.println();
    }

    private double addAmount(String type) {
        System.out.printf("Įvesti %s sumą: %n", type);
        double sum;
        while (true) {
            try {
                sum = Double.parseDouble(scannerB.nextLine().replace(",","."));
                break;
            } catch (NumberFormatException ex) {
                System.out.println("Nuskaitymo klaida. Įveskite sumą.");
            }
        }
        return Math.abs(sum);
    }

    private boolean addTransactionType(String question) {
        System.out.println(question);
        boolean trueFalse;
        while (true) {
            String entered = scannerB.nextLine();
            if (entered.equalsIgnoreCase("T") || entered.equalsIgnoreCase("TAIP") || entered.equalsIgnoreCase("Y") || entered.equals("1")) {
                trueFalse = true;
                break;
            }
            if (entered.equalsIgnoreCase("N") || entered.equalsIgnoreCase("NE") || entered.equals("0")) {
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

    public void fillData() {
        System.out.println();

        records.add(new IncomeRecord(counter++, LocalDateTime.parse("2023-05-01 12:15", dateTimeFormatter), 6, 1000.00, true, "darbo uzmokestis"));
        records.add(new IncomeRecord(counter++, LocalDateTime.parse("2023-05-01 13:12", dateTimeFormatter), 8, 3.15, false, "uz lipdukus"));
        records.add(new IncomeRecord(counter++, LocalDateTime.parse("2023-05-02 18:00", dateTimeFormatter), 7, 50.00, false, "sukapotos malkos"));
        records.add(new IncomeRecord(counter++, LocalDateTime.parse("2023-05-03 08:03", dateTimeFormatter), 9, 20.00, true, "uz pavezima"));
        records.add(new IncomeRecord(counter++, LocalDateTime.parse("2023-05-03 12:15", dateTimeFormatter), 8, 5.5, true, "uz laida"));
        records.add(new IncomeRecord(counter++, LocalDateTime.parse("2023-05-03 17:00", dateTimeFormatter), 7, 25.00, false, "pakeiciau plokste"));
        System.out.println("IncoemStatments loaded...");

        records.add(new OutgoingRecord(counter++, LocalDateTime.parse("2023-05-01 12:30", dateTimeFormatter), 3, -4.50, true, "pietūs"));
        records.add(new OutgoingRecord(counter++, LocalDateTime.parse("2023-05-01 17:30", dateTimeFormatter), 0, -111.32, false, "maistas"));
        records.add(new OutgoingRecord(counter++, LocalDateTime.parse("2023-05-02 18:00", dateTimeFormatter), 1, -120.46, false, "automobilio remontas"));
        records.add(new OutgoingRecord(counter++, LocalDateTime.parse("2023-05-03 08:03", dateTimeFormatter), 5, -32.68, true, "vitaminiai"));
        records.add(new OutgoingRecord(counter++, LocalDateTime.parse("2023-05-03 12:15", dateTimeFormatter), 4, -25.80, true, "mikseris"));
        records.add(new OutgoingRecord(counter++, LocalDateTime.parse("2023-05-03 17:00", dateTimeFormatter), 2, -68.00, false, "batai"));
        System.out.println("OutgoingStatements loaded...");

        System.out.println();

    }

    public String booleanInLT(boolean trueFalse){
        return (trueFalse ? "TAIP" : "NE");
    }

    public ArrayList<Record> createFilteredRecords() {
        LocalDateTime dateFrom = addDateTime("filtro pradžios", "2000-01-01 00:00");
        LocalDateTime dateTill = addDateTime("filtro pabaigos", null);
        Set<Integer> categories = getCategoriesFilter();
        int paymentFilter = getPaymentFilter();
        return getRecords(dateFrom, dateTill, categories, paymentFilter);
    }

    private Set<Integer> getCategoriesFilter(){
        System.out.println("Kategorijos (atskirti tarpais): ");
        printCategories();
        String intLine = scannerB.nextLine();
        String[] intInString = intLine.split(" ");
        Set<Integer> categories = new HashSet<>();
        for (String s : intInString) {
            try {
                int categoryInt = Integer.parseInt(s);
                categories.add(categoryInt - 1);
            } catch (NumberFormatException ignored) {
            }
        }
        return categories;
    }

    private int getPaymentFilter() {
        System.out.println("Atsiskaitymas kortele/bakiniu pavedimu (T/N) ?");
        int paymentFilter;
        while (true) {
            String entered = scannerB.nextLine();
            if (entered.equalsIgnoreCase("T") || entered.equalsIgnoreCase("Y") || entered.equals("1")) {
                paymentFilter = 1;
                break;
            }
            if (entered.equalsIgnoreCase("N") || entered.equals("0")) {
                paymentFilter = 2;
                break;
            }
            if (entered.equalsIgnoreCase("")) {
                paymentFilter = 0;
                break;
            }
            System.out.println("Nuskaitymo klaida. Įveskite tinkamą atsakymą.");
        }
        return paymentFilter;
    }
}
