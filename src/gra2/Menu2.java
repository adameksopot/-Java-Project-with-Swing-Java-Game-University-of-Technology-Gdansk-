package gra2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
// klasa menu gry
public class Menu2 extends JFrame {

    JButton zdj;
    JButton b1;
    JButton b2;
    JButton b3;
    JButton b4;
    JButton ah;
    
// konstruktor
    Menu2(String nazwa) {
        super();
     
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 768);
        setResizable(false);
        setLayout(new GridBagLayout());
        setLayout(new BorderLayout());
        stworzLogo();
        stworzwybory();
        autorAH();
        setVisible(true);

    }
// tworzenie loga uczelni w funkcji
    public void stworzLogo() {

        JPanel logo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        zdj = new JButton();
        logo.add(zdj);
        this.add(logo, BorderLayout.NORTH);
        logo.setBackground(Color.white);
        zdj.setOpaque(false);
        zdj.setBorderPainted(false);
        zdj.setContentAreaFilled(false);
        zdj.setBackground(Color.white);
        zdj.setFocusPainted(false);
        zdj.setIcon(new ImageIcon("images\\pg.png"));

    }
 // opcje gry,gra cytrusy,numery,zasady,wyjscie
    public void stworzwybory() {

        JPanel wybory = new JPanel(new GridBagLayout());
        b1 = new JButton();
        b2 = new JButton();
        b3 = new JButton();
        b4 = new JButton();
        b1.addActionListener(new StartGry(this));
        b1.setIcon(new ImageIcon("images\\gb.JPG"));
        b1.setOpaque(false);
        b1.setBorderPainted(false);
        b1.setContentAreaFilled(false);
        b1.setBackground(Color.white);
        b1.setFocusPainted(false);
        b2.addActionListener(new Zasady(this));
        b2.setIcon(new ImageIcon("images\\7.jpg"));
        b2.setOpaque(false);
        b2.setBorderPainted(false);
        b2.setContentAreaFilled(false);
        b2.setBackground(Color.white);
        b2.setFocusPainted(false);
        b3.setIcon(new ImageIcon("images\\8.jpg"));
        b3.setOpaque(false);
        b3.setBorderPainted(false);
        b3.setContentAreaFilled(false);
        b3.setBackground(Color.white);
        b3.setFocusPainted(false);
        b3.addActionListener(new KoniecGry());
        b4.setIcon(new ImageIcon("images\\SS2.JPG"));
        b4.setOpaque(false);
        b4.setBorderPainted(false);
        b4.setContentAreaFilled(false);
        b4.setBackground(Color.white);
        b4.setFocusPainted(false);
        b4.addActionListener(new StartGry2(this));
        wybory.setBackground(Color.white);
        wybory.add(b1);
        wybory.add(b4);
        wybory.add(b2);
        wybory.add(b3);

        this.add(wybory, BorderLayout.CENTER);

    }
 // wyswietlenie mojego nazwiska jako autora w rogo ekranu
    public void autorAH() {

        JPanel autor = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        ah = new JButton();
        ah.setOpaque(false);
        ah.setBorderPainted(false);
        ah.setContentAreaFilled(false);
        ah.setBackground(Color.white);
        ah.setFocusPainted(false);
        autor.setBackground(Color.white);
        ah.setIcon(new ImageIcon("images\\ah.jpg"));
        autor.add(ah);
        this.add(autor, BorderLayout.SOUTH);

    }
// prywatna klasa nasluchujaca button od gry cytrusy
    private class StartGry implements ActionListener {
// pole Menu2 do ukrycia biezacego JFrame'a
        Menu2 m;

        public StartGry(Menu2 menu) {
            this.m = menu;
        }
// utworzenie obiektu SG z parametrami jak szybkosc zaniku i pokazywania sie buttonow oraz poziomem
        @Override
        public void actionPerformed(ActionEvent e) {
            SG adam = new SG(1090, 1500, 1, m);
            adam.setVisible(true);
            m.setVisible(false);

        }
    }

    private class StartGry2 implements ActionListener {
// gra w numery alarmowe 
// konstruktor z parametrem menu do pozniejszego ukrycia        
// ukrywanie framu zrobilem analogicznie jak wyzej        
        Menu2 m;
     
        

        public StartGry2(Menu2 menu) {
            this.m = menu;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            SG2 adam = new SG2(m);
            adam.setVisible(true);

            m.setVisible(false);

        }
    }
// w klasa w ktorej opisano zasady
    private class Zasady implements ActionListener {

        Menu2 m;

        public Zasady(Menu2 menu) {
            this.m = menu;

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Rules adas = new Rules(m);
            adas.setVisible(true);
            m.setVisible(false);
        }
    }
// wyjscie z gry 
  // system exit konczy dzialanie programu   
    private class KoniecGry implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
