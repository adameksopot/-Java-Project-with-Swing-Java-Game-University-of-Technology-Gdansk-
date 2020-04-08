package gra2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Rules extends JFrame {

    JButton exit;
    JPanel panelek;
    JPanel panelekdrugi;
    Menu2 menu;

    Rules(Menu2 m) {
        super();
        this.menu = m;
           setSize(1024, 768);
        panelek = new JPanel();
        panelek.setBackground(Color.white);
        
        this.add(panelek, BorderLayout.CENTER);
        exit = new JButton();
        exit.setIcon(new ImageIcon("images\\return.jpg"));
        exit.setOpaque(false);
        exit.setBorderPainted(false);
        exit.setContentAreaFilled(false);
        exit.setBackground(Color.white);
        exit.setFocusPainted(false);

        exit.addActionListener(new Exit(this));
        panelekdrugi = new JPanel();
        panelekdrugi.add(exit);
        panelekdrugi.setBackground(Color.white);
        this.add(panelekdrugi, BorderLayout.SOUTH);
        setResizable(false);

    }

    private class Exit implements ActionListener {

        Rules frame;

        public Exit(Rules _frame) {
            frame = _frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            frame.dispose();
            menu.setVisible(true);

        }
    }
}
