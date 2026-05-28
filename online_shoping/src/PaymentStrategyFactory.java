public class PaymentStrategyFactory {

    public static PaymentStrategy create(PaymentType type) {
        switch (type) {
            case CASH:
                return new CashPaymentStrategy();
            case CARD:
                return new CardPaymentStrategy();
            case CREDIT:
                return new OnlinePaymentStrategy();
            default:
                throw new IllegalArgumentException("Неизвестный тип оплаты");
        }
    }
}