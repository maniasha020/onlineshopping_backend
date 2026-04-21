public class Main {
    public static void main(String[] args) {

        System.out.println("      СОЗДАНИЕ БАЗОВЫХ ТОВАРОВ      ");
        System.out.println("-----------------------------------------");

        abProduct mouse = new Product("Logitech Mouse", 1500.0);
        abProduct pad = new Product("Gaming Pad", 500.0);

        System.out.println("Первый товар: " + mouse);
        System.out.println("ID генерируется автоматически: " + mouse.getId());
        System.out.println("Второй товар: " + pad);
        System.out.println();

        System.out.println("       РАБОТА С ЭЛЕКТРОНИКОЙ         ");
        System.out.println("-----------------------------------------");

        Electro laptop = new Electro("MacBook Air", 120000.0, "Laptops", "Apple", "M2");
        abProduct phone = new Electro("iPhone 15", 90000.0, "Smartphones", "Apple", "A15");

        System.out.println("Тип первого товара: " + laptop.getProductType());
        System.out.println("Тип второго товара: " + phone.getProductType());
        System.out.println();

        System.out.println("    ДОБАВЛЕНИЕ АКСЕССУАРОВ В ТОВАР   ");
        System.out.println("-------------------------------------------");

        System.out.println("Базовая цена ноутбука: " + laptop.getPrice());

        laptop.addItem(mouse);
        laptop.addItem(pad);

        System.out.println("После добавления аксессуаров итоговая цена: " + laptop.getFinalPrice());
        System.out.println();

        System.out.println("         СРАВНЕНИЕ ОБЪЕКТОВ          ");
        System.out.println("----------------------------------------");

        abProduct mouseDuplicate = new Product("Logitech Mouse", 1500.0);

        System.out.println("Мышь 1 и Мышь 2 равны по ID? " + mouse.equals(mouseDuplicate));

        if (laptop instanceof Electro) {
            System.out.println("Объект laptop действительно является Электроникой");
        }
        System.out.println();

        System.out.println("      ГЛОБАЛЬНЫЙ КАТАЛОГ И СЧЕТЧИКИ  ");
        System.out.println("------------------------------------------");

        Category electroCat = new Category("Electronics");
        electroCat.addSubcategory("Laptops");
        electroCat.addSubcategory("Phones");

        Category foodCat = new Category("Food");

        catalog storeCatalog = new catalog();
        storeCatalog.addCategory(electroCat);
        storeCatalog.addCategory(foodCat);

        storeCatalog.showCatalog();

        System.out.println("Статическая проверка:");
        System.out.println("Всего категорий создано: " + Category.getCategoryCount());
        System.out.println("Всего подкатегорий создано: " + Category.getSubcategoryCount());
        System.out.println();

        System.out.println("      СПРАВОЧНИК КАТЕГОРИЙ ТОВАРОВ   ");
        System.out.println("----------------------------------------");

        abProduct.getStaticCategories().forEach((id, name) -> {
            System.out.println("Код категории: " + id + ", Название: " + name);
        });
        System.out.println();

        System.out.println("          ПРОВЕРКА HASHCODE          ");
        System.out.println("------------------------------------------");

        System.out.println("HashCode ноутбука: " + laptop.hashCode());
        System.out.println("HashCode смартфона: " + phone.hashCode());

        abProduct testProduct = new Product("Test", 100.0);
        System.out.println("HashCode нового товара: " + testProduct.hashCode());
        System.out.println();

        System.out.println("        ПЕРВЫЙ ПОКУПАТЕЛЬ И ОПЛАТА   ");
        System.out.println("-----------------------------------------");

        Client c = new Client("Masha", 130000);

        c.addProduct(laptop);

        System.out.println("Финансовый статус покупателя: " + c.getFinancialStatus());
        System.out.println("Общая сумма покупки: " + c.getCartTotal());
        System.out.println("Хватает ли денег? " + c.checkBalance(c.getCartTotal()));

        c.payForProducts();

        System.out.println("Ноутбук оплачен? " + laptop.isPaid());
        System.out.println("Осталось денег у Masha: " + c.getBalance());
        System.out.println();

        System.out.println("      ВТОРОЙ ПОКУПАТЕЛЬ И 2 ПОКУПКИ  ");
        System.out.println("----------------------------------------");

        Client c2 = new Client("Dasha", 200000);

        abProduct keyboard = new Product("Mechanical Keyboard", 7000.0);
        abProduct headphones = new Product("Headphones", 12000.0);

        c2.addProduct(keyboard);
        c2.addProduct(headphones);

        System.out.println("Покупатель: Dasha");
        System.out.println("Начальный баланс: " + c2.getBalance());
        System.out.println("Финансовый статус: " + c2.getFinancialStatus());
        System.out.println("Общая сумма покупки: " + c2.getCartTotal());
        System.out.println("Хватает ли денег? " + c2.checkBalance(c2.getCartTotal()));
        System.out.println();

        c2.payForProducts();

        System.out.println("Клавиатура оплачена? " + keyboard.isPaid());
        System.out.println("Наушники оплачены? " + headphones.isPaid());
        System.out.println("Баланс после покупки: " + c2.getBalance());
        System.out.println();
    }
}