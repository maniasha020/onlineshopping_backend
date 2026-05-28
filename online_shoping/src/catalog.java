import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class catalog {
    private List<Category> categories = new ArrayList<>();

    public void addCategory(Category category) {
        if (category != null) {
            categories.add(category);
            Collections.sort(categories);
        }
    }

    public List<Category> getCategories() {
        return categories;
    }

    public Category getCategoryByNumber(int number) {
        if (number < 1 || number > categories.size()) {
            return null;
        }

        return categories.get(number - 1);
    }

    public void showCatalog() {
        System.out.println("\n=== КАТАЛОГ ===");
        if (categories.isEmpty()) {
            System.out.println("Каталог пуст.");
            return;
        }

        for (int i = 0; i < categories.size(); i++) {
            Category cat = categories.get(i);
            System.out.println((i + 1) + ". " + cat.getName());
        }
        System.out.println("====================================");
    }
}
