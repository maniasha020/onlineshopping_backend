public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public void createTransaction(long id, double amount, PaymentType type) {
        PaymentStrategy strategy = PaymentStrategyFactory.create(type);
        strategy.pay(amount);

        // Исправлено: теперь передается 4-й параметр (комментарий), чтобы не было ошибки
        Transaction transaction = new Transaction(id, amount, type, "Прямая оплата через сервис");

        repository.add(transaction);
    }
}