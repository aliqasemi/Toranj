package Database.Tables;

public class Order extends Tables {

    private int order_id;
    private Double prepaid_fund;
    private Double total_amount;
    private String order_date;
    private String delivery_date;
    private Integer count_of_product;
    private int customer_id;
    private int personnel_id;
    private int supplier_id;
    private String description;

    public Order(int order_id, Double prepaid_fund, Double total_amount, String order_date, String delivery_date,
                 Integer count_of_product, int customer_id, int personnel_id, int supplier_id, String description) {
        super("orders", 10);
        setOrderID(order_id);
        setPrepaidFund(prepaid_fund);
        setTotalAmount(total_amount);
        setOrderDate(order_date);
        setDeliveryDate(delivery_date);
        setCountOfProduct(count_of_product);
        setCustomerID(customer_id);
        setPersonnelID(personnel_id);
        setSupplierID(supplier_id);
        setDescription(description);

    }

    private void setOrderID(int order_id) {
        this.order_id = order_id;
    }

    private void setPrepaidFund(Double prepaid_fund) {
        this.prepaid_fund = prepaid_fund;
    }

    private void setTotalAmount(Double total_amount) {
        this.total_amount = total_amount;
    }

    private void setOrderDate(String order_date) {
        this.order_date = order_date;
    }

    private void setDeliveryDate(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    private void setCountOfProduct(Integer count_of_product) {
        this.count_of_product = count_of_product;
    }

    private void setCustomerID(Integer customer_id) {
        this.customer_id = customer_id;
    }

    private void setPersonnelID(int personnel_id) {
        this.personnel_id = personnel_id;
    }

    private void setSupplierID(Integer supplier_id) {
        this.supplier_id = supplier_id;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public String getFields() {
        String str = "order_id, prepaid_fund, total_amount, order_date, delivery_date, count_of_product, "
                + "customer_customer_id, personnel_personnel_id, supplier_supplier_id, description";
        return str;
    }

    public String getFieldsValues() {
        String str = order_id + ", " + prepaid_fund + ", " + total_amount + ", " + "\"" + order_date + "\"" + ", " +
                "\"" + delivery_date + "\"" + ", " + count_of_product + ", " + customer_id + ", " + personnel_id + ", " +
                supplier_id + ", '" + this.description + "'";

        str = str.replaceAll("\"null\"", "NULL");
        str = str.replaceAll("null", "NULL");
        return str;
    }
}
