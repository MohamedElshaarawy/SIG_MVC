/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author mshaarawy
 */
public class FileOperations {

    private String invoiceHeaderfilePath  ="C:\\Users\\mshaarawy\\Desktop\\InvoiceHeader.csv";
    private String invoiceLinesfilePath   ="C:\\Users\\mshaarawy\\Desktop\\InvoiceLine.csv";

    static  DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);

    static ArrayList<InvoiceHeader> invoiceHeaders;

    public ArrayList<InvoiceHeader> readFile() throws FileNotFoundException, IOException {
        FileReader invoiceHeaderfileReader = new FileReader(invoiceHeaderfilePath);
        FileReader invoicelinesFileReader = new FileReader(invoiceLinesfilePath);
        
        BufferedReader invoicesHeaderBufferedReader = new BufferedReader(invoiceHeaderfileReader);
        BufferedReader invoiceLinesBufferedReader = new BufferedReader(invoicelinesFileReader);

        
        String invoiceHeaderFileline = null;
        String invoiceLinesFileLine = null;
        
        int headerRow = 0;
        int linesRow = 0;
        
        ArrayList<InvoiceHeader> invoiceHeader = new ArrayList<>();       
        ArrayList<InvoiceLines> invoiceLines = new ArrayList<>();
        
        
        
        

       
        while((invoiceHeaderFileline = invoicesHeaderBufferedReader.readLine())!=null)
        {
            String[] invoiceHeadersplitedLine = invoiceHeaderFileline.split(",");
            
            while((invoiceLinesFileLine = invoiceLinesBufferedReader.readLine())!=null)
            {
             String[] invoiceLinesSplitedLine = invoiceLinesFileLine.split(",");

             if((Integer.parseInt(invoiceHeadersplitedLine[0]))==(Integer.parseInt(invoiceLinesSplitedLine[0])))
                    {
                        
                        
                        invoiceLines.add(new InvoiceLines((Integer.parseInt(invoiceLinesSplitedLine[0])),
                                                           (invoiceLinesSplitedLine[1]),
                                                             (Double.parseDouble(invoiceLinesSplitedLine[2])),
                                                              (Integer.parseInt(invoiceLinesSplitedLine[3]))));
                                            // System.out.println("invoicelines "+invoiceLines.toString());

                    }
                 
            }
            invoiceHeader.add(new InvoiceHeader(Integer.parseInt(invoiceHeadersplitedLine[0]),
                                            LocalDate.parse(invoiceHeadersplitedLine[1],dateTimeFormatter),
                                                  invoiceHeadersplitedLine[2],
                                                    invoiceLines));
                                                   //      System.out.println("invoicelines 2 "+invoiceHeader.toString());
System.out.println(invoiceLines.toString());
          
       
        }
        
        invoicesHeaderBufferedReader.close();
        invoiceLinesBufferedReader.close();
        
        return invoiceHeader;   
        
    }
    

//    public void writeFile(ArrayList<InvoiceHeader> invoiceHeaders) throws IOException {
//        
//        FileWriter fileWriter = new FileWriter(new File(invoiceHeaderfilePath));
//       
//        try {
//
//            TableModel modelInvoiceDetails = table.getModel();
//            FileWriter csv = new FileWriter(new File(path));
//
//            //            for (int i = 0; i < model.getColumnCount(); i++) {
//            //                csv.write(model.getColumnName(i) + ",");
//            //            }
//            //
//            //            csv.write("\n");
//
//            for (int i = 0; i < modelInvoiceDetails.getRowCount(); i++) {
//                for (int j = 0; j < modelInvoiceDetails.getColumnCount(); j++) {
//                    try {
//                        csv.write(modelInvoiceDetails.getValueAt(i, j).toString() + ",");
//                    } catch (NullPointerException e) {
//
//                    }
//                }
//                csv.write("\n");
//
//            }
//
//            JOptionPane.showMessageDialog(null, "Saved Successfully", "Info",
//                    JOptionPane.INFORMATION_MESSAGE);
//            csv.close();
//        } catch (IOException e) {
//            System.out.println("Error " + e);
//        }
//
//
//    }
   
    public void setInvoiceHeaderfilePath(String invoiceHeaderfilePath) {
        this.invoiceHeaderfilePath = invoiceHeaderfilePath;
    }

    public void setInvoiceLinesfilePath(String invoiceLinesfilePath) {
        this.invoiceLinesfilePath = invoiceLinesfilePath;
    }
    public static void main(String[] args) throws IOException {

//        InvoiceLines invoiceLines = new InvoiceLines("cat", 5, 0);
//        System.out.println(invoiceLines);
                System.out.println(LocalDate.parse("10-09-2022",dateTimeFormatter));
                
                FileOperations fileOperations = new FileOperations();
                System.out.println(fileOperations.readFile());

    }



}
