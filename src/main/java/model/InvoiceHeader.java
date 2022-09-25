/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import javax.crypto.spec.IvParameterSpec;

/**
 *
 * @author mshaarawy
 */
public class InvoiceHeader{

   private int invoiceNum;
   private LocalDate date;
   private String customer;
   private ArrayList<InvoiceLines> invoiceLines;
   
   
   
        InvoiceHeader() {}


     InvoiceHeader(int invoiceNum, LocalDate date, String customer) {
        this.invoiceNum = invoiceNum;
        this.date = date;
        this.customer = customer;
    }
     
        InvoiceHeader(int invoiceNum, LocalDate date, String customer, ArrayList<InvoiceLines> invoiceLineses) {
        this.invoiceNum = invoiceNum;
        this.date = date;
        this.customer = customer;
    }
     
     public int getInvoieNum()
     {
     return invoiceNum;
     }
    
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }


    
    public double getInvoiceTotal(){
        double invoiceTotal=0.0;
        for(InvoiceLines invoiceLines:invoiceLines)
            invoiceTotal +=invoiceLines.getItemTotal();
        
        return invoiceTotal;
       
    }
    
    public ArrayList<InvoiceLines> getLines()
    {
        if(invoiceLines==null){
            invoiceLines=new ArrayList<>();
                    }
        
    return invoiceLines;
    }
    
    

    @Override
    public String toString() {
    
        return "Invoice{" + "invoiceNum=" + invoiceNum + ", date=" + date + ", customer= " + customer + ", invoicesLines " +  invoiceLines ;
        
    }
    
    
    
    
}
