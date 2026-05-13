public class Product extends abProduct {

    public Product(String title, double price) {
        super(title, price, "General");
    }

    public Product(String title, double price, String category) {
        super(title, price, category);
    }

    @Override
    public String getProductType() {
        return "Item";
    }
}
