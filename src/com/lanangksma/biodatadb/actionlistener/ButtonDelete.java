package com.lanangksma.biodatadb.actionlistener;

import com.lanangksma.biodatadb.biodata.BiodataTableModel;
import com.lanangksma.biodatadb.dao.BiodataDao;
import com.lanangksma.biodatadb.main.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonDelete implements ActionListener {
    private final MainFrame mainFrame;
    private final BiodataDao biodataDao;
    private final BiodataTableModel tableModel;

    public ButtonDelete(MainFrame mainFrame, BiodataDao biodataDao, BiodataTableModel tableModel) {
        this.mainFrame = mainFrame;
        this.biodataDao = biodataDao;
        this.tableModel = tableModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(isSelectedRowRemoved()) {
            String id = getSelectedRowId();
            String name = getSelectedRowName();
            showMessage("Proses Berhasil!" + name, "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
            deleteRowInTableAndDatabase(id);
        }
    }

    private boolean isSelectedRowRemoved() {
        if (isRowSelected()) {
            showMessage("Harap pilih baris yang ingin dihapus!", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if(isDeleteConfirmed()) {
            showMessage("Proses Dibatalkan!", "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        return true;
    }

    private boolean isRowSelected() {
        return mainFrame.getTable().getSelectedRow() == -1;
    }

    private boolean isDeleteConfirmed() {
        int confirmStatus = JOptionPane.showConfirmDialog(mainFrame, "Apakah anda yakin ingin menghapus data ini?", "YES_NO_OPTION", JOptionPane.YES_NO_OPTION);
        return confirmStatus != JOptionPane.YES_OPTION;
    }

    private void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(mainFrame, message, title, messageType);
    }

    private String getSelectedRowId() {
        return mainFrame.getTable().getValueAt(mainFrame.getTable().getSelectedRow(), 0).toString();
    }

    private String getSelectedRowName() {
        return mainFrame.getTable().getValueAt(mainFrame.getTable().getSelectedRow(), 1).toString();
    }

    private void deleteRowInTableAndDatabase(String id) {
        mainFrame.deleteBiodata();
        biodataDao.delete(id);
        mainFrame.clearForm();
    }
}
