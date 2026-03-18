public class Product {
    private String Title;
    private double price;

    Product(String Title, double price) {
        this.Title = Title;
        this.price = price;
    }

    public String getTitle(){return this.Title;}
    public void setTitle(String Title){this.Title = Title;}

    public double getPrice(){return this.price;}
    public void setPrice(double price){this.price = price;}

}

