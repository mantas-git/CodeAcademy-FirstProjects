package Enums;

public enum Strings {
    INCOME("PAJAMOS"),
    OUTCOME("IŠLAIDOS"),
    INCOMINGS("pajamų"),
    OUTGOINGS("išlaidų"),
    PAYMENTCARD("Kortele"),
    PAYMENTBANK("Pavedimu"),
    PAYMENTBOTH("Kortele/pavedimu"),
    RECORDNOTFOUND("Pagal įvestą numerį įrašas nerastas."),
    DATETIMEFORMAT("yyyy-MM-dd HH:mm");

    private String label;

    Strings(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
