import java.time.LocalDateTime;

public class BalanceHistory {
    private long itemId;
    private String itemType; //galima panaudoti Enum klasę
    private double amount;
    private double fee;
    private float currentBalance;
    private LocalDateTime dateTime;

    public BalanceHistory() {
    }

    public BalanceHistory(long itemId, String itemType, double amount, double fee, float currentBalance, LocalDateTime dateTime) {
        this.itemId = itemId;
        this.itemType = itemType;
        this.amount = amount;
        this.fee = fee;
        this.currentBalance = currentBalance;
        this.dateTime = dateTime;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public float getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(float currentBalance) {
        this.currentBalance = currentBalance;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "BalanceHistory{" +
                "itemId=" + itemId +
                ", itemType='" + itemType + '\'' +
                ", amount=" + amount +
                ", fee=" + fee +
                ", currentBalance=" + currentBalance +
                ", dateTime=" + dateTime +
                '}';
    }
}
