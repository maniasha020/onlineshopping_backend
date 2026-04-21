import java.util.ArrayList;
import java.util.Objects;

public class Electro extends abProduct {
    private String brand;
    private String model;
    private ArrayList<abProduct> items = new ArrayList<>();

    public Electro(String title, double price, String category, String brand, String model) {
        super(title, price, category);
        this.brand = brand;
        this.model = model;
    }

    public void addItem(abProduct item) {
        if (item != null) {
            items.add(item);
        }
    }

    public void addItems(ArrayList<abProduct> abProducts) {
        items.addAll(abProducts);
    }

    @Override
    public double getFinalPrice() {
        double total = getPrice();

        for (abProduct p : items) {
            total += p.getFinalPrice();
        }

        return total;
    }

    @Override
    public String getProductType() {
        return "Electronics";
    }

    @Override
    public String toString() {
        return "Electro{id=" + getId() + ", title='" + getTitle() + "', total=" + getFinalPrice() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof Electro)) return false;
        if (!super.equals(o)) return false;
        Electro electro = (Electro) o;
        return Objects.equals(brand, electro.brand) && Objects.equals(model, electro.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), brand, model);
    }
}