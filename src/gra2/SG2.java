package gra2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class SG2 extends JFrame {
        // tutaj przechowywana bedzie informacja czy klikamy dobry button z numuerem
    // -1 poniewaz w tablicy numerujemy od 0
        public static int CORRECT_BUTTON_ID = -1;

    public JLabel label;
    Menu2 menu;
    Timer time;
    Random s;
    JButton k0, k1, k2, k3, k4, k5, k6, k7, k8, koniec, zdj, zdj2;
    JButton[] buttony = new JButton[9];
    Random nr = new Random();
    public int obrazek;
    public double punkty, klikniecia;
    public double wynik;
    int co_ile_przycisk_sie_pojawia;
    int co_ile_znika;

    SG2(Menu2 m) {
        super();
        this.menu = m;
        setLayout(null);
          setSize(1024, 768);
        this.obrazek = nr.nextInt(7); // randomowo losowany obrazek instytucji 
        punkty = 0;
        klikniecia = 0;
        getContentPane().setBackground(Color.WHITE);
// buttona z instytucjami 
        for (int i = 0; i < 8; i++) {
            buttony[i] = new JButton();
            buttony[i].setBounds(70, 150, 700, 250);
            buttony[i].setBackground(Color.white);
            buttony[i].setOpaque(false);
            buttony[i].setBorderPainted(false);
            buttony[i].setFocusPainted(false);
            buttony[i].setContentAreaFilled(false);
            buttony[i].setVisible(false);

            add(buttony[i]);
        }

        buttony[8] = new JButton();
   
        buttony[8].setBackground(Color.white);
        buttony[8].setOpaque(false);
        buttony[8].setBorderPainted(false);
        buttony[8].setFocusPainted(false);
        buttony[8].setContentAreaFilled(false);
        buttony[8].setVisible(true);
        buttony[8].setBounds(750, 650, 55, 60);
        add(buttony[8]);
        buttony[8].setIcon(new ImageIcon("images/start.png"));
        buttony[0].setIcon(new ImageIcon("images/policja.jpg"));
        buttony[1].setIcon(new ImageIcon("images/sp.jpg"));
        buttony[2].setIcon(new ImageIcon("images/pr.jpg"));
        buttony[3].setIcon(new ImageIcon("images/ogolny.jpg"));
        buttony[4].setIcon(new ImageIcon("images/sm.jpg"));
        buttony[5].setIcon(new ImageIcon("images/pg2.jpg"));
        buttony[6].setIcon(new ImageIcon("images/pe.jpg"));
        buttony[7].setIcon(new ImageIcon("images/pc.jpg"));

        k0 = new JButton();
        k0.setIcon(new ImageIcon("images/lampki.gif"));
        k0.setBackground(Color.white);
        k0.setOpaque(false);
        k0.setBorderPainted(false);
        k0.setFocusPainted(false);
        k0.setContentAreaFilled(false);
        k0.setVisible(true);
        k0.setBounds(100, 10, 450, 80);
        add(k0);
        k1 = new JButton();
        k1.setIcon(new ImageIcon("images/997.jpg"));
        k1.setContentAreaFilled(false);
        k1.setBounds(20, 380, 150, 80);
        add(k1);
        k2 = new JButton();
        k2.setIcon(new ImageIcon("images/998.jpg"));
        k2.setContentAreaFilled(false);
        k2.setBounds(190, 380, 150, 80);
        add(k2);
        k3 = new JButton();
        k3.setIcon(new ImageIcon("images/999.jpg"));
        k3.setContentAreaFilled(false);
        k3.setBounds(360, 380, 150, 80);
        add(k3);
        k4 = new JButton();
        k4.setIcon(new ImageIcon("images/112.jpg"));
        k4.setContentAreaFilled(false);
        k4.setBounds(530, 380, 150, 80);
        add(k4);
        k5 = new JButton();
        k5.setIcon(new ImageIcon("images/986.jpg"));
        k5.setContentAreaFilled(false);
        k5.setBounds(20, 480, 150, 80);
        add(k5);
        k6 = new JButton();
        k6.setIcon(new ImageIcon("images/992.jpg"));
        k6.setContentAreaFilled(false);
        k6.setBounds(190, 480, 150, 80);
        add(k6);
        k7 = new JButton();
        k7.setIcon(new ImageIcon("images/991.jpg"));
        k7.setContentAreaFilled(false);
        k7.setBounds(360, 480, 150, 80);
        add(k7);
        k8 = new JButton();
        k8.setIcon(new ImageIcon("images/993.jpg"));
        k8.setContentAreaFilled(false);
        k8.setBounds(530, 480, 150, 80);
        add(k8);

        koniec = new JButton();
        koniec.setIcon(new ImageIcon("images/return.jpg"));
        koniec.setOpaque(false);
        koniec.setContentAreaFilled(false);
        koniec.setBounds(600, 550, 180, 250);
        koniec.setFocusPainted(true);
        koniec.setBorderPainted(false);
        add(koniec);
        koniec.addActionListener(new Exit(this));

        k1.addActionListener(new K(1));
        k2.addActionListener(new K(2));
        k3.addActionListener(new K(3));
        k4.addActionListener(new K(4));
        k5.addActionListener(new K(5));
        k6.addActionListener(new K(6));
        k7.addActionListener(new K(7));
        k8.addActionListener(new K(8));

        buttony[8].addActionListener(new K9());
        label = new JLabel("Jak ci idzie: " + wynik + "%");
        label.setBounds(460, 680, 150, 20);
        add(label);

    }
// wyliczanie statystyki dobrych odpowiedzi 
    public void licz(double punkty, double klikniecia) {

        wynik = (punkty / klikniecia) * 10000;
        wynik = Math.round(wynik);
        wynik = wynik / 100;
        label.setText("Statystyka: " + wynik + "%");

    }
//wyjscie analogiczne do klasy SG
    public class Exit implements ActionListener {

        SG2 frame;

        public Exit(SG2 _frame) {
            frame = _frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            frame.dispose();
            menu.setVisible(true);

        }
    }
// klasa nasluchujaca klikniecie numeru
    public class K implements ActionListener {

        private int buttonId;

        public K(int bid) {
            this.buttonId = bid;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            buttonClicked(this.buttonId);
        }
    }

    //tutaj dodajemy akcje dla poszczególnych przycisków
    private void buttonClicked(int buttonId) {
// tu sprawdzamy czy dobry button i w zaleznosci od tego
        // przyznajemy punkty lub odejmujemy
        if (buttonId == CORRECT_BUTTON_ID) {
            punkty++;
            klikniecia++;
            licz(punkty, klikniecia);

        } else {
            klikniecia++;
            licz(punkty, klikniecia);
        }

        switch (buttonId) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
        }
    }

    public class K9 implements ActionListener {
// przycisk start uruchamia timer wykorzystany w poprzedniej grze
 // co ile znika ustawiono na 4900 gdyz program dziala 1 ms 
        
        Random s = new Random();

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            co_ile_przycisk_sie_pojawia = 5000;
            co_ile_znika = 4900;
            int a = s.nextInt(8);
            time = new Timer(co_ile_przycisk_sie_pojawia, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    int a = s.nextInt(8);
                    buttony[a].setVisible(true);
                    CORRECT_BUTTON_ID = a + 1;
                    {
                        System.out.println(a);
                    }

                    final Timer t = new Timer(co_ile_znika, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            buttony[a].setVisible(false);
                            CORRECT_BUTTON_ID = -1;
                        }
                    });
                    t.setRepeats(false);
                    t.start();
                }
            });
            time.setRepeats(true);
            time.start();

        }
    }
}
