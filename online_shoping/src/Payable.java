public interface Payable {
    double getFinalPrice();
    void pay(double amount);
    boolean isPaid();
}