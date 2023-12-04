package com.lanangksma.biodatadb.actionlistener;

import com.lanangksma.biodatadb.biodata.Biodata;
import com.lanangksma.biodatadb.dao.BiodataDao;
import com.lanangksma.biodatadb.main.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class ButtonAdd implements ActionListener {
    private MainFrame mainFrame;
    private BiodataDao biodataDao;

    public ButtonAdd(MainFrame mainFrame, BiodataDao biodataDao) {
        this.mainFrame = mainFrame;
        this.biodataDao = biodataDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(isValidForm()){
            Biodata biodata = createNewBiodata();
            mainFrame.addBiodata(biodata);
            biodataDao.insert(biodata);
            mainFrame.clearForm();
        }
    }

    private boolean isValidForm(){
        if( checkFieldEmpty(mainFrame.getNama(),"Harap isi nama!") || 
            checkFieldEmpty(mainFrame.getNoHp(),"Harap isi nomor hp!") ||
            checkFieldEmpty(mainFrame.getJenisKelamin(), "Harap pilih jenis kelamin!") ||
            checkFieldEmpty(mainFrame.getAlamat(), "Harap isi alamat!")) 
            return false; 

        if (JOptionPane.showConfirmDialog(mainFrame, "Apakah Anda yakin ingin menyimpan data ini?", "YES_NO_OPTION", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(mainFrame, "Proses Dibatalkan!", "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        JOptionPane.showMessageDialog(mainFrame, "Proses Berhasil!", "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }

    private boolean checkFieldEmpty(String fieldContent, String errorMessage) {
        if(fieldContent.isEmpty()){
            JOptionPane.showMessageDialog(mainFrame, errorMessage, "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
            return true;
        }
        return false;
    }

    private Biodata createNewBiodata(){
        Biodata biodata = new Biodata();
        biodata.setId(UUID.randomUUID().toString());
        biodata.setNama(mainFrame.getNama());
        biodata.setNoHp(mainFrame.getNoHp());
        biodata.setJenisKelamin(mainFrame.getJenisKelamin());
        biodata.setStatus(mainFrame.getStatus());
        biodata.setAlamat(mainFrame.getAlamat());
        return biodata;
    }
}
