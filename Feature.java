public class Feature {
    private String name;
    private int price;

    public Feature(String tn, int cs) {
        name = tn;
        price = cs;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void displayFeature() {
        System.out.println("Feature Name   " + name);
        System.out.println("Cost        " + price);
    }
}
