package Database.Tables;

public class OrderHasProduct extends Tables {

    private int order_id;
    private int product_id;

    public OrderHasProduct(int order_id, int product_id) {
        super("order_has_product", 2);
        setOrderID(order_id);
        setProductID(product_id);

    }


    private void setOrderID(int order_id) {
        this.order_id = order_id;
    }

    private void setProductID(int product_id) {
        this.product_id = product_id;
    }

    @Override
    public String getFields() {
        String str = "order_order_id, product_product_id";
        return str;
    }

    @Override
    public String getFieldsValues() {
        String str = order_id + ", " + product_id;
        return str;
    }
}
