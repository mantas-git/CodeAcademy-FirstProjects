package recordModels;
import enums.Categories;
import enums.Strings;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IncomeRecord extends Record {
    private int incomeCategory;
    private boolean transferedToTheBank;

    public IncomeRecord(int id, LocalDateTime processDate, int incomeCategory, double amount, boolean transferedToTheBank, String additionalInfo) {
        super(id, processDate, amount, additionalInfo);
        this.incomeCategory = incomeCategory;
        this.transferedToTheBank = transferedToTheBank;
    }

    public int getIncomeCategory() {
        return incomeCategory;
    }

    public void setIncomeCategory(int incomeCategory) {
        this.incomeCategory = incomeCategory;
    }

    public boolean isTransferredToTheBank() {
        return transferedToTheBank;
    }

    public void setTransferredToTheBank(boolean transferedToTheBank) {
        this.transferedToTheBank = transferedToTheBank;
    }

    @Override
    public String toString() {
        return String.format("Sukurtas %s įrašas Nr. %d:%n%s\t%s\t%.2f\t%s\t(%s)",
                Strings.INCOMINGS.getLabel(),
                id,
                processDate.format(DateTimeFormatter.ofPattern(Strings.DATETIMEFORMAT.getLabel())),
                Categories.values()[incomeCategory].getCategorie(),
                amount,
                additionalInfo,
                (transferedToTheBank ? "bankiniu pavedimu" : "atsiskaitymas grynais"));
    }

    public String toCsvString(){
        return String.format(Strings.CSVFORMAT.getLabel(),
       //         id,
                processDate,
                incomeCategory,
                amount,
                additionalInfo,
                transferedToTheBank);
    }
}
