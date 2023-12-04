package com.lanangksma.biodatadb.actionlistener;

import com.lanangksma.biodatadb.biodata.Biodata;
import com.lanangksma.biodatadb.dao.BiodataDao;
import com.lanangksma.biodatadb.main.MainFrame;
import com.lanangksma.biodatadb.utils.SaveToFile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ButtonExport implements ActionListener {
    private MainFrame mainFrame;
    private BiodataDao biodataDao;

    public ButtonExport(MainFrame mainFrame, BiodataDao biodataDao) {
        this.mainFrame = mainFrame;
        this.biodataDao = biodataDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Simpan ke File");

        int userSelection = fileChooser.showSaveDialog(mainFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File sv = fileChooser.getSelectedFile();
            saveToFile(sv);
        }
    }

    private void saveToFile(File file) {
        // Proses penyimpanan ke file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            // Tulis header ke file
            writer.write("Nama, No HP, Jenis Kelamin, Alamat");
            writer.newLine();

            // Ambil data dari tabel dan tulis ke file
            for (Biodata biodata : biodataDao.getAll()) {
                writer.write(biodata.getNama() + ", ");
                writer.write(biodata.getNoHp() + ", ");
                writer.write(biodata.getJenisKelamin() + ", ");
                writer.write(biodata.getAlamat());
                writer.newLine();
            }

            JOptionPane.showMessageDialog(mainFrame, "Data berhasil disimpan ke file.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(mainFrame, "Gagal menyimpan data ke file.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

}
