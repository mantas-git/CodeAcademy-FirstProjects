import java.time.LocalDateTime;

public class IncomeStatement {
    private double amount;
    private LocalDateTime processDate;
    private String category;
    private String transferedToTheBank;
    private String additionalInfo;

    public IncomeStatement() {
    }

    public IncomeStatement(double amount, LocalDateTime processDate, String category, String transferedToTheBank, String additionalInfo) {
        this.amount = amount;
        this.processDate = processDate;
        this.category = category;
        this.transferedToTheBank = transferedToTheBank;
        this.additionalInfo = additionalInfo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getProcessDate() {
        return processDate;
    }

    public void setProcessDate(LocalDateTime processDate) {
        this.processDate = processDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTransferedToTheBank() {
        return transferedToTheBank;
    }

    public void setTransferedToTheBank(String transferedToTheBank) {
        this.transferedToTheBank = transferedToTheBank;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @Override
    public String toString() {
        return "IncomeStatement{" +
                "amount=" + amount +
                ", processDate=" + processDate +
                ", category='" + category + '\'' +
                ", transferedToTheBank='" + transferedToTheBank + '\'' +
                ", additionalInfo='" + additionalInfo + '\'' +
                '}';
    }
}
