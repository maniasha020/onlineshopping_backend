import java.util.ArrayList;
import java.util.List;

public class Client implements Financeable {

    private String name;
    private double balance;
    private List<abProduct> cart;

    public Client(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.cart = new ArrayList<>();
    }

    public void addToCart(abProduct product) {
        if (product != null && product.isAvailable()) {
            cart.add(product);
        }
    }

    public List<abProduct> getCart() {
        return cart;
    }

    @Override
    public boolean checkBalance(double amount) {
        return balance >= amount;
    }

    @Override
    public String getFinancialStatus() {
        return "Баланс: " + balance;
    }

    @Override
    public double getFinalPrice() {
        double total = 0;

        for (abProduct p : cart) {
            total += p.getFinalPrice();
        }

        return total;
    }

    public void payForProduct(abProduct product) {
        if (product == null) {
            System.out.println("Товар не выбран");
            return;
        }

        double sum = product.getFinalPrice();

        if (checkBalance(sum) && product.isAvailable()) {
            balance -= sum;
            product.pay(sum);
            product.setStatus(ProductStatus.NOT_AVAILABLE);
            System.out.println(name + " оплатил товар " + product.getTitle());
        } else {
            System.out.println(name + " не может оплатить товар " + product.getTitle());
        }
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }
}
