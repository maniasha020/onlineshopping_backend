import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class abProduct implements Payable {

    private final Long id;
    private String title;
    private double price;
    private String category;
    private boolean paid;
    private ProductStatus status;

    private static Long counter = 1L;
    private static Map<Integer, String> staticCategories = new HashMap<>();

    static {
        staticCategories.put(1, "Бытовая техника");
        staticCategories.put(2, "Смартфоны");
        staticCategories.put(3, "Ноутбуки");
        staticCategories.put(4, "Телевизоры");
        staticCategories.put(5, "Аксессуары");
    }

    public abProduct(String title, double price, String category) {
        this.id = generateId();
        this.title = title;
        this.price = price;
        this.category = category;
        this.paid = false;
        this.status = ProductStatus.AVAILABLE;
    }

    private Long generateId() {
        return counter++;
    }

    private double calculatePrice() {
        return price;
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

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public static Map<Integer, String> getStaticCategories() {
        return staticCategories;
    }


    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public boolean isAvailable() {
        return status == ProductStatus.AVAILABLE;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", title=" + title +
                ", price=" + price +
                ", category=" + category +
                ", paid=" + paid +
                ", status=" + status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof abProduct)) return false;
        abProduct p = (abProduct) o;
        return Objects.equals(id, p.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
