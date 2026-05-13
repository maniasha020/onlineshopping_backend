import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<abProduct> products;
    private OrderStatus status;
    private PaymentType paymentType;

    public Order() {
        this.products = new ArrayList<>();
        this.status = OrderStatus.CREATED;
    }

    public void addProduct(abProduct product) {
        if (product != null && product.isAvailable()) {
            products.add(product);
            product.setStatus(ProductStatus.RESERVED);
        }
    }

    public double getFinalPrice() {
        double total = 0;

        for (abProduct product : products) {
            total += product.getFinalPrice();
        }

        return total;
    }

    public void pay(PaymentType paymentType) {
        this.paymentType = paymentType;
        this.status = OrderStatus.PAID;

        for (abProduct product : products) {
            product.pay(product.getFinalPrice());
            product.setStatus(ProductStatus.NOT_AVAILABLE);
        }
    }

    public OrderStatus getStatus() {
        return status;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public List<abProduct> getProducts() {
        return products;
    }

    public void printOrder() {
        System.out.println("Статус заказа: " + status);
        System.out.println("Способ оплаты: " + paymentType);
        System.out.println("Сумма заказа: " + getFinalPrice());

        for (abProduct product : products) {
            System.out.println(product.getTitle() + " | статус: " + product.getStatus());
        }
    }
}