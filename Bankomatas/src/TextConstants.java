public enum TextConstants {
    UNIVERSAL_PIN("1111"),
    CASH_IN("Sąskaitos papildymas "),
    CASH_OU("Pinigų išėmimas      "),
    FEE_FEE("                  Paslaugos mokestis "),
    LINES("----------------------------------------------------------");

    private String universalPin;

    TextConstants(String x){
        universalPin = x;
    }

    public String getUniversalText(){
        return universalPin;
    }
}
