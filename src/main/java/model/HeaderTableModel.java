/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import view.SigView;

/**
 *
 * @author mshaarawy
 */
public class HeaderTableModel extends AbstractTableModel {

    String[] columns = {"Num", "Customer", "Date", "Total"};
    ArrayList<InvoiceHeader> headers;
    SigView frame;

    public HeaderTableModel(ArrayList<InvoiceHeader> headers) {
        this.headers = headers;
    }

    @Override
    public int getRowCount() {
        return headers.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        InvoiceHeader header = headers.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return header.getInvoiceNum();

            case 1:
                return header.getCustomer();
            case 2:
                return SigView.sdf.format(header.getDate());
            case 3:
                return header.getTotal();
        }
        

        return "";
    }

}
