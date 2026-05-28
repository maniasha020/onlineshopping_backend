import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private final long id;
    private final double amount;
    private final PaymentType paymentType;
    private final LocalDateTime dateTime;
    private final String comment;

    public Transaction(long id, double amount, PaymentType paymentType, String comment) {
        this.id = id;
        this.amount = amount;
        this.paymentType = paymentType;
        this.comment = comment;
        this.dateTime = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getComment() {
        return comment;
    }

    public String toTableRow() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeStr = dateTime.format(formatter);

        return String.format("| %-3d | %-10.2f | %-10s | %-8s | %-50s |",
                id, amount, paymentType, timeStr, comment);
    }
}