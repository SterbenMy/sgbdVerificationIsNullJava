package proiect;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Verifica implements ActionListener {

    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String sql,a;
  

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String valoarea = (String) FrameGeneral.cbTabel.getSelectedItem();
            String valoareaDb = (String) FrameGeneral.cbDatabase.getSelectedItem();
            
            if ("Db1".equals(valoareaDb) && "Clienti".equals(valoarea)) {
                con = ConnectionManager.getConnectionFirst();
                sql = "SELECT count(IS_NULLABLE) FROM  EX1.INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME LIKE 'Clienti' AND IS_NULLABLE LIKE 'NO'";
            } else if ("Manageri".equals(valoarea) && "Db1".equals(valoareaDb)) {
                con = ConnectionManager.getConnectionFirst();
                sql = " SELECT count(IS_NULLABLE) FROM    EX1.INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME LIKE 'Manageri' AND IS_NULLABLE LIKE 'NO'";
            } else if ("Clienti".equals(valoarea) && "Db2".equals(valoareaDb)) {
                con = ConnectionManager.getConnectionSecond();
                sql = " SELECT count(IS_NULLABLE) FROM    EX2.INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME LIKE 'Clienti' AND IS_NULLABLE LIKE 'NO'";
            }
             stmt = con.createStatement();
             rs = stmt.executeQuery(sql);
             
             while (rs.next()) {
                      a = (rs.getString(1));
                    }
          
            
            if (Integer.parseInt(a) > 1) {
            JOptionPane.showMessageDialog(null, "Baza de date e ok are in total " + Integer.parseInt(a) + " constrangeri de not null");
            } else {
            JOptionPane.showMessageDialog(null, "Baza de date nu e ok are in total " + Integer.parseInt(a) + " constrangeri de not null");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Verifica.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
