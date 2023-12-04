package com.lanangksma.biodatadb.main;

import com.lanangksma.biodatadb.actionlistener.ButtonAdd;
import com.lanangksma.biodatadb.actionlistener.ButtonDelete;
import com.lanangksma.biodatadb.actionlistener.ButtonExport;
import com.lanangksma.biodatadb.actionlistener.ButtonUpdate;
import com.lanangksma.biodatadb.biodata.Biodata;
import com.lanangksma.biodatadb.biodata.BiodataTableModel;
import com.lanangksma.biodatadb.dao.BiodataDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class MainFrame extends JFrame {
    private BiodataTableModel tableModel;
    private JLabel namaLabel, alamatLabel, noHpLabel, jenisKelaminLabel, statusLabel;
    private JTextField namaTextField, noHpTextField;
    private JTextArea alamatTextArea;
    private JRadioButton lakiLakiRadioButton, perempuanRadioButton;
    private JCheckBox statusCheckBox;
    private JButton simpanButton, ubahButton, hapusButton, exportButton;
    private JTable table;
    private JScrollPane scrollableTable;

    public MainFrame(BiodataDao biodataDao) {
        super();

        initComponents(biodataDao);
        prepareWindowCloseEvent();
    }

    private void initComponents(BiodataDao biodataDao) {
        List<Biodata> biodataList = biodataDao.getAll();

        namaLabel = new JLabel("Nama");
        noHpLabel = new JLabel("No HP");
        jenisKelaminLabel = new JLabel("Jenis Kelamin");
        statusLabel = new JLabel("Status");
        alamatLabel = new JLabel("Alamat");

        namaTextField = new JTextField();
        noHpTextField = new JTextField();

        lakiLakiRadioButton = new JRadioButton("Laki-laki");
        perempuanRadioButton = new JRadioButton("Perempuan");

        ButtonGroup bg = new ButtonGroup();
        bg.add(lakiLakiRadioButton);
        bg.add(perempuanRadioButton);

        statusCheckBox = new JCheckBox("Warga Negara Asing");
        alamatTextArea = new JTextArea();

        simpanButton = new JButton("Simpan");
        ubahButton = new JButton("Ubah");
        hapusButton = new JButton("Hapus");
        exportButton = new JButton("Export");

        table = new JTable();
        scrollableTable = new JScrollPane(table);

        tableModel = new BiodataTableModel(biodataList);
        table.setModel(tableModel);

        ButtonAdd addActionListener = new ButtonAdd(this, biodataDao);
        ButtonUpdate updateActionListener = new ButtonUpdate(this, biodataDao);
        ButtonDelete deleteActionListener = new ButtonDelete(this, biodataDao, tableModel);
        ButtonExport exportActionListener = new ButtonExport(this, biodataDao);

        simpanButton.addActionListener(addActionListener);
        ubahButton.addActionListener(updateActionListener);
        hapusButton.addActionListener(deleteActionListener);
        exportButton.addActionListener(exportActionListener);

        addComponentsToFrame();
    }

    private void prepareWindowCloseEvent() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handleCloseEvent();
            }
        });
    }

    private void handleCloseEvent() {
        if (JOptionPane.showConfirmDialog(
                MainFrame.this,
                "Apakah anda yakin ingin keluar?",
                "Exit", JOptionPane.YES_NO_OPTION
        ) == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }

    private void addComponentsToFrame() {
        int textFieldWidth = 500;
        int textFieldHeight = 30;

        addComponent(namaLabel, 15, 40, textFieldWidth, 10);
        addComponent(namaTextField, 15, 60, textFieldWidth, textFieldHeight);

        addComponent(noHpLabel, 15, 100, textFieldWidth, 10);
        addComponent(noHpTextField, 15, 120, textFieldWidth, textFieldHeight);

        addComponent(jenisKelaminLabel, 15, 160, textFieldWidth, 10);
        addComponent(lakiLakiRadioButton, 15, 180, textFieldWidth, 20);
        addComponent(perempuanRadioButton, 15, 210, textFieldWidth, 20);

        addComponent(statusLabel, 15, 240, textFieldWidth, 10);
        addComponent(statusCheckBox, 15, 260, textFieldWidth, 20);

        addComponent(alamatLabel, 15, 290, textFieldWidth, 10);
        addComponent(alamatTextArea, 15, 310, textFieldWidth, 90);

        addComponent(simpanButton, 15, 410, 100, 40);
        addComponent(ubahButton, 120, 410, 100, 40);
        addComponent(hapusButton, 225, 410, 100, 40);
        addComponent(exportButton, 330, 410, 100, 40);

        addComponent(scrollableTable, 15, 460, textFieldWidth, 200);

        this.setSize(550, 800);
        this.setLayout(null);
        this.setVisible(true);
    }

    private void addComponent(Component component, int x, int y, int width, int height) {
        component.setBounds(x, y, width, height);
        this.add(component);
    }

    public String getNama() {
        return this.namaTextField.getText();
    }

    public String getAlamat() {
        return this.alamatTextArea.getText();
    }

    public String getNoHp() {
        return this.noHpTextField.getText();
    }

    public String getJenisKelamin() {
        if(this.lakiLakiRadioButton.isSelected()) {
            return "Laki-laki";
        }
        else if(this.perempuanRadioButton.isSelected()) {
            return "Perempuan";
        }
        else {
            return null;
        }
    }

    public String getStatus() {
        if(this.statusCheckBox.isSelected()) {
            return "WNA";
        }
        else {
            return "WNI";
        }
    }

    public JTable getTable() {
        return this.table;
    }

    public void clearForm() {
        this.namaTextField.setText("");
        this.alamatTextArea.setText("");
        this.noHpTextField.setText("");
        this.lakiLakiRadioButton.setSelected(false);
        this.perempuanRadioButton.setSelected(false);
        this.statusCheckBox.setSelected(false);
    }

    public void addBiodata(Biodata biodata) {
        this.tableModel.add(biodata);
    }

    public void updateBiodata(Biodata biodata, int row) {
        this.tableModel.update(row, biodata);
    }

    public void deleteBiodata() {
        this.tableModel.delete(this.table.getSelectedRow());
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            MainFrame f = new MainFrame(new BiodataDao());
            f.setVisible(true);
        });
    }
}
