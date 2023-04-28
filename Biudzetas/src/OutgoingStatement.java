import java.time.LocalDateTime;

public class OutgoingStatement {
    private double amount;
    private LocalDateTime processDate;
    private String category;
    private String paymentMethod;
    private String additionalInfo;

    public OutgoingStatement() {
    }

    public OutgoingStatement(double amount, LocalDateTime processDate, String category, String paymentMethod, String additionalInfo) {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
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
