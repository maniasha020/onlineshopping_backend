public class Product extends abProduct {

    public Product(String title, double price) {
        super(title, price, "General");
    }

    @Override
    public String getProductType() {
        return "Item";
    }
}