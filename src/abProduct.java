import java.util.HashMap;

public abstract class abProduct extends Product{
    private HashMap<Integer, String> category = new HashMap<>();
//    private String category;

    abProduct(String Title, double price, HashMap<Integer, String> category) {
        super(Title, price);
        this.category = this.category;
    }

    public void setCategory(HashMap<Integer, String> category) {this.category = category;}
    public HashMap<Integer, String> getCategory() {return category;}
}
