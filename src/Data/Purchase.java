package Data;

public class Purchase {

    //Attributes
    private int purchaseId;
    private String supplierName;
    private String supplieLastName;
    private String productName;
    private String orderDate;
    private String deliveryDate;
    private int count;
    private double price;
    private double prepaidFund;
    private double totalAmount;
    private String personnelName;
    private String description;

    //Constructor
    public Purchase(String supplierName, String supplieLastName, String productName, String orderDate, String deliveryDate, int count, double price, double prepaidFund, String description) {
        this.supplierName = supplierName;
        this.supplieLastName = supplieLastName;
        this.productName = productName;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.count = count;
        this.price = price;
        this.prepaidFund = prepaidFund;
        if (count != 0) {
            this.totalAmount = this.count * 3500;
        } else {
            this.totalAmount = 4100;
        }
        this.description = description;
    }

    //Default Constructor
    public Purchase() {

    }

    //Methods

    //Setters

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setSupplieLastName(String supplieLastName) {
        this.supplieLastName = supplieLastName;
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

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPrepaidFund(double prepaidFund) {
        this.prepaidFund = prepaidFund;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setPersonnelName(String personnelName) {
        this.personnelName = personnelName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //Getters

    public int getPurchaseId() {
        return purchaseId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getSupplieLastName() {
        return supplieLastName;
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

    public double getPrice() {
        return price;
    }

    public double getPrepaidFund() {
        return prepaidFund;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getPersonnelName() {
        return personnelName;
    }

    public String getDescription() {
        return description;
    }
}
