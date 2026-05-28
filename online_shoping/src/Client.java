import java.util.ArrayList;
import java.util.List;

public class Client implements Financeable {
    private final String name;
    private final DebitAccount debitAccount;
    private final CreditAccount creditAccount;
    private final List<abProduct> purchaseHistory = new ArrayList<>();

    public Client(String name, DebitAccount debitAccount, CreditAccount creditAccount) {
        this.name = name;
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
    }

    public boolean processPurchase(double amount, String productTitle, PaymentType paymentType) {
        TransactionRepository repository = TransactionRepository.getInstance();
        long txId = System.currentTimeMillis() % 1000;

        if (paymentType == PaymentType.CASH) {
            System.out.println("[Регулятор] Выбрана оплата наличными. Списание со счетов отложено до получения.");
            repository.add(new Transaction(txId, amount, paymentType, "Оплата при получении: " + productTitle));
            return true;
        }

        if (paymentType == PaymentType.CARD) {
            if (debitAccount.withdraw(amount)) {
                System.out.println("[Регулятор] Средства успешно списаны с Дебетового счета.");
                repository.add(new Transaction(txId, amount, paymentType, "Дебет: покупка " + productTitle));
                return true;
            } else {
                System.out.println("На дебетовом счете не хватает средств! Автоматический переход на Кредит...");
                if (creditAccount.withdraw(amount)) {
                    String linkage = String.format("Регулятор: Дебет -> Кредит под %s. Долг: %.2f", productTitle, creditAccount.getBalance());
                    repository.add(new Transaction(txId, amount, paymentType, linkage));
                    return true;
                } else {
                    System.out.println("Ошибка: Даже с учетом кредита лимит превышен!");
                    repository.add(new Transaction(txId, amount, paymentType, "ОТКАЗ (Недостаточно средств): " + productTitle));
                    return false;
                }
            }
        }

        if (paymentType == PaymentType.CREDIT) {
            if (creditAccount.withdraw(amount)) {
                System.out.println("Средства напрямую списаны с Кредитного счета.");
                String creditLog = String.format("Кредит: прямая покупка %s. Баланс: %.2f", productTitle, creditAccount.getBalance());
                repository.add(new Transaction(txId, amount, paymentType, creditLog));
                return true;
            } else {
                System.out.println("Ошибка: Превышен кредитный лимит при онлайн-оплате!");
                repository.add(new Transaction(txId, amount, paymentType, "ОТКАЗ (Превышен кред. лимит): " + productTitle));
                return false;
            }
        }

        return false;
    }

    public void transferDebitToCredit(double amount) {
        TransactionRepository repository = TransactionRepository.getInstance();
        long txId = System.currentTimeMillis() % 1000;

        if (amount <= 0) {
            System.out.println("Сумма перевода должна быть больше нуля.");
            return;
        }

        if (debitAccount.getBalance() < amount) {
            System.out.println("Недостаточно средств на Дебетовом счете для перевода!");
            repository.add(new Transaction(txId, amount, PaymentType.CARD, "ОТКАЗ ПЕРЕВОДА: Дебет -> Кредит (Недостаточно средств)"));
            return;
        }

        double currentCreditBalance = creditAccount.getBalance();
        if (currentCreditBalance >= 0) {
            System.out.println("На кредитном счете нет долга. Перевод не требуется.");
            repository.add(new Transaction(txId, amount, PaymentType.CARD, "ОТКАЗ ПЕРЕВОДА: Дебет -> Кредит (Нет задолженности)"));
            return;
        }

        debitAccount.withdraw(amount);
        creditAccount.deposit(amount);

        System.out.println("Перевод выполнен успешно.");
        repository.add(new Transaction(txId, amount, PaymentType.CARD, "ПЕРЕВОД: Дебет -> Кредитный счет"));
    }

    public void transferCreditToDebit(double amount) {
        TransactionRepository repository = TransactionRepository.getInstance();
        long txId = System.currentTimeMillis() % 1000;

        if (amount <= 0) {
            System.out.println("Сумма перевода должна быть больше нуля.");
            return;
        }

        if (creditAccount.withdraw(amount)) {
            debitAccount.deposit(amount);
            System.out.println("Средства успешно переведены из кредитного лимита на Дебет.");
            repository.add(new Transaction(txId, amount, PaymentType.CREDIT, "ПЕРЕВОД: Кредит -> Дебетовый счет"));
        } else {
            System.out.println("Перевод отклонен: Превышен доступный кредитный лимит!");
            repository.add(new Transaction(txId, amount, PaymentType.CREDIT, "ОТКАЗ ПЕРЕВОДА: Кредит -> Дебет (Превышен лимит)"));
        }
    }

    public void addProductToHistory(abProduct product) {
        purchaseHistory.add(product);
    }

    public void showPurchaseHistory() {
        System.out.println("\n=== ЛИЧНАЯ ИСТОРИЯ ПОКУПОК КЛИЕНТА ===");
        if (purchaseHistory.isEmpty()) {
            System.out.println("История пуста.");
        } else {
            purchaseHistory.forEach(p -> System.out.println("- " + p.getTitle() + " | Цена: " + p.getFinalPrice() + " руб."));
        }
    }

    @Override
    public boolean checkBalance(double amount) {
        return (debitAccount.getBalance() + creditAccount.getBalance()) >= amount;
    }

    @Override
    public String getFinancialStatus() {
        return String.format("Дебет: %.2f руб. | Кредит (Баланс): %.2f руб.", debitAccount.getBalance(), creditAccount.getBalance());
    }

    @Override
    public double getFinalPrice() { return 0; }
    public String getName() { return name; }
    public CreditAccount getCreditAccount() { return creditAccount; }
    public DebitAccount getDebitAccount() { return debitAccount; }
}