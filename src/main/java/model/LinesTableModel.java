/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author mshaarawy
 */
public class LinesTableModel extends AbstractTableModel {

    private String[] column = {"Item", "Unit Price", "Count", "Total"};

    private ArrayList<InvoiceLines> lines;

    public LinesTableModel(ArrayList<InvoiceLines> lines) {
        this.lines = lines;
    }

    @Override
    public int getRowCount() {
        return lines.size();
    }

    @Override
    public int getColumnCount() {
        return column.length;
    }

    @Override
    public String getColumnName(int columns) {
        return column[columns];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        InvoiceLines line = lines.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return line.getItemName();

            case 1:
                return line.getItemPrice();
            case 2:
                return line.getCount();
            case 3:
                return line.getItemTotal();
        }

        return "";
    }

}
