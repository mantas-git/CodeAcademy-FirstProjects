import java.time.LocalDateTime;
import java.util.ArrayList;

public class DataFilter {
    LocalDateTime dateFrom;
    LocalDateTime dateTill;
    ArrayList<Integer> categories;
    boolean transactionType;

    public DataFilter(LocalDateTime dateFrom, LocalDateTime dateTill, ArrayList<Integer> categories, boolean transactionType) {
        this.dateFrom = dateFrom;
        this.dateTill = dateTill;
        this.categories = categories;
        this.transactionType = transactionType;
    }

    public LocalDateTime getDateFrom() {
        return dateFrom;
    }

    public LocalDateTime getDateTill() {
        return dateTill;
    }

    public ArrayList<Integer> getCategories() {
        return categories;
    }

    public boolean isTransactionType() {
        return transactionType;
    }
}
