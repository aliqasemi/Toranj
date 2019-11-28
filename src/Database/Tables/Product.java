package Database.Tables;

public class Product extends Tables {

    private int product_id;
    private String name;
    private double price;
    private int discount_percent;
    private int count_of_product;
    private String description;

    public Product(int product_id, String name, double price, int discount_percent, int count_of_product, String description) {
        super("product", 6);
        setProductID(product_id);
        setName(name);
        setPrice(price);
        setDiscountPercent(discount_percent);
        setCountOfProduct(count_of_product);
        setDescription(description);
    }

    private void setProductID(int product_id) {
        this.product_id = product_id;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setPrice(double price) {
        this.price = price;
    }

    private void setDiscountPercent(int discount_percent) {
        this.discount_percent = discount_percent;
    }

    private void setCountOfProduct(int count_of_product) {
        this.count_of_product = count_of_product;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getFields() {
        String str = "product_id, name, price, discount_percent, count_of_product, description";
        return str;
    }

    @Override
    public String getFieldsValues() {
        String str;
        str = product_id + ", " + "\"" + name + "\"" + ", " + price + ", " + discount_percent + ", " + count_of_product +
                ", \"" + description + "\"";


        str = str.replaceAll("\"null\"", "NULL");
        return str;
    }
}
