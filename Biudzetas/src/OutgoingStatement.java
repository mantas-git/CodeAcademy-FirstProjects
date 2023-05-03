import java.time.LocalDateTime;

public class OutgoingStatement {
    private double amount;
    private LocalDateTime processDate;
    private int category;
    private boolean paymentMethod;
    private String additionalInfo;

    public OutgoingStatement() {
    }

    public OutgoingStatement(double amount, LocalDateTime processDate, int category, boolean paymentMethod, String additionalInfo) {
        this.amount = amount;
        this.processDate = processDate;
        this.category = category;
        this.paymentMethod = paymentMethod;
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

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public boolean getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(boolean paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @Override
    public String toString() {
        return "OutgoingStatement{" +
                "amount=" + amount +
                ", processDate=" + processDate +
                ", category='" + category + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", additionalInfo='" + additionalInfo + '\'' +
                '}';
    }
}
