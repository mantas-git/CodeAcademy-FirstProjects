package recordModels;

import enums.Categories;
import enums.Strings;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        return String.format("Sukurtas %s įrašas Nr. %d:%n%s\t%s\t%.2f\t%s\t(%s)",
                Strings.OUTGOINGS.getLabel(),
                id,
                processDate.format(DateTimeFormatter.ofPattern(Strings.DATETIMEFORMAT.getLabel())),
                Categories.values()[outgoingCategory].getCategorie(),
                amount,
                additionalInfo,
                (paymentMethod ? "mokėta kortele" : "atsiskaita grynais"));
    }

    public String toCsvString(){
        return String.format(Strings.CSVFORMAT.getLabel(),
        //        id,
                processDate,
                outgoingCategory,
                amount,
                additionalInfo,
                paymentMethod);
    }
}
