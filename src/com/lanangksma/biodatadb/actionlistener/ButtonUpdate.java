package com.lanangksma.biodatadb.actionlistener;

import com.lanangksma.biodatadb.biodata.Biodata;
import com.lanangksma.biodatadb.dao.BiodataDao;
import com.lanangksma.biodatadb.main.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonUpdate implements ActionListener {
    private MainFrame mainFrame;
    private BiodataDao biodataDao;

    public ButtonUpdate(MainFrame mainFrame, BiodataDao biodataDao) {
        this.mainFrame = mainFrame;
        this.biodataDao = biodataDao;
    }

    private void getTable() {
        JTable table = this.mainFrame.getTable();
        int row = table.getSelectedRow();
        
        String nama = this.mainFrame.getNama();
        String alamat = this.mainFrame.getAlamat();
        String noHp = this.mainFrame.getNoHp();
        String jenisKelamin = this.mainFrame.getJenisKelamin();
        String status = this.mainFrame.getStatus();

        Biodata biodata = new Biodata();
        biodata.setNama(nama);
        biodata.setAlamat(alamat);
        biodata.setNoHp(noHp);
        biodata.setJenisKelamin(jenisKelamin);
        biodata.setStatus(status);

        this.biodataDao.update(biodata);
        this.mainFrame.updateBiodata(biodata, row);
        this.mainFrame.clearForm();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (mainFrame.getNama().isEmpty() || mainFrame.getAlamat().isEmpty() || mainFrame.getNoHp().isEmpty() || mainFrame.getJenisKelamin().isEmpty() || mainFrame.getStatus().isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields must be filled!");
        }
        else {
            getTable();
        }
        
    }
}


