public class DebitAccount extends Account {
    public DebitAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }
}