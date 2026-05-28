import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        catalog storeCatalog = createCatalog();
        Order order = new Order();
        TransactionRepository repository = TransactionRepository.getInstance();

        DebitAccount debit = new DebitAccount("D-101", 25000.0);
        CreditAccount credit = new CreditAccount("C-202", 0.0, 100000.0, 0.10);
        Client client = new Client("Алексей", debit, credit);

        int choice = 0;
        while (choice != 13) {
            printMenu();
            System.out.println("Счета клиента -> " + client.getFinancialStatus());
            choice = readInt("Выберите пункт меню: ");

            if (choice == 1) {
                storeCatalog.showCatalog();
                int catChoice = readInt("Выберите номер категории для просмотра товаров (0 для отмены): ");
                if (catChoice > 0) {
                    Category selectedCategory = storeCatalog.getCategoryByNumber(catChoice);
                    if (selectedCategory != null) {
                        System.out.println("\n--- Товары в категории: " + selectedCategory.getName() + " ---");
                        printProducts(selectedCategory.getItems());
                    } else {
                        System.out.println("Категория не найдена.");
                    }
                }
            } else if (choice == 2) {
                showAllProducts(storeCatalog);
            } else if (choice == 3) {
                sortProducts(storeCatalog);
            } else if (choice == 4) {
                compareProducts(storeCatalog);
            } else if (choice == 5) {
                showAvailableProducts(storeCatalog);
            } else if (choice == 6) {
                filterByStatus(storeCatalog);
            } else if (choice == 7) {
                executePurchaseFlow(storeCatalog, client, order);
            } else if (choice == 8) {
                client.showPurchaseHistory();
            } else if (choice == 9) {
                double currentBalance = client.getCreditAccount().getBalance();
                if (currentBalance >= 0) {
                    System.out.println("У вас нет задолженности по кредитному счету.");
                } else {
                    double debt = Math.abs(currentBalance);
                    double rate = client.getCreditAccount().getInterestRate();
                    double totalToPay = debt + (debt * rate);

                    System.out.printf("Текущий чистый долг: %.2f руб.%n", debt);
                    System.out.printf("Процентная ставка: %.0f%%%n", rate * 100);
                    System.out.printf("Чтобы полностью закрыть долг, нужно вернуть с процентами: %.2f руб.%n", totalToPay);

                    double amount = readDouble("Введите сумму для внесения на счет: ");
                    client.getCreditAccount().deposit(amount);

                    repository.add(new Transaction(System.currentTimeMillis() % 1000, amount, PaymentType.CREDIT, "ПОПОЛНЕНИЕ: Внесение наличных на Кредит"));
                }
            } else if (choice == 10) {
                TransactionHistory historyOutput = new TransactionHistory(repository.getAll());
                historyOutput.printHistoryTable();
            }
            else if (choice == 11) {
                double amount = readDouble("Введите сумму для перевода с Дебета на Кредит: ");
                client.transferDebitToCredit(amount);
            }
            else if (choice == 12) {
                double amount = readDouble("Введите сумму для перевода с Кредита на Дебет: ");
                client.transferCreditToDebit(amount);
            }
            else if (choice == 13) {
                System.out.println("Завершение работы.");
            }
        }
    }

    private static catalog createCatalog() {
        catalog storeCatalog = new catalog();
        Category accessories = new Category("Аксессуары");
        Category electronics = new Category("Электроника");

        accessories.addItem(new Product("Игровая мышь", 2500.0, "Аксессуары"));
        accessories.addItem(new Product("Механическая клавиатура", 6000.0, "Аксессуары"));
        electronics.addItem(new Electro("Смартфон iPhone 15", 85000.0, "Электроника", "Apple", "15"));
        electronics.addItem(new Electro("Ноутбук ThinkPad", 55000.0, "Электроника", "Lenovo", "T490"));

        storeCatalog.addCategory(accessories);
        storeCatalog.addCategory(electronics);
        return storeCatalog;
    }

    private static void printMenu() {
        System.out.println("\n================ МЕНЮ ИНТЕРНЕТ-МАГАЗИНА ================");
        System.out.println("1. Каталог");
        System.out.println("2. Вывести абсолютно все товары (Stream API)");
        System.out.println("3. Сортировка товаров в категории");
        System.out.println("4. Сравнение двух объектов (Комперинг)");
        System.out.println("5. Проверить доступные товары (Интерфейс ShopChecker + Лямбда)");
        System.out.println("6. Глубокая фильтрация по статусу (Stream API)");
        System.out.println("7. Оформить и оплатить покупку");
        System.out.println("8. Посмотреть историю покупок клиента");
        System.out.println("9. Пополнить кредитный счет / Узнать сумму к возврату");
        System.out.println("10. Вывести системную таблицу транзакций (Table Object)");
        System.out.println("11. Перевести деньги с Дебета на Кредит (Погашение)");
        System.out.println("12. Перевести деньги с Кредита на Дебет (Взять в долг)");
        System.out.println("13. Выход");
    }

    private static void showAllProducts(catalog storeCatalog) {
        storeCatalog.getCategories().stream()
                .peek(cat -> System.out.println("Категория: " + cat.getName()))
                .flatMap(cat -> cat.getItems().stream())
                .forEach(p -> System.out.println("   -> " + p.getTitle() + " | Цена: " + p.getFinalPrice() + " руб. | Статус: " + p.getStatus()));
    }

    private static void printProducts(List<abProduct> products) {
        if (products.isEmpty()) {
            System.out.println("В этой категории пока нет товаров.");
            return;
        }
        for (int i = 0; i < products.size(); i++) {
            abProduct product = products.get(i);
            System.out.println((i + 1) + ". " + product.getTitle()
                    + " | Цена: " + product.getFinalPrice() + " руб."
                    + " | Статус: " + product.getStatus());
        }
    }

    private static void showAvailableProducts(catalog storeCatalog) {
        ShopChecker<abProduct> availableChecker = product -> product.getStatus() == ProductStatus.AVAILABLE;
        System.out.println("=== ДОСТУПНЫЕ ТОВАРЫ ===");
        storeCatalog.getCategories().stream()
                .flatMap(cat -> cat.getItems().stream())
                .filter(availableChecker::check)
                .forEach(p -> System.out.println("- " + p.getTitle() + " за " + p.getFinalPrice() + " руб."));
    }

    private static void filterByStatus(catalog storeCatalog) {
        System.out.println("1. AVAILABLE | 2. NOT_AVAILABLE | 3. RESERVED");
        int s = readInt("Введите номер статуса: ");
        ProductStatus targetStatus = (s == 1) ? ProductStatus.AVAILABLE : (s == 2) ? ProductStatus.NOT_AVAILABLE : ProductStatus.RESERVED;

        storeCatalog.getCategories().stream()
                .flatMap(cat -> cat.getItems().stream())
                .filter(p -> p.getStatus() == targetStatus)
                .forEach(p -> System.out.println("[Фильтр] " + p.getTitle() + " -> " + p.getStatus()));
    }

    private static void executePurchaseFlow(catalog storeCatalog, Client client, Order order) {
        storeCatalog.showCatalog();
        int catNum = readInt("Выберите номер категории: ");
        Category category = storeCatalog.getCategoryByNumber(catNum);
        if (category == null) return;

        List<abProduct> items = category.getItems();
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getTitle() + " | Цена: " + items.get(i).getFinalPrice());
        }

        int itemNum = readInt("Выберите номер товара: ");
        if (itemNum < 1 || itemNum > items.size()) return;
        abProduct product = items.get(itemNum - 1);

        System.out.println("Способ оплаты: 1. CASH (При получении) | 2. CARD (Дебетовая) | 3. CREDIT (Кредитная)");
        int pChoice = readInt("Ваш выбор: ");
        PaymentType paymentType = (pChoice == 1) ? PaymentType.CASH : (pChoice == 2) ? PaymentType.CARD : PaymentType.CREDIT;

        boolean isSuccess = client.processPurchase(product.getFinalPrice(), product.getTitle(), paymentType);

        if (isSuccess) {
            PaymentStrategy paymentStrategy = PaymentStrategyFactory.create(paymentType);
            paymentStrategy.pay(product.getFinalPrice());

            product.pay(product.getFinalPrice());
            product.setStatus(ProductStatus.NOT_AVAILABLE);
            client.addProductToHistory(product);
            order.addProduct(product);
            System.out.println("Оформление завершено успешно!");
        } else {
            System.out.println("Отказ операции: Списание средств не удалось!");
        }
    }

    private static void sortProducts(catalog storeCatalog) {
        storeCatalog.showCatalog();
        int number = readInt("Введите номер категории: ");
        Category category = storeCatalog.getCategoryByNumber(number);
        if (category == null) return;

        List<abProduct> products = new ArrayList<>(category.getItems());
        products.sort(ProductComparators.byPriceUp());
        products.forEach(p -> System.out.println(p.getTitle() + " -> " + p.getFinalPrice()));
    }

    private static void compareProducts(catalog storeCatalog) {
        storeCatalog.showCatalog();
        int number = readInt("Введите номер категории: ");
        Category category = storeCatalog.getCategoryByNumber(number);
        if (category == null || category.getItems().size() < 2) return;

        List<abProduct> products = category.getItems();
        ProductCompareService.compare(products.get(0), products.get(1), ProductComparators.byPriceUp());
    }

    private static int readInt(String message) {
        System.out.print(message);
        while (!sc.hasNextInt()) {
            System.out.println("Введите число.");
            sc.next();
            System.out.print(message);
        }
        int val = sc.nextInt();
        sc.nextLine();
        return val;
    }

    private static double readDouble(String message) {
        System.out.print(message);
        while (!sc.hasNextDouble()) {
            System.out.println("Введите корректное число.");
            sc.next();
            System.out.print(message);
        }
        double val = sc.nextDouble();
        sc.nextLine();
        return val;
    }
}