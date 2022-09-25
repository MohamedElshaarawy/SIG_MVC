/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author mshaarawy
 */
public class LinesTableModel extends AbstractTableModel {

    @Override
    public int getRowCount() {
        return 3;
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int column) {
        return "column";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return "123";
    }

}
