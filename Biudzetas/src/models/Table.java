package models;

import enums.Categories;
import enums.Strings;
import recordModels.IncomeRecord;
import recordModels.OutgoingRecord;
import recordModels.Record;

public abstract class Table {
    public static String getVerticalLine(){
        return (String.format("%-131s", "|").replaceAll(" ", "-") + "|\n");
    }

    public static String getTableNames(String name){
        return (String.format("| %64s %63s |%n", name, ""));
    }

    public static String getColumnNames(String paymentMethod){
        return (String.format("| %5s |\t%-20s |\t%-20s |\t%-20s |\t%10s |\t%-30s |%n",
                Strings.COLUMNNR.getLabel(),
                Strings.COLUMNDATEANDTIME.getLabel(),
                Strings.COLUMNCATEGORIE.getLabel(),
                paymentMethod,
                Strings.COLUMNAMOUNT.getLabel(),
                Strings.COLUMNCOMMENT.getLabel()));
    }

    public static String getDataLine(Budget budget, Record record){
        return (String.format("| %5d |\t%-20s |\t%-20s |\t%-20s |\t%10.2f |\t%-30s |%n", record.getId(),
                record.getProcessDate().format(budget.getDateTimeFormatter()), Categories.values()[getGeneralCategory(record)].getCategorie(),
                budget.booleanInLT(getGeneralType(record)), record.getAmount(), record.getAdditionalInfo()));
    }

    public static String getBottomLine(double totalSum){
        return (String.format("| %62s %29.2f %35s |%n", "Viso", totalSum, ""));
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
}
