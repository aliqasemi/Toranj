package Data;

public class Productes {

    //Attributes
    private String name;
    private double price;
    private int discountPercent;
    private int count;
    private double totalAmont;
    private String description;

    //Constructor
    public Productes(String name, double price, int discountPercent, int count, String description) {
        this.name = name;
        this.price = price;
        this.discountPercent = discountPercent;
        this.count = count;
        if (discountPercent != 0 && count != 0) {
            this.totalAmont = this.price * this.count * discountPercent / 100;
        } else if (count != 0) {
            this.totalAmont = this.price * this.count;
        } else {
            this.totalAmont = this.price;
        }
        this.description = description;
    }

    //Default Constructor
    public Productes() {

    }

    //Methods

    //Setters


    public void setTotalAmont(double totalAmont) {
        this.totalAmont = totalAmont;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //Getters


    public double getTotalAmont() {
        return totalAmont;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public int getCount() {
        return count;
    }

    public String getDescription() {
        return description;
    }
}
