/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author mshaarawy
 */
public class InvoiceHeader {

    private int invoiceNum;
    private Date date;
    private String customer;
    private ArrayList<InvoiceLines> invoiceLines;

   public InvoiceHeader(int invoiceNum, Date date, String customer) {
        this.invoiceNum = invoiceNum;
        this.date = date;
        this.customer = customer;
    }

    

    public int getInvoiceNum() {
        return invoiceNum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public double getTotal() {
        double invoiceTotal = 0.0;
        for (int i=0; i <getLines().size(); i++) {
           InvoiceLines line  = getLines().get(i);
           invoiceTotal += line.getItemTotal();
        }

        return invoiceTotal;

    }

    public ArrayList<InvoiceLines> getLines() {
        if (invoiceLines == null) {
            invoiceLines = new ArrayList<>();
        }

        return invoiceLines;
    }

    @Override
    public String toString() {

        return "Invoice{" + "invoiceNum=" + invoiceNum + ", date=" + date + ", customer= " + customer + ", invoicesLines " + invoiceLines;

    }

}
