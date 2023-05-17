package fileRedWrite;

import enums.Strings;
import models.Budget;
import models.Table;
import recordModels.IncomeRecord;
import recordModels.OutgoingRecord;
import recordModels.Record;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteToFile {
    public void saveToFile(ArrayList<Record> records) {
        try {
            File file = new File(Strings.DATAFILENAME.getLabel());
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Record record : records) {
                bufferedWriter.write(record instanceof IncomeRecord ? ((IncomeRecord) record).toCsvString() : ((OutgoingRecord) record).toCsvString());
                bufferedWriter.flush();
            }
            bufferedWriter.close();
            System.out.printf("Duomenys išsaugoti faile %s%n", file.getAbsolutePath());
        }
        catch (IOException ioException){
            System.out.printf("!!! Nepavyko išsaugoti į failą (%s) !!!%n", Strings.DATAFILENAME.getLabel());
        }
    }

    public void printToFile(Budget budget, int tableType, ArrayList<Record> records){
        try {
            File file = new File(Strings.PRINTFILENAME.getLabel());
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            double totalSum = 0;
            if (tableType == 0) {
                printTableTop(String.format("%s ir %s", Strings.INCOME.getLabel(), Strings.OUTCOME.getLabel()), Strings.PAYMENTBOTH.getLabel(), bufferedWriter);
                for (Record record : records) {
                    printDataLine(record, bufferedWriter, budget);
                    totalSum += record.getAmount();
                }
            }
            if (tableType == 1) {
                printTableTop(Strings.INCOME.getLabel(), Strings.PAYMENTBANK.getLabel(), bufferedWriter);
                for (Record record : records) {
                    if (record instanceof IncomeRecord) {
                        printDataLine(record, bufferedWriter, budget);
                        totalSum += record.getAmount();
                    }
                }
            }
            if (tableType == 2) {
                printTableTop(Strings.OUTCOME.getLabel(), Strings.PAYMENTCARD.getLabel(), bufferedWriter);
                for (Record record : records) {
                    if (record instanceof OutgoingRecord) {
                        printDataLine(record, bufferedWriter, budget);
                        totalSum += record.getAmount();
                    }
                }
            }
            printTableBottom(totalSum, bufferedWriter);
            bufferedWriter.close();
            System.out.printf("Duomenys atspaudinti faile %s%n", file.getAbsolutePath());
        }
        catch (IOException ioException){
            System.out.printf("!!! Nepavyko atspausdinti į failą (%s) !!!%n", Strings.PRINTFILENAME.getLabel());
        }
    }

    private static void printTableTop(String name, String paymentMethod, BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write(Table.getVerticalLine()
                + Table.getTableNames(name)
                + Table.getVerticalLine()
                + Table.getColumnNames(paymentMethod)
                + Table.getVerticalLine());
    }

    private static void printTableBottom(double totalSum, BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write(Table.getVerticalLine()
                + Table.getBottomLine(totalSum)
                + Table.getVerticalLine());
    }

    private static void printDataLine(Record record, BufferedWriter bufferedWriter, Budget budget) throws IOException {
        bufferedWriter.write(Table.getDataLine(budget, record));
    }
}
