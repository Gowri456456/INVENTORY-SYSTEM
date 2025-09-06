package com.wipro.sales.bean;

public class Product {
	 private String productID;
	    private String productName;
	    private int quantityOnHand;
	    private double unitPrice;
	    private int reorderLevel;

	    // Getters and Setters
	    public String getProductID() { return productID; }
	    public void setProductID(String productID) { this.productID = productID; }

	    public String getProductName() { return productName; }
	    public void setProductName(String productName) { this.productName = productName; }

	    public int getQuantityOnHand() { return quantityOnHand; }
	    public void setQuantityOnHand(int quantityOnHand) { this.quantityOnHand = quantityOnHand; }

	    public double getUnitPrice() { return unitPrice; }
	    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }

	    public int getReorderLevel() { return reorderLevel; }
	    public void setReorderLevel(int reorderLevel) { this.reorderLevel = reorderLevel; }
}
