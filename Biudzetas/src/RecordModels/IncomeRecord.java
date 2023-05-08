package RecordModels;

import java.time.LocalDateTime;

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

    public boolean isTransferedToTheBank() {
        return transferedToTheBank;
    }

    public void setTransferedToTheBank(boolean transferedToTheBank) {
        this.transferedToTheBank = transferedToTheBank;
    }

    @Override
    public String toString() {
        return "IncomeRecord{" + getId() +
                "category=" + incomeCategory +
                ", transferedToTheBank=" + transferedToTheBank +
                '}';
    }
}
