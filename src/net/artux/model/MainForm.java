package net.artux.model;

import net.artux.Application;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainForm extends JFrame implements MainUI{
    private JPanel rootPanel;

    private JTextField aField;
    private JTextField bField;
    private JTextArea textArea1;
    private JButton тактButton;
    private JButton сбросButton;
    private JCheckBox автоматическиCheckBox;
    private JSlider slider1;
    private JPanel contentPanel;
    private JPanel aContent;
    private JPanel bContent;
    private JPanel aResultContent;
    private JPanel bResultContent;
    private JPanel cResultContent;
    private JPanel chResultContent;
    private JPanel managePanel;

    private JTable aTable;
    private JTable bTable;

    private BufferedImage image;

    public MainForm(){
        setContentPane(rootPanel);
        setSize(1400, 850);
        setLocationByPlatform(true);

        try {
            image = ImageIO.read(new File("resources/22.bmp"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        contentPanel.add(new JLabel(new ImageIcon(image)));
        aTable = initTable(aContent, 16);
        aTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = aTable.rowAtPoint(e.getPoint());
                int col = aTable.columnAtPoint(e.getPoint());
                if (row >= 0 && col >= 0) {
                    Application.dataModel.flip(0, col);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        bTable = initTable(bContent, 16);
        bTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = bTable.rowAtPoint(e.getPoint());
                int col = bTable.columnAtPoint(e.getPoint());
                if (row >= 0 && col >= 0) {
                    Application.dataModel.flip(1, col);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        //initTable(bTable, 16);

    }

    JTable initTable(JPanel panel, int n){

        JTable table = new JTable();
        panel.add(table, BorderLayout.CENTER);
        panel.add(table.getTableHeader(), BorderLayout.NORTH);

        Integer[][] arr = new Integer[1][n];
        String[] colNames = new String[n];

        for (int i = 0; i < n; i++) {
            colNames[i] = (n-i -1) + "";
            arr[0][i] = 0;
        }

        DefaultTableModel tableModel = new DefaultTableModel(arr, colNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };


        table.setModel(tableModel);
        //table.setAutoResizeMode();
        table.setCellSelectionEnabled(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        return table;
    }

    @Override
    public void updateInput(BitSet aSet, BitSet bSet) {
        for (int i = 0; i < aSet.getSize(); i++) {
            aTable.getModel().setValueAt(aSet.get(i) ? 1 : 0, 0, i);
        }
        for (int i = 0; i < bSet.getSize(); i++) {
            bTable.getModel().setValueAt(bSet.get(i) ? 1 : 0, 0, i);
        }
        aField.setText(aSet.getInt(true) + "");
        bField.setText(bSet.getInt(true) + "");
    }

    @Override
    public void updateResults(DataModel dataModel) {

    }
}
