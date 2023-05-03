import java.time.LocalDateTime;

public class OutgoingStatement {
    private int id = 1;
    private LocalDateTime processDate;
    private int category;
    private boolean paymentMethod;
    private double amount;
    private String additionalInfo;

    public OutgoingStatement() {
    }

    public OutgoingStatement(int id, LocalDateTime processDate, int category, boolean paymentMethod, double amount, String additionalInfo) {
        this.id = id;
        this.processDate = processDate;
        this.category = category;
        this.paymentMethod = paymentMethod;
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

    public boolean isPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(boolean paymentMethod) {
        this.paymentMethod = paymentMethod;
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

        return  "OutgoingStatement{" +
                "amount=" + amount +
                ", processDate=" + processDate +
                ", category='" + category + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", additionalInfo='" + additionalInfo + '\'' +
                '}';
    }
}
