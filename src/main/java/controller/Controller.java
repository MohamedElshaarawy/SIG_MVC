/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.Box;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import model.HeaderTableModel;
import model.InvoiceLines;
import model.InvoiceHeader;
import model.LinesTableModel;
import view.SigView;

/**
 *
 * @author mshaarawy
 */
public class Controller implements ActionListener, ListSelectionListener {

    InvoiceLines invoiceItemModel;

    private SigView frame;
    InvoiceHeader invoiceHeader;
    InvoiceLines invoiceLines;
    HeaderTableModel headerTableModel;
//    int headrowIndex;

    public Controller(SigView frame) {
        this.frame = frame;
    }

    public void ivoiceinit() {
        DefaultTableModel invoiceModel = new DefaultTableModel();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Action Fired");
        String command = e.getActionCommand();
        switch (command) {

            case "Create New Invoice":
                createNewInvoice();
                break;

            case "Delete Invoice":
                deleteInvoice();
                break;

            case "Create New Line":
                createNewLine();
                break;

            case "Delete Line":
                deleteLine();
                break;

            case "Load File": {
                try {
                    loadFile(null, null);
                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

            case "Save File":
                saveFile();
                break;

        }
    }

    private void createNewInvoice() {
        JTextField customerTxt = new JTextField(5);
        JTextField dateTxt = new JTextField(5);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Customer:"));
        myPanel.add(customerTxt);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Date:"));
        myPanel.add(dateTxt);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Please Enter Invoice", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int invoiceNum = frame.getInvoices().size() + 1;
            String customer = customerTxt.getText();
            Date date;
            try {
                date = SigView.sdf.parse(dateTxt.getText());
                InvoiceHeader inv = new InvoiceHeader(invoiceNum, date, customer);
                frame.getInvoices().add(inv);
//            Date date =
            } catch (ParseException ex) {

                JOptionPane.showMessageDialog(frame, "Invalid Date Format \n"
                        + "Correct format is dd-MM-yyyy");
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }

            frame.setHeaderTableModel(new HeaderTableModel(frame.getInvoices()));

//            frame.getInvoices().add(invoiceHeader)
            System.out.println("Customer value: " + customerTxt.getText());
            System.out.println("Date value: " + dateTxt.getText());
        }
    }

    private void deleteInvoice() {

        int rowIndex = frame.getHeaderTable().getSelectedRow();
                if (rowIndex == -1) {
            JOptionPane.showMessageDialog(frame, "Select Line Please!");
        }else{

            int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete selected Invoice?");
            if (result == JOptionPane.OK_OPTION) {
                frame.getInvoices().remove(rowIndex);

                frame.setHeaderTableModel(new HeaderTableModel(frame.getInvoices()));
                frame.setLinesTableModel(new LinesTableModel(frame.getLines()));
            }

        }
    }

    private void deleteLine() {

        int headrowIndex = frame.getHeaderTable().getSelectedRow();

        int linerowIndex = frame.getLineTable().getSelectedRow();
        if (linerowIndex == -1) {
            JOptionPane.showMessageDialog(frame, "Select Line Please!");
        }
        {

            if (linerowIndex != -1) {

                int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want "
                        + "to delete selected Line?");
                if (result == JOptionPane.OK_OPTION) {
                    var invoice = frame.getInvoices().get(headrowIndex);
                    invoice.getLines().remove(linerowIndex);

                    var selectedInvoice = frame.getInvoices().get(headrowIndex);
                    frame.setLinesTableModel(new LinesTableModel(selectedInvoice.getLines()));
                    frame.setLinesTableModel(new LinesTableModel(invoice.getLines()));
                    frame.setCustomerNameTxt(invoice.getCustomer());
                    frame.getInvoiceDateTxt().setText(SigView.sdf.format(invoice.getDate()));
                    frame.getInvoiceNumLbL().setText("" + invoice.getInvoiceNum());
                    frame.getInvoiceTatolLbl().setText("" + invoice.getTotal());
                }

            }
        }
    }

    private void createNewLine() {

        int headrowIndex = frame.getHeaderTable().getSelectedRow();
        if (headrowIndex == -1) {
            JOptionPane.showMessageDialog(frame, "Please select Invoice First");
        } else {

            JTextField itemTxt = new JTextField(5);
            JTextField unitPriceTxt = new JTextField(5);
            JTextField countTxt = new JTextField(5);

            JPanel myPanel = new JPanel();
            myPanel.add(new JLabel("Item:"));
            myPanel.add(itemTxt);
            myPanel.add(Box.createHorizontalStrut(15)); // a spacer
            myPanel.add(new JLabel("Unit Price:"));
            myPanel.add(unitPriceTxt);
            myPanel.add(Box.createHorizontalStrut(15)); // a spacer

            myPanel.add(new JLabel("Count:"));
            myPanel.add(countTxt);

            int result = JOptionPane.showConfirmDialog(null, myPanel,
                    "Please Enter Line", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                // int lineNum = frame.getLines().size() + 1;
                String itemName = itemTxt.getText();
                double unitPrice = Double.parseDouble(unitPriceTxt.getText());
                int count = Integer.parseInt(countTxt.getText());

                InvoiceHeader inv = frame.getInvoices().get(headrowIndex);

                InvoiceLines invoiceLine = new InvoiceLines(itemName,
                        unitPrice, count, inv);
                inv.getLines().add(invoiceLine);

                var selectedInvoice = frame.getInvoices().get(headrowIndex);
//                frame.setHeaderTableModel(new HeaderTableModel(frame.getInvoices()));// to prevent remove selection

                frame.setLinesTableModel(new LinesTableModel(selectedInvoice.getLines()));

                frame.setLinesTableModel(new LinesTableModel(inv.getLines()));
                frame.setCustomerNameTxt(inv.getCustomer());
                frame.getInvoiceDateTxt().setText(SigView.sdf.format(inv.getDate()));
                frame.getInvoiceNumLbL().setText("" + inv.getInvoiceNum());
                frame.getInvoiceTatolLbl().setText("" + inv.getTotal());

//            
            }
        }

    }

    public void loadFile(String invoiceHeaderfilePath, String invoiceLinesfilePath) throws IOException, ParseException {
        File headerFile = null;
        File lineFile = null;
        if (invoiceHeaderfilePath == null && invoiceLinesfilePath == null) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select Header File");
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                headerFile = fileChooser.getSelectedFile();
                fileChooser.setDialogTitle("Select Lines File");
                result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    lineFile = fileChooser.getSelectedFile();
                }
            }
        } else {
            headerFile = new File(invoiceHeaderfilePath);
            lineFile = new File(invoiceLinesfilePath);
        }

        if (headerFile != null && lineFile != null) {
            List<String> headerLines = Files.lines(Paths.get(headerFile.getAbsolutePath())).collect(Collectors.toList());

            List<String> lineLines = Files.lines(Paths.get(lineFile.getAbsolutePath())).collect(Collectors.toList());
            frame.getInvoices().clear();
            try {
                
            for (String headerLine : headerLines) {
                String[] parts = headerLine.split(",");
                String numString = parts[0];
                String dateString = parts[1];
                String name = parts[2];
                int num = Integer.parseInt(numString);
                Date date = SigView.sdf.parse(dateString);
                InvoiceHeader inv = new InvoiceHeader(num, date, name);
                frame.getInvoices().add(inv);
            }
            for (String lineline : lineLines) {
                String[] parts = lineline.split(",");
                String invoiceNumStr = parts[0];
                String itemName = parts[1];
                String itemPriceStr = parts[2];
                String countStr = parts[3];

                var invoiceNum = Integer.parseInt(invoiceNumStr);
                double itemPrice = Double.parseDouble(itemPriceStr);
                int count = Integer.parseInt(countStr);
                InvoiceHeader inv = frame.getInvoiceByNum(invoiceNum);
                InvoiceLines invoiceLine = new InvoiceLines(itemName, itemPrice, count, inv);
                inv.getLines().add(invoiceLine);
                frame.setHeaderTableModel(new HeaderTableModel(frame.getInvoices()));
                frame.setLinesTableModel(new LinesTableModel(frame.getLines()));

            }
//            System.out.println("Check Point");
            
            } catch (NumberFormatException | ParseException e) {
                System.err.println("Error Happend");
            }

        }

    }

    private void saveFile() {

        String headerString = "";
        String lineString = "";
        var headers = frame.getInvoices();

        for (InvoiceHeader header : headers) {
            headerString += "" + header.getInvoiceNum() + ","
                    +  SigView.sdf.format(header.getDate())+ ","
                    +  header.getCustomer()  + "\n";
            for (InvoiceLines line : header.getLines()) {
                lineString += "" + line.getInvoiceHeader().getInvoiceNum()
                        + "," + line.getItemName()
                        + "," + line.getItemPrice()
                        + "," + line.getCount() + "\n";

            }

        }
        System.out.println(headerString);
        System.out.println(lineString);
        JFileChooser fc = new JFileChooser();
        int result = fc.showSaveDialog(frame);
        if(result == JFileChooser.APPROVE_OPTION)
        {
            FileWriter fileWriter = null;
            try {
                String path = fc.getSelectedFile().getPath();
                fileWriter = new FileWriter(new File(path));
                fileWriter.write(headerString);
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                try {
                    fileWriter.close();
                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        
        }
         result = fc.showSaveDialog(frame);
        if(result == JFileChooser.APPROVE_OPTION)
        {
            FileWriter fileWriter = null;
            try {
                String path = fc.getSelectedFile().getPath();
                fileWriter = new FileWriter(new File(path));
                fileWriter.write(lineString);
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                try {
                    fileWriter.close();
                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        
        }
        

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int rowIndex = frame.getHeaderTable().getSelectedRow();
        if (rowIndex != -1) {

            InvoiceHeader header = frame.getInvoices().get(rowIndex);
            frame.setLinesTableModel(new LinesTableModel(header.getLines()));
            frame.setCustomerNameTxt(header.getCustomer());
            frame.getInvoiceDateTxt().setText(SigView.sdf.format(header.getDate()));
            frame.getInvoiceNumLbL().setText("" + header.getInvoiceNum());
            frame.getInvoiceTatolLbl().setText("" + header.getTotal());

        }
    }

}
