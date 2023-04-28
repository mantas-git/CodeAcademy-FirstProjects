import java.time.LocalDateTime;
import java.util.Scanner;

public class Biudzetas {
    private IncomeStatement[] incomeStatements = new IncomeStatement[100];
    int incomeStCounter = 0;
    private OutgoingStatement[] outgoingStatements = new OutgoingStatement[100];
    int outgoingStCounter = 0;

    Scanner scannerB = new Scanner(System.in);

    public Biudzetas() {
    }

    public IncomeStatement[] getIncomeStatements() {
        return incomeStatements;
    }

    public void setIncomeStatements(IncomeStatement[] incomeStatements) {
        this.incomeStatements = incomeStatements;
    }

    public OutgoingStatement[] getOutgoingStatements() {
        return outgoingStatements;
    }

    public void setOutgoingStatements(OutgoingStatement[] outgoingStatements) {
        this.outgoingStatements = outgoingStatements;
    }

    public void pridetiPajamuIrasa(){
        IncomeStatement incomeStatement = new IncomeStatement();
        System.out.println("Įvesti pajamų sumą: ");
        double sum = Double.parseDouble(scannerB.nextLine());
        incomeStatement.setProcessDate(LocalDateTime.now());
        incomeStatement.setAmount(sum);
        System.out.println("Įvesti pajamų kategoriją: ");
        incomeStatement.setCategory(scannerB.nextLine());
        System.out.println("Ar pinigai pervesti į banką? ");
        incomeStatement.setTransferedToTheBank(scannerB.nextLine());
        System.out.println("Papildoma infomracija: ");
        incomeStatement.setAdditionalInfo(scannerB.nextLine());
        incomeStatements[incomeStCounter] = incomeStatement;
        incomeStCounter++;
        System.out.println();
    }

    public void gautiPajamuIrasa(){
        System.out.println("gautiPajamuIrasa");
        for(int i = 0; i < incomeStCounter; i++){
            System.out.println(incomeStatements[i]);
        }

    }

    public void pridetiIslaiduIrasa(){
        OutgoingStatement outgoingStatement = new OutgoingStatement();
        System.out.print("Įvesti išlaidų sumą: ");
        double sum = Double.parseDouble(scannerB.nextLine());
        outgoingStatement.setProcessDate(LocalDateTime.now());
        outgoingStatement.setAmount(sum);
        System.out.print("Įvesti pajamų kategoriją: ");
        outgoingStatement.setCategory(scannerB.nextLine());
        System.out.print("Mokėjimas grynais ar kortele?  ");
        outgoingStatement.setPaymentMethod(scannerB.nextLine());
        System.out.print("Papildoma infomracija: ");
        outgoingStatement.setAdditionalInfo(scannerB.nextLine());
        outgoingStatements[incomeStCounter] = outgoingStatement;
        outgoingStCounter++;
    }

    public void gautiIslaiduIrasa(){
        System.out.println("gautiIslaiduIrasa");
        for(int i = 0; i < outgoingStCounter; i++){
            System.out.println(outgoingStatements[i]);
        }

    }
}
