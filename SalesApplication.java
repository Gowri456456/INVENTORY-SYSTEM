package com.wipro.sales.main;

import com.wipro.sales.bean.*;
import com.wipro.sales.service.Administrator;
import java.util.*;

public class SalesApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Administrator admin = new Administrator();

        while (true) {
            System.out.println("\n1. Insert Stock");
            System.out.println("2. Delete Stock");
            System.out.println("3. Insert Sales");
            System.out.println("4. View Sales Report");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    Product p = new Product();
                    System.out.print("Enter Product Name: ");
                    p.setProductName(sc.next());
                    System.out.print("Enter Quantity: ");
                    p.setQuantityOnHand(sc.nextInt());
                    System.out.print("Enter Unit Price: ");
                    p.setUnitPrice(sc.nextDouble());
                    System.out.print("Enter Reorder Level: ");
                    p.setReorderLevel(sc.nextInt());
                    System.out.println("Result: " + admin.insertStock(p));
                    break;

                case 2:
                    System.out.print("Enter Product ID to delete: ");
                    String pid = sc.next();
                    System.out.println("Result: " + admin.deleteStock(pid));
                    break;

                case 3:
                    Sales s = new Sales();
                    System.out.print("Enter Product ID: ");
                    s.setProductID(sc.next());
                    System.out.print("Enter Quantity Sold: ");
                    s.setQuantitySold(sc.nextInt());
                    System.out.print("Enter Sales Price per Unit: ");
                    s.setSalesPricePerUnit(sc.nextDouble());
                    s.setSalesDate(new Date());
                    System.out.println("Result: " + admin.insertSales(s));
                    break;

                case 4:
                    ArrayList<SalesReport> list = admin.getSalesReport();
                    for (SalesReport r : list) {
                        System.out.println(r.getSalesID() + " | " + r.getProductName() + 
                            " | Qty: " + r.getQuantitySold() +
                            " | Profit: " + r.getProfitAmount());
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    return;
            }
        }
    }
}
