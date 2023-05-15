package FileModels;

import Enums.Strings;
import RecordModels.IncomeRecord;
import RecordModels.OutgoingRecord;
import RecordModels.Record;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteToFile {
    public void saveToFile(ArrayList<Record> records) {
        try {
            FileWriter fileWriter = new FileWriter(Strings.DATAFILENAME.getLabel());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//            bufferedWriter.write(String.format("%s;%s;%s;%s;%s;%s%n",
//                    Strings.COLUMNNR.getLabel(),
//                    Strings.COLUMNDATEANDTIME.getLabel(),
//                    Strings.COLUMNCATEGORIE.getLabel(),
//                    Strings.COLUMNAMOUNT.getLabel(),
//                    Strings.COLUMNCOMMENT.getLabel(),
//                    Strings.PAYMENTBOTH.getLabel()));

            for (Record record : records) {
                bufferedWriter.write(record instanceof IncomeRecord ? ((IncomeRecord) record).toCsvString() : ((OutgoingRecord) record).toCsvString());
                bufferedWriter.flush();
            }
            bufferedWriter.close();
            System.out.printf("Duomenys išsaugoti faile %s%n", Strings.DATAFILENAME.getLabel());
        }
        catch (IOException ioException){
            System.out.println("!!! Nepavyko išsaugoti į failą !!!");
        }
    }
}
