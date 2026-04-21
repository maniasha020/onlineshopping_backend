import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class abProduct implements Payable {
    private final Long id;
    private String title;
    private double price;
    private String category;
    private boolean paid;

    private static Long counter = 1L;
    private static Map<Integer, String> staticCategories = new HashMap<>();

    static {
        staticCategories.put(1, "Бытовая техника");
        staticCategories.put(2, "Смартфоны");
        staticCategories.put(3, "Ноутбуки");
        staticCategories.put(4, "Телевизоры");
    }

    public abProduct(String title, double price, String category) {
        this.id = generateId();
        this.title = title;
        this.price = price;
        this.category = category;
        this.paid = false;
    }

    private Long generateId() {
        return counter++;
    }

    private double calculatePrice() {
        return this.price;
    }

    @Override
    public double getFinalPrice() {
        return calculatePrice();
    }

    @Override
    public void pay(double amount) {
        if (amount >= getFinalPrice()) {
            paid = true;
        }
    }

    @Override
    public boolean isPaid() {
        return paid;
    }

    public abstract String getProductType();

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public static Map<Integer, String> getStaticCategories() {
        return staticCategories;
    }

    @Override
    public String toString() {
        return "abProduct{id=" + id + ", title='" + title + "', price=" + price +
                ", category='" + category + "', paid=" + paid + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof abProduct)) return false;
        abProduct abProduct = (abProduct) o;
        return Objects.equals(id, abProduct.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}