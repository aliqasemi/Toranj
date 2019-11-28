package Data;

public class Sales {

    private int saleId;
    private String customerName;
    private String customerLastname;
    private String productName;
    private String orderDate;
    private String deliveryDate;
    private int count;
    private double prepaidFund;
    private double totalAmount;
    private String personnelName;
    private String description;

    public Sales(String customerName, String customerLastname, String productName, String orderDate, String deliveryDate, int count, double prepaidFund, String description) {
        this.customerName = customerName;
        this.customerLastname = customerLastname;
        this.productName = productName;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.count = count;
        this.prepaidFund = prepaidFund;
        if (count != 0) {
            this.totalAmount = this.count * 2500;
        } else {
            this.totalAmount = 5600;
        }
        this.description = description;
    }

    public Sales() {

    }

    //Methods

    //Setters


    public void setCustomerLastname(String customerLastname) {
        this.customerLastname = customerLastname;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setPrepaidFund(double prepaidFund) {
        this.prepaidFund = prepaidFund;
    }

    public void setPersonnelName(String userName) {
        this.personnelName = userName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //Getters


    public int getSaleId() {
        return saleId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerLastname() {
        return customerLastname;
    }

    public String getProductName() {
        return productName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public int getCount() {
        return count;
    }

    public double getPrepaidFund() {
        return prepaidFund;
    }

    public String getPersonnelName() {
        return personnelName;
    }

    public String getDescription() {
        return description;
    }
}
