/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author mshaarawy
 */
public class InvoiceLines {

    private int invoiceNum;
    private String itemName;
    private double itemPrice;
    private int count;
    private InvoiceHeader invoiceHeader;

    
    InvoiceLines()
    {}

    public InvoiceLines( String itemName, double itemPrice, int count, InvoiceHeader invoiceHeader) {
//        this.invoiceNum = invoiceNum;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.count = count;
        this.invoiceHeader = invoiceHeader;
    }
    
     public InvoiceLines(int invoiceNum, String itemName, double ItemPrice, int count) {
        this.invoiceNum = invoiceNum;
        this.itemName = itemName;
        this.itemPrice = ItemPrice;
        this.count = count;
    }
    
    public double getItemTotal()
    {
    return count*itemPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getInvoice() {
        return invoiceNum;
    }

    public void setInvoice(int invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double ItemPrice) {
        this.itemPrice = ItemPrice;
    }

    @Override
    public String toString() {
        return "invoiceItem{" + "itemName=" + itemName + ", ItemPrice=" + itemPrice + ", count=" + count + '}';
    }

    public InvoiceHeader getInvoiceHeader() {
        return invoiceHeader;
    }

    public void setInvoiceHeader(InvoiceHeader invoiceHeader) {
        this.invoiceHeader = invoiceHeader;
    }
    
 
    
    
}
