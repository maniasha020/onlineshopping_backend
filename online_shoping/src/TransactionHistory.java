import java.util.List;

public class TransactionHistory {

    private final List<Transaction> transactions;

    public TransactionHistory(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void printHistoryTable() {
        System.out.println("=================================================================================================");
        System.out.printf("| %-3s | %-10s | %-10s | %-8s | %-50s |%n",
                "ID", "AMOUNT", "TYPE", "TIME", "COMMENT / ACCOUNT LINKAGE");
        System.out.println("=================================================================================================");

        for (Transaction t : transactions) {
            System.out.println(t.toTableRow());
        }

        System.out.println("=================================================================================================");
    }
}