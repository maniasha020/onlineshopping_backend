import java.util.ArrayList;

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
        if (item != null) items.add(item);
    }

    @Override
    public double getFinalPrice() {
        double total = getPrice();
        for (abProduct p : items) {
            total += p.getPrice();
        }
        return total;
    }

    @Override
    public String getProductType() {
        return "Electronics";
    }
}