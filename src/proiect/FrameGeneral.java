package proiect;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class FrameGeneral extends JFrame {

    private final JFrame f;
    public static JComboBox cbDatabase, cbTabel;
    private JPanel pNord, pEast, pWest, pSouth;
    private JButton bAlege, bVerifica, bVizualizare;

    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    public FrameGeneral() {
///////////////////////////////////////////////////////////new etc
        f = new JFrame("Main");
        cbDatabase = new JComboBox();
        cbTabel = new JComboBox();
        bVizualizare = new JButton("Vizualizare");
        bAlege = new JButton("Alege");
        bVerifica = new JButton("Verifica");
        pEast = new JPanel();
        pNord = new JPanel();
        pWest = new JPanel();
        pSouth = new JPanel();
////////////////////////////////////////////////////////////add etc        
        cbDatabase.addItem("Db1");
        cbDatabase.addItem("Db2");
        pNord.add(cbDatabase);
        pEast.add(bVizualizare);
        pEast.add(bVerifica);
        pNord.add(bAlege);
        pWest.add(cbTabel);
        f.add(pEast, BorderLayout.EAST);
        f.add(pNord, BorderLayout.NORTH);
        f.add(pWest, BorderLayout.WEST);
        bVerifica.addActionListener(new Verifica());
        bAlege.addActionListener(new btAlege());
        bVizualizare.addActionListener((ActionEvent ae) -> {
            try {
                Vizualizare_Tabel f2 = new Vizualizare_Tabel();
            } catch (SQLException ex) {
                Logger.getLogger(FrameGeneral.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
////////////////////////////////////////////////////////////sets etc

        cbTabel.setVisible(false);
        bVizualizare.setVisible(false);
        bVerifica.setVisible(false);
        cbDatabase.setSelectedItem(null);
        f.setSize(500, 500);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public class btAlege implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String valoarea = (String) cbDatabase.getSelectedItem();
            if ("Db1".equals(valoarea)) {

                try {
                    con = ConnectionManager.getConnectionFirst();
                    stmt = con.createStatement();
                    cbTabel.removeAllItems();
                    rs = stmt.executeQuery("SELECT TABLE_NAME FROM EX1.INFORMATION_SCHEMA.TABLES");
                    while (rs.next()) {
                        cbTabel.addItem(rs.getString(1));
                    }

                    //System.out.println("baza de date 1  =  " + con);
                    ////////////////////////////////////////////////////////////code
                    bVizualizare.setVisible(true);
                    bVerifica.setVisible(true);
                    cbTabel.setVisible(true);

                    con.close();

                } catch (SQLException ex) {
                    Logger.getLogger(FrameGeneral.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if ("Db2".equals(valoarea)) {
                try {
                    con = ConnectionManager.getConnectionSecond();
                    // System.out.println("baza de date 2  =  " + con);
                    stmt = con.createStatement();
                    cbTabel.removeAllItems();
                    rs = stmt.executeQuery("SELECT TABLE_NAME FROM EX2.INFORMATION_SCHEMA.TABLES");
                    while (rs.next()) {
                        cbTabel.addItem(rs.getString(1));
                    }
                    /////////////////////////////////////////////////////code

                    bVizualizare.setVisible(true);
                    bVerifica.setVisible(true);
                    cbTabel.setVisible(true);
                    con.close();
                } catch (SQLException eq) {
                    System.out.print("HZ2");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Alegeti un BD");
            }

            //System.out.println("Ajung si pe aici");
        }
    }

}
