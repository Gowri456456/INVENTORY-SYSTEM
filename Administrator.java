package com.wipro.sales.service;

import java.util.*;
import com.wipro.sales.bean.*;

public class Administrator {
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Sales> sales = new ArrayList<>();
    private int productSeq = 1001;
    private int salesSeq = 1000;

    // Insert Product
    public String insertStock(Product stockObj) {
        if (stockObj == null || stockObj.getProductName().length() < 2) {
            return "Data not Valid for insertion";
        }
        String productId = stockObj.getProductName().substring(0, 2).toUpperCase() + productSeq++;
        stockObj.setProductID(productId);
        products.add(stockObj);
        return productId;
    }

    // Delete Product
    public String deleteStock(String productID) {
        for (Product p : products) {
            if (p.getProductID().equals(productID)) {
                products.remove(p);
                return "deleted";
            }
        }
        return "record cannot be deleted";
    }

    // Insert Sale
    public String insertSales(Sales salesObj) {
        if (salesObj == null) return "Object not valid for insertion";

        // Check product exists
        Product product = null;
        for (Product p : products) {
            if (p.getProductID().equals(salesObj.getProductID())) {
                product = p;
                break;
            }
        }
        if (product == null) return "Unknown Product for sales";

        // Stock validation
        if (product.getQuantityOnHand() < salesObj.getQuantitySold()) {
            return "Not enough stock on hand for sales";
        }

        // Date validation
        if (salesObj.getSalesDate().after(new Date())) {
            return "Invalid date";
        }

        // Generate SalesID
        Calendar cal = Calendar.getInstance();
        cal.setTime(salesObj.getSalesDate());
        int year = cal.get(Calendar.YEAR) % 100;
        String salesId = year + "" + (salesSeq++);
        salesObj.setSalesID(salesId);

        // Insert Sale
        sales.add(salesObj);

        // Update Stock
        product.setQuantityOnHand(product.getQuantityOnHand() - salesObj.getQuantitySold());

        return "Sales Completed";
    }

    // Sales Report
    public ArrayList<SalesReport> getSalesReport() {
        ArrayList<SalesReport> reports = new ArrayList<>();
        for (Sales s : sales) {
            Product p = null;
            for (Product pr : products) {
                if (pr.getProductID().equals(s.getProductID())) {
                    p = pr; break;
                }
            }
            if (p != null) {
                SalesReport r = new SalesReport();
                r.setSalesID(s.getSalesID());
                r.setSalesDate(s.getSalesDate());
                r.setProductID(p.getProductID());
                r.setProductName(p.getProductName());
                r.setQuantitySold(s.getQuantitySold());
                r.setProductUnitPrice(p.getProductUnitPrice());
                r.setSalesPricePerUnit(s.getSalesPricePerUnit());
                r.setProfitAmount(s.getSalesPricePerUnit() - p.getProductUnitPrice());
                reports.add(r);
            }
        }
        return reports;
    }
}