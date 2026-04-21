import java.util.ArrayList;

public class Client implements Financeable {
    private String name;
    private double balance;
    private ArrayList<abProduct> cart;

    public Client(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.cart = new ArrayList<>();
    }

    public void addProduct(abProduct product) {
        if (product != null) {
            cart.add(product);
        }
    }

    public double getCartTotal() {
        double total = 0;

        for (abProduct product : cart) {
            total += product.getFinalPrice();
        }

        return total;
    }

    @Override
    public boolean checkBalance(double amount) {
        return balance >= amount;
    }

    @Override
    public String getFinancialStatus() {
        return "Баланс клиента: " + balance;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    public void payForProducts() {
        double total = getCartTotal();

        if (checkBalance(total)) {
            balance -= total;

            for (abProduct product : cart) {
                product.pay(product.getFinalPrice());
            }

            System.out.println(name + " оплатил покупку на сумму " + total);
        } else {
            System.out.println(name + " не может оплатить покупку. Не хватает денег");
        }
    }

    public String getName() {
        return name;
    }
}