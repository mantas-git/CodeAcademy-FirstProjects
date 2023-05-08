import java.time.LocalDateTime;
import java.util.ArrayList;

public class DataFilter {
    LocalDateTime dateFrom;
    LocalDateTime dateTill;
    ArrayList<Integer> categories;
    int transactionType;

    public DataFilter() {
    }

    public LocalDateTime getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDateTime dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDateTime getDateTill() {
        return dateTill;
    }

    public void setDateTill(LocalDateTime dateTill) {
        this.dateTill = dateTill;
    }

    public ArrayList<Integer> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Integer> categories) {
        this.categories = categories;
    }

    public int getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(int transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public String toString() {
        return "DataFilter{" +
                "dateFrom=" + dateFrom +
                ", dateTill=" + dateTill +
                ", categories=" + categories +
                ", transactionType=" + transactionType +
                '}';
    }
}
