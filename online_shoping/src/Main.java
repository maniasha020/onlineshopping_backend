//TODO создать новый класс product у него есть 3 поля
// 1)id(Long)
// 2)title(String)
// 3)price(Double)
// создать гетеры сеттеры и вывести два обьекта

//TODO создать абстрактный класс добавить новый класс электроника с категориями
// добавить абстракт можно продукт в абстакт добавляем новое поле, категория
// хешмап где номер категории и названием, создаем новый обычный класс который имеет
// название электро  он должен наследоваться от класса абстракт продукт ип должен быть
// с инкапсуляцией айди в ручную не можем инициализировать не использовать сеттер
// создат новый метод  в абстракт делатьб метод приватным он делает айди калькулейт прайс
//
//

//TODO адпродукт = категории два поля нейм и сабкатегории будут в листе тип категории есть два метода
// аад.категории и шов.категории показывает создаём новый класс который называется каталог где все на английском
// в каталоге храним список основных категорий добавляем два счетчика одник дл того сколько категорий и
// второй для того чтобы знать сколько подкатегорий


//TODO создать два интерфейса один пейбл, второй финансебл, пейбл имеет следующие методы пейбл пмогает обьектам товаров,
// финсебл помогает клиентам, внктли пейбл 3 абстракт метода 1 возвращает дабл без параметра называет гетфиналпрайс,
// второй войд название пей принимает параметр дабл можно назвать амоунт оплата товаров, 3 булиннг ис пейт оплачен ли товар,
// фин принимает 3 метода 1 возваращает дабл чекбаланс есть ли баланс без параметра, второй возвращает булинг
// провекра достаточно ли денег принимает параметр, 3 стринг гетфиналсилстатус финстатус, все необходимые класы нужно
// расширить с помощью хешкод иклс тусринг донтрепит инседоф создает обьекты и сравниваем их по цене по названию.


//TODO обавляем новый энам для статусов товаров статус для способов оплаты и возможно для статуса заказов эти энамы в
// нужных местах применяем  и используем и 3 щаг добавляем функциональный интерфес чтобы он работал с товарами и заказами
// когда его добавили его реализация будет через лямда выражениерасширяем пользовательское мекня и через лямда выражение
// можно реализрвать следуюзее проверка товаров, фильтрация по статусу с энамыми, возможно скидка по статусу клиента и
// можно проверять доступность товаров в результате энамы и функциональный интерфейс с лямда выражениями  в пользовательском меню
//
//добавляем энам статусы для оплаты клиентов и тд на выбор добавляем 1 функционал интерфейс реализуем и применяем в програме
//та же добавляем лямду выражение и используем ее с функционал интер оптимизируем код лишние строки убираем или заменяем на лямду

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        catalog storeCatalog = createCatalog();
        Order order = new Order();

        int choice = 0;

        while (choice != 9) {
            printMenu();
            choice = readInt("Выберите пункт меню: ");

            if (choice == 1) {
                storeCatalog.showCatalog();
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
                createOrder(storeCatalog, order);
            } else if (choice == 8) {
                order.printOrder();
            } else if (choice == 9) {
                System.out.println("Выход из программы");
            } else {
                System.out.println("Такого пункта меню нет");
            }
        }
    }

    private static catalog createCatalog() {
        abProduct mouse = new Product("Logitech Mouse", 1500.0, "Аксессуары");
        abProduct pad = new Product("Gaming Pad", 500.0, "Аксессуары");
        abProduct keyboard = new Product("Keyboard", 4000.0, "Аксессуары");
        Electro laptop = new Electro("MacBook Air", 120000.0, "Ноутбуки", "Apple", "M2");
        abProduct phone = new Electro("iPhone 15", 90000.0, "Смартфоны", "Apple", "A15");

        pad.setStatus(ProductStatus.NOT_AVAILABLE);

        Category accessoriesCat = new Category("Аксессуары");
        accessoriesCat.addSubcategory("Мыши");
        accessoriesCat.addSubcategory("Клавиатуры");
        accessoriesCat.addItem(mouse);
        accessoriesCat.addItem(pad);
        accessoriesCat.addItem(keyboard);

        Category electroCat = new Category("Электроника");
        electroCat.addSubcategory("Ноутбуки");
        electroCat.addSubcategory("Смартфоны");
        electroCat.addItem(laptop);
        electroCat.addItem(phone);

        catalog storeCatalog = new catalog();
        storeCatalog.addCategory(electroCat);
        storeCatalog.addCategory(accessoriesCat);

        return storeCatalog;
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("===== МЕНЮ ИНТЕРНЕТ-МАГАЗИНА =====");
        System.out.println("1. Показать категории");
        System.out.println("2. Показать товары");
        System.out.println("3. Сортировка товаров");
        System.out.println("4. Сравнить товары");
        System.out.println("5. Проверить доступные товары");
        System.out.println("6. Фильтрация товаров по статусу");
        System.out.println("7. Создать заказ и выбрать оплату");
        System.out.println("8. Показать заказ и статус заказа");
        System.out.println("9. Выход");
    }

    private static void showAllProducts(catalog storeCatalog) {
        for (Category category : storeCatalog.getCategories()) {
            System.out.println("Категория: " + category.getName());
            printProducts(category.getItems());
        }
    }

    private static void sortProducts(catalog storeCatalog) {
        Category category = chooseCategory(storeCatalog);

        if (category == null) {
            return;
        }

        List<abProduct> products = new ArrayList<>(category.getItems());

        if (products.isEmpty()) {
            System.out.println("В категории нет товаров");
            return;
        }

        System.out.println("1. По цене по возрастанию");
        System.out.println("2. По цене по убыванию");
        System.out.println("3. По названию А-Я");
        System.out.println("4. По названию Я-А");

        int sortChoice = readInt("Выберите сортировку: ");

        if (sortChoice == 1) {
            Collections.sort(products, ProductComparators.byPriceUp());
        } else if (sortChoice == 2) {
            Collections.sort(products, ProductComparators.byPriceDown());
        } else if (sortChoice == 3) {
            Collections.sort(products, ProductComparators.byNameUp());
        } else if (sortChoice == 4) {
            Collections.sort(products, ProductComparators.byNameDown());
        } else {
            System.out.println("Неверный вариант сортировки");
            return;
        }

        printProducts(products);
    }

    private static void compareProducts(catalog storeCatalog) {
        Category category = chooseCategory(storeCatalog);

        if (category == null) {
            return;
        }

        List<abProduct> products = category.getItems();

        if (products.size() < 2) {
            System.out.println("Для сравнения нужно минимум два товара");
            return;
        }

        printProducts(products);

        int firstNumber = readInt("Введите номер первого товара: ");
        int secondNumber = readInt("Введите номер второго товара: ");

        abProduct firstProduct = getProductByNumber(products, firstNumber);
        abProduct secondProduct = getProductByNumber(products, secondNumber);

        if (firstProduct == null || secondProduct == null) {
            System.out.println("Неверный номер товара");
            return;
        }

        System.out.println("1. Сравнить по цене");
        System.out.println("2. Сравнить по названию");

        int compareChoice = readInt("Выберите вариант сравнения: ");

        if (compareChoice == 1) {
            ProductCompareService.compare(firstProduct, secondProduct, ProductComparators.byPriceUp());
        } else if (compareChoice == 2) {
            ProductCompareService.compare(firstProduct, secondProduct, ProductComparators.byNameUp());
        } else {
            System.out.println("Неверный вариант сравнения");
        }
    }

    private static void showAvailableProducts(catalog storeCatalog) {
        ShopChecker<abProduct> availableChecker = product -> product.isAvailable();

        System.out.println("Доступные товары:");

        for (Category category : storeCatalog.getCategories()) {
            System.out.println("Категория: " + category.getName());

            boolean found = false;

            for (abProduct product : category.getItems()) {
                if (availableChecker.check(product)) {
                    printProduct(product, category.getItems().indexOf(product) + 1);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("Нет доступных товаров");
            }
        }
    }

    private static void filterByStatus(catalog storeCatalog) {
        ProductStatus status = chooseProductStatus();

        if (status == null) {
            System.out.println("Неверный статус");
            return;
        }

        ShopChecker<abProduct> statusChecker = product -> product.getStatus() == status;

        System.out.println("Товары со статусом " + status + ":");

        boolean found = false;

        for (Category category : storeCatalog.getCategories()) {
            for (abProduct product : category.getItems()) {
                if (statusChecker.check(product)) {
                    System.out.println(category.getName() + ": " + product.getTitle()
                            + " | цена: " + product.getFinalPrice()
                            + " | статус: " + product.getStatus());
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("Товаров с таким статусом нет");
        }
    }

    private static void createOrder(catalog storeCatalog, Order order) {
        Category category = chooseCategory(storeCatalog);

        if (category == null) {
            return;
        }

        if (category.getItems().isEmpty()) {
            System.out.println("В категории нет товаров");
            return;
        }

        printProducts(category.getItems());

        int productNumber = readInt("Выберите товар для заказа: ");
        abProduct selectedProduct = getProductByNumber(category.getItems(), productNumber);

        if (selectedProduct == null) {
            System.out.println("Неверный номер товара");
            return;
        }

        ShopChecker<abProduct> availableChecker = product -> product.getStatus() == ProductStatus.AVAILABLE;

        if (!availableChecker.check(selectedProduct)) {
            System.out.println("Товар недоступен для заказа");
            return;
        }

        order.addProduct(selectedProduct);
        System.out.println("Товар добавлен в заказ: " + selectedProduct.getTitle());

        PaymentType paymentType = choosePaymentType();

        if (paymentType == null) {
            System.out.println("Неверный способ оплаты");
            return;
        }

        order.pay(paymentType);
        System.out.println("Заказ оплачен");
        System.out.println("Статус заказа: " + order.getStatus());
    }

    private static Category chooseCategory(catalog storeCatalog) {
        storeCatalog.showCatalog();
        int number = readInt("Введите номер категории: ");

        Category category = storeCatalog.getCategoryByNumber(number);

        if (category == null) {
            System.out.println("Такой категории нет");
        }

        return category;
    }

    private static ProductStatus chooseProductStatus() {
        System.out.println("1. AVAILABLE");
        System.out.println("2. NOT_AVAILABLE");
        System.out.println("3. RESERVED");

        int statusChoice = readInt("Выберите статус товара: ");

        if (statusChoice == 1) {
            return ProductStatus.AVAILABLE;
        } else if (statusChoice == 2) {
            return ProductStatus.NOT_AVAILABLE;
        } else if (statusChoice == 3) {
            return ProductStatus.RESERVED;
        }

        return null;
    }

    private static PaymentType choosePaymentType() {
        System.out.println("Выберите способ оплаты:");
        System.out.println("1. Наличные");
        System.out.println("2. Карта");
        System.out.println("3. Онлайн");

        int payChoice = readInt("Ваш выбор: ");

        if (payChoice == 1) {
            return PaymentType.CASH;
        } else if (payChoice == 2) {
            return PaymentType.CARD;
        } else if (payChoice == 3) {
            return PaymentType.ONLINE;
        }

        return null;
    }

    private static void printProducts(List<abProduct> products) {
        if (products.isEmpty()) {
            System.out.println("Товаров нет");
            return;
        }

        for (int i = 0; i < products.size(); i++) {
            printProduct(products.get(i), i + 1);
        }
    }

    private static void printProduct(abProduct product, int number) {
        System.out.println(number + ". " + product.getTitle()
                + " | цена: " + product.getFinalPrice()
                + " | категория: " + product.getCategory()
                + " | статус: " + product.getStatus());
    }

    private static abProduct getProductByNumber(List<abProduct> products, int number) {
        if (number < 1 || number > products.size()) {
            return null;
        }

        return products.get(number - 1);
    }

    private static int readInt(String message) {
        System.out.print(message);

        while (!sc.hasNextInt()) {
            System.out.println("Введите число");
            sc.next();
            System.out.print(message);
        }

        return sc.nextInt();
    }
}
