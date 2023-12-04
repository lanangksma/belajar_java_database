package com.lanangksma.biodatadb.biodata;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class BiodataTableModel extends AbstractTableModel{
    private String[] columnNames = {"ID", "Nama", "Alamat", "No HP", "Jenis Kelamin", "Status"};
    private List<Biodata> data;

    public BiodataTableModel(List<Biodata> data) {
        this.data = data;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        Biodata rowItem = data.get(row);
        return switch (col) {
            case 0 -> rowItem.getId();
            case 1 -> rowItem.getNama();
            case 2 -> rowItem.getAlamat();
            case 3 -> rowItem.getNoHp();
            case 4 -> rowItem.getJenisKelamin();
            case 5 -> rowItem.getStatus();
            default -> "";
        };
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return true;
    }

    public void add(Biodata value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void set(int row, int col, Biodata value) {
        Biodata rowItem = data.get(row);
        data.set(row, rowItem);
        fireTableRowsUpdated(row, row);
    }

    public void update(int row, Biodata value) {
        Biodata rowItem = data.get(row);
        rowItem.setNama(value.getNama());
        rowItem.setAlamat(value.getAlamat());
        rowItem.setNoHp(value.getNoHp());
        rowItem.setJenisKelamin(value.getJenisKelamin());
        rowItem.setStatus(value.getStatus());
        fireTableRowsUpdated(row, row);
    }

    public List<Biodata> getData() {
        return data;
    }

    public void delete(int row) {
        data.remove(row);
        fireTableRowsDeleted(row, row);
    }
}
