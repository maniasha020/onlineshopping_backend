import java.util.Comparator;

public class ProductComparators {

    public static Comparator<abProduct> byPriceUp() {
        return (p1, p2) -> Double.compare(p1.getFinalPrice(), p2.getFinalPrice());
    }

    public static Comparator<abProduct> byPriceDown() {
        return (p1, p2) -> Double.compare(p2.getFinalPrice(), p1.getFinalPrice());
    }

    public static Comparator<abProduct> byNameUp() {
        return (p1, p2) -> p1.getTitle().compareToIgnoreCase(p2.getTitle());
    }

    public static Comparator<abProduct> byNameDown() {
        return (p1, p2) -> p2.getTitle().compareToIgnoreCase(p1.getTitle());
    }
}