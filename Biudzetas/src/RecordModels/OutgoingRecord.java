package RecordModels;

import java.time.LocalDateTime;

public class OutgoingRecord extends Record{
    private int outgoingCategory;
    private boolean paymentMethod;

    public OutgoingRecord(int id, LocalDateTime processDate, int outgoingCategory, double amount, boolean paymentMethod, String additionalInfo) {
        super(id, processDate, amount, additionalInfo);
        this.outgoingCategory = outgoingCategory;
        this.paymentMethod = paymentMethod;
    }

    public int getOutgoingCategory() {
        return outgoingCategory;
    }

    public void setOutgoingCategory(int outgoingCategory) {
        this.outgoingCategory = outgoingCategory;
    }

    public boolean isPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(boolean paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "OutgoingRecord{" +
                "category=" + outgoingCategory +
                ", paymentMethod=" + paymentMethod +
                '}';
    }
}
