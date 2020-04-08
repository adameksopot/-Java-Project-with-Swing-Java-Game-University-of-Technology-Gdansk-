package gra2;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
// przy przejsciu 7 poziomow gry zabojcze cytrusy
public class Congratulations extends JFrame {

    JButton exit;
    JPanel panelek;
    JPanel panelekdrugi;
    Menu2 menu;

    Congratulations(Menu2 m) {
        super();
        this.menu = m;
            setSize(1024, 768);
        panelekdrugi = new JPanel();
        panelek = new JPanel(new GridBagLayout());
        panelek.setBackground(Color.white);
        panelekdrugi.setBackground(Color.white);
        this.add(panelekdrugi);
        this.add(panelek);
        exit = new JButton();
        panelek.add(exit);
        exit.setIcon(new ImageIcon("images\\oki.gif"));
        exit.setOpaque(false);
        exit.setBorderPainted(false);
        exit.setContentAreaFilled(false);
        exit.setBackground(Color.white);
        exit.setFocusPainted(false);

        exit.addActionListener(new Congratulations.Exit(this));
        setResizable(false);
        setVisible(true);

    }

    private class Exit implements ActionListener {

        Congratulations frame;

        public Exit(Congratulations _frame) {
            frame = _frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

              System.exit(0);

        }
    }
}
