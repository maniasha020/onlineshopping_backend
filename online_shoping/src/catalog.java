import java.util.ArrayList;
import java.util.List;

public class catalog {
    private List<Category> categories = new ArrayList<>();

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void showCatalog() {
        System.out.println("     CATALOG    ");
        for (Category cat : categories) {
            System.out.println(cat);
        }
        System.out.println("Categories: " + Category.getCategoryCount());
        System.out.println("Subcategories: " + Category.getSubcategoryCount());
    }
}
