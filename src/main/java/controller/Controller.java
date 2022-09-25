/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
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
    InvoiceHeader invoiceModel;
    private SigView frame;

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

            case "Save":
                save();
                break;

            case "Cancel":
                cancel();
                break;

            case "Load File":
            {
                try {
                    loadFile(null, null);
                } catch (IOException ex) {
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
    }

    private void deleteInvoice() {
    }

    private void save() {
    }

    private void cancel() {
    }

    public void loadFile(String invoiceHeaderfilePath, String invoiceLinesfilePath) throws IOException {
        File headerFile = null;
        File lineFile = null;
        if (invoiceHeaderfilePath == null && invoiceLinesfilePath == null) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                headerFile = fileChooser.getSelectedFile();
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
        
        List<String> lineLines = Files.lines(Paths.get(headerFile.getAbsolutePath())).collect(Collectors.toList());

        
        
        }

    }

    private void saveFile() {
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int rowIndex = frame.getHeaderTable().getSelectedRow();
        if (rowIndex != -1) {

//          InvoiceHeader header = frame.getHeaderTable().get(rowIndex);
//          frame.setLinesTableModel((new LinesTableModel(header.getLines()));
        }
    }

}
