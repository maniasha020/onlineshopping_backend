import java.util.Comparator;

public class ProductCompareService {

    public static void compare(abProduct p1, abProduct p2, Comparator<abProduct> comp) {
        if (p1 == null || p2 == null) {
            System.out.println("Ошибка: товар не выбран");
            return;
        }

        if (!p1.getCategory().equals(p2.getCategory())) {
            System.out.println("Нельзя сравнить товары из разных категорий");
            System.out.println("Первый товар: " + p1.getTitle() + ", категория: " + p1.getCategory());
            System.out.println("Второй товар: " + p2.getTitle() + ", категория: " + p2.getCategory());
            return;
        }

        int res = comp.compare(p1, p2);

        if (res == 0) {
            System.out.println("Товары равны");
        } else if (res > 0) {
            System.out.println("Первый товар больше");
        } else {
            System.out.println("Второй товар больше");
        }
    }
}