package FileModels;

import Budget.Budget;
import Enums.Strings;
import RecordModels.IncomeRecord;
import RecordModels.OutgoingRecord;
import RecordModels.Record;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class LoadFromFile {
    private Budget budget;

    public LoadFromFile(Budget budget) {
        this.budget = budget;
    }

    public void loadFromFile(){
        try {
            FileReader fileReader = new FileReader(Strings.DATAFILENAME.getLabel());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String stringFromFile = bufferedReader.readLine();
            int lineNumber = 1;
            while (stringFromFile != null){
                Record record = crateRecord(stringFromFile, lineNumber);
                if(record != null){
                    budget.addRecord(record);
                }
                lineNumber++;
                stringFromFile = bufferedReader.readLine();
            }
            bufferedReader.close();
        }
        catch (IOException io){
            System.out.println("!!! Nepavyko nuskaityti iš failo !!!");
        }
        System.out.printf("Duomenys įkelti iš failo %s%n", Strings.DATAFILENAME.getLabel());
        
    }

    private Record crateRecord(String stringFromFile, int lineNumber) {
        Record record = null;
        try {
            String[] stringsInLine = stringFromFile.split(";");
            int stringId = 0;
            int id = budget.getCounter();
            LocalDateTime localDateTime = LocalDateTime.parse(stringsInLine[stringId++]);
            int categorie = Integer.parseInt(stringsInLine[stringId++]);
            double amount = Double.parseDouble(stringsInLine[stringId++].replace(",", "."));
            String comment = stringsInLine[stringId++];
            Boolean paymentMethod = (stringsInLine[stringId].equals("true") ? true : false);
            if (amount < 0) {
                record = new OutgoingRecord(id, localDateTime, categorie, amount, paymentMethod, comment);
            } else {
                record = new IncomeRecord(id, localDateTime, categorie, amount, paymentMethod, comment);
            }
        }
        catch (Exception e){
            System.out.println("Nepavyko nuskaityti eilutės nr.: " + lineNumber);
        }
        finally {
            return record;
        }
        
    }
}
