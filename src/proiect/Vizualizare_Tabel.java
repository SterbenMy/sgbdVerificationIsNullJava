package proiect;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Vizualizare_Tabel extends JFrame {

    private JFrame f;
    private JPanel p;
    private JTable t;
    public int colN, rowN;
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private JScrollPane sp;
    private String sql;

    public Vizualizare_Tabel() throws SQLException {
        String valoarea = (String) FrameGeneral.cbTabel.getSelectedItem();
        String valoareaDb = (String) FrameGeneral.cbDatabase.getSelectedItem();
        if ("Clienti".equals(valoarea) && "Db1".equals(valoareaDb)) {
            con = ConnectionManager.getConnectionFirst();
            sql = "SELECT  COLUMN_NAME,DATA_TYPE,IS_NULLABLE FROM    EX1.INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME LIKE 'Clienti' ";
        } else if ("Manageri".equals(valoarea) && "Db1".equals(valoareaDb)) {
            con = ConnectionManager.getConnectionFirst();
            sql = "SELECT  COLUMN_NAME,DATA_TYPE,IS_NULLABLE FROM    EX1.INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME LIKE 'Manageri' ";
        } else if ("Clienti".equals(valoarea) && "Db2".equals(valoareaDb)) {
            con = ConnectionManager.getConnectionSecond();
            sql = "SELECT  COLUMN_NAME,DATA_TYPE,IS_NULLABLE FROM    EX2.INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME LIKE 'Clienti' ";
        }

         stmt = con.createStatement();
         rs = stmt.executeQuery(sql);

        colN = rs.getMetaData().getColumnCount();//nr de coloane

        rowN = 0;
        while (rs.next()) {
            rowN++;
        }// se numara randurile
        rs = stmt.executeQuery(sql);
        String[] colName = new String[colN];
        for (int i = 0; i < colN; i++) {
            colName[i] = rs.getMetaData().getColumnName(i + 1);
        }
        String[][] table = new String[rowN][colN];
        int i = 0;
        while (rs.next()) {
            for (int j = 0; j < colN; j++) {
                if (rs.getString(j + 1) != null) {
                    if (rs.getString(j + 1).matches("[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9] .*")) {
                        table[i][j] = rs.getString(j + 1).split(" ")[0];
                    } else {
                        table[i][j] = rs.getString(j + 1);
                    }
                }
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
            i++;
        }

        DefaultTableModel model = new DefaultTableModel(table, colName);
        f = new JFrame("Vizualizare tabel ales");
        p = new JPanel();
        t = new JTable(model);
        p.add(t);
        f.add(p);
        sp = new JScrollPane(t, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setPreferredSize(new Dimension(900, 500));
        p.add(sp);
        t.setPreferredSize(new Dimension(1000, 700));
        f.setSize(1000, 700);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

}
