public abstract class Account {
    private final String accountNumber;
    protected double balance;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }

    public abstract boolean withdraw(double amount);
    public abstract void deposit(double amount);
}