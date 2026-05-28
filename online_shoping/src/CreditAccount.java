public class CreditAccount extends Account {
    private final double creditLimit;
    private final double interestRate;

    public CreditAccount(String accountNumber, double balance, double creditLimit, double interestRate) {
        super(accountNumber, balance);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public boolean withdraw(double amount) {
        if ((balance + creditLimit) >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public void deposit(double amount) {
        if (balance < 0) {
            double debt = Math.abs(balance);
            double clearAmount = Math.min(amount, debt);
            double fee = clearAmount * interestRate;
            System.out.printf("Начислен и удержан процент за использование: %.2f руб.%n", fee);
            balance += (amount - fee);
        } else {
            balance += amount;
        }
    }
}