package proiect;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MainGui extends JFrame {

    private final JFrame f;
    private final JButton b1;
    private FrameGeneral f1;

    public MainGui() {

        f = new JFrame("Cristi");
        b1 = new JButton("Partea Cristi Macari");
        f.add(b1);
        f.setLayout(new FlowLayout());
        f.setSize(300, 90);
        f.setVisible(true);
        f.setLocationRelativeTo(null);

        b1.addActionListener((ActionEvent ae) -> {
            f1 = new FrameGeneral();
        });
        //Close Window 
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

}
