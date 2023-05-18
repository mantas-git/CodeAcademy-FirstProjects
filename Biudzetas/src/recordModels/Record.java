package recordModels;

import java.time.LocalDateTime;

public class Record {
    protected int id;
    protected LocalDateTime processDate;
    protected double amount;
    protected String additionalInfo;

    public Record(int id, LocalDateTime processDate, double amount, String additionalInfo) {
        this.id = id;
        this.processDate = processDate;
        this.amount = amount;
        this.additionalInfo = additionalInfo;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getProcessDate() {
        return processDate;
    }

    public void setProcessDate(LocalDateTime processDate) {
        this.processDate = processDate;
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
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
