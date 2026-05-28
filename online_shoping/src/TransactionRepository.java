import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {

    private static TransactionRepository instance;
    private final List<Transaction> transactions = new ArrayList<>();

    private TransactionRepository() {}

    public static TransactionRepository getInstance() {
        if (instance == null) {
            instance = new TransactionRepository();
        }
        return instance;
    }

    public void add(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getAll() {
        return transactions;
    }
}