package enums;

public enum Strings {
    INCOME("PAJAMOS"),
    OUTCOME("IŠLAIDOS"),
    INCOMINGS("pajamų"),
    OUTGOINGS("išlaidų"),
    PAYMENTCARD("Kortele"),
    PAYMENTBANK("Pavedimu"),
    PAYMENTBOTH("Kortele/pavedimu"),
    RECORDNOTFOUND("Pagal įvestą numerį įrašas nerastas."),
    DATETIMEFORMAT("yyyy-MM-dd HH:mm"),
    COLUMNNR("Nr"),
    COLUMNDATEANDTIME("Data ir laikas"),
    COLUMNCATEGORIE("Kategorija"),
    COLUMNAMOUNT("Suma"),
    COLUMNCOMMENT("Komentaras"),
    CSVFORMAT("%s;%s;%.2f;%s;%s%n"),
    DATAFILENAME("Biudzetas/BudgetData.csv"),
    PRINTFILENAME("Biudzetas/TablePrint.txt"),
    ENTEREDUNKNOWNSYMBOL("Įvestas neatpažintas simbolis");

    private String label;

    Strings(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
