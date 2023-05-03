import java.time.LocalDateTime;

public class IncomeStatement {
    private int id = 1;
    private LocalDateTime processDate;
    private int category;
    private boolean transferedToTheBank;
    private double amount;
    private String additionalInfo;

    public IncomeStatement() {
    }

    public IncomeStatement(int id, LocalDateTime processDate, int category, boolean transferedToTheBank, double amount, String additionalInfo) {
        this.id = id;
        this.processDate = processDate;
        this.category = category;
        this.transferedToTheBank = transferedToTheBank;
        this.amount = amount;
        this.additionalInfo = additionalInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getProcessDate() {
        return processDate;
    }

    public void setProcessDate(LocalDateTime processDate) {
        this.processDate = processDate;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public boolean isTransferedToTheBank() {
        return transferedToTheBank;
    }

    public void setTransferedToTheBank(boolean transferedToTheBank) {
        this.transferedToTheBank = transferedToTheBank;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @Override
    public String toString() {
        return  "IncomeStatement{" +
                "amount=" + amount +
                ", processDate=" + processDate +
                ", category='" + category + '\'' +
                ", transferedToTheBank='" + transferedToTheBank + '\'' +
                ", additionalInfo='" + additionalInfo + '\'' +
                '}';
    }

}
