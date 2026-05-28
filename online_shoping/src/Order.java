import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private final List<abProduct> products = new ArrayList<>();

    public double getTotalPrice() {

        double sum = 0;

        for (abProduct product : products) {
            sum += product.getFinalPrice();
        }

        return sum;
    }
    private OrderStatus status = OrderStatus.CREATED;

    private final LocalDateTime createdAt;

    public Order() {
        this.createdAt = LocalDateTime.now();
    }

    public void addProduct(abProduct product) {
        products.add(product);
    }

    public void pay(PaymentType type) {
        this.status = OrderStatus.PAID;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<abProduct> getProducts() {
        return products;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void printOrderTable() {

        System.out.println("===============================================");
        System.out.printf("| %-3s | %-15s | %-10s |%n",
                "ID", "TITLE", "PRICE");

        System.out.println("===============================================");

        int id = 1;

        for (abProduct product : products) {
            System.out.printf("| %-3d | %-15s | %-10.2f |%n",
                    id++,
                    product.getTitle(),
                    product.getFinalPrice()
            );
        }

        System.out.println("===============================================");
        System.out.println("STATUS: " + status);
        System.out.println("CREATED: " + createdAt);
        System.out.println("===============================================");
    }
}