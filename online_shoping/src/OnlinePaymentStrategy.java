public class OnlinePaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Онлайн оплата: " + amount);
    }
}