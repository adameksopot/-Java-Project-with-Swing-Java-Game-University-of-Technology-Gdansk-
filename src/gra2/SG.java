package gra2;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import static javax.swing.JComponent.TOOL_TIP_TEXT_KEY;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SG extends JFrame { // SG inaczej startgame

    JPanel pn1;
    JPanel pn2;
    JPanel pn3;
    JPanel pn4;
    JButton[] buttony = new JButton[25];
    JButton start;
    JButton exit;
    JLabel lb1;
    JLabel lb2;
    JLabel pokazpoziom;
    int punkty = 0;
    int zycia = 5;
    Timer time;
    int co_ile_przycisk_sie_pojawia;
    int co_ile_znika;
    int poziom;

    Menu2 menu;
// konstruktor z parametrami wywolywania i zanikania buttonow ktore zbijamy oraz poziomem,parametr m sluzy do powrotu do menu
    SG(int _co_ile_przycisk_sie_pojawia, int _co_ile_znika, int _poziom, Menu2 m) { 

        super();
        this.menu = m;
        setSize(1024, 768);
        setResizable(false);
     
        poziom = _poziom;
        co_ile_przycisk_sie_pojawia = _co_ile_przycisk_sie_pojawia;
        co_ile_znika = _co_ile_znika;
        Toolkit tools = getToolkit();
        Image cursorimg = tools.getImage("images\\miecz2.png");
        Point hotstop = new Point(8, 0);
        // ustawienie miecza swietlnego
        setCursor(tools.createCustomCursor(cursorimg, hotstop, TOOL_TIP_TEXT_KEY));

        pn1 = new JPanel();
        pn2 = new JPanel();
        pn3 = new JPanel();
        pn2.setBackground(Color.white);
        pn3.setBackground(Color.white);
        pn1.setBackground(Color.white);
        pokazpoziom = new JLabel(" LVL: " + poziom);
        lb1 = new JLabel(" PKT: ");
        lb2 = new JLabel("Życia  " + zycia);
        pn1.setLayout(new GridBagLayout());

        pn2.setLayout(new GridLayout(5, 5)); // podzial panelu buttonow na plansze 5x5
        pn2.setBounds(10, 10, 10, 1);

        start = new JButton();
        start.addActionListener(new B1());
        start.setIcon(new ImageIcon("images\\6.jpg"));
        start.setOpaque(false);
        start.setBorderPainted(false);
        start.setContentAreaFilled(false);
        start.setBackground(Color.blue);
        start.setFocusPainted(false);
        pn1.add(start);

        for (int i = 0; i <= 24; i++) {
            if (i == 0 && i == 6 && i == 12 && i == 18 && i == 24) {
            }
            buttony[i] = new JButton();
            buttony[i].addActionListener(new MousePressed(this)); 
            buttony[i].setVisible(false);
            buttony[i].setOpaque(false);
            buttony[i].setBorderPainted(false);
            buttony[i].setContentAreaFilled(false);
            buttony[i].setBackground(Color.white);
            buttony[i].setFocusPainted(false);

            pn2.add(buttony[i]);
        }
        for (int i = 0; i < 25; i = i + 12) {
            buttony[i].setIcon(new ImageIcon("images\\z2.png"));
        }
        for (int i = 5; i <= 9; i = i + 2) {
            buttony[i].setIcon(new ImageIcon("images\\z4.png"));
        }
        for (int i = 15; i <= 17; i++) {
            buttony[i].setIcon(new ImageIcon("images\\z4.png"));
        }
        for (int i = 20; i <= 23; i++) {
            buttony[i].setIcon(new ImageIcon("images\\z.png"));
        }
        for (int i = 2; i < 5; i = i + 2) {
            buttony[i].setIcon(new ImageIcon("images\\z3.png"));
        }
        for (int i = 10; i < 15; i = i + 4) {
            buttony[i].setIcon(new ImageIcon("images\\z3.png"));
        }
        for (int i = 6; i < 20; i = i + 12) {
            buttony[i].setIcon(new ImageIcon("images\\z5.png"));
        }
        for (int i = 3; i < 14; i = i + 5) {
            buttony[i].setIcon(new ImageIcon("images\\z6.png"));
        }
        for (int i = 1; i < 12; i = i + 10) {
            buttony[i].setIcon(new ImageIcon("images\\z7.png"));
        }
        buttony[19].setIcon(new ImageIcon("images\\z7.png"));

        exit = new JButton();
        exit.setIcon(new ImageIcon("images\\return.jpg"));
        exit.setOpaque(false);
        exit.setBorderPainted(false);
        exit.setContentAreaFilled(false);
        exit.setBackground(Color.white);
        exit.setFocusPainted(false);
        exit.addActionListener(new Exit(this));
        pn3.add(lb2);
        pn3.add(exit);
        pn3.add(pokazpoziom);
        pn3.add(lb1);
        add(pn1, BorderLayout.NORTH);
        add(pn2, BorderLayout.CENTER);
        add(pn3, BorderLayout.SOUTH);

    }
// przycisk (strzalka) start do rozpoczecia gry
//  dwa timery do ukrywania i zapalania buttonow  
   // zapalanie nastepuja "w kolko"
    // natomiast wewnatrz jest dodatkowy timer ktory jednorazowo gasi button
   // dzialanie timerow w ms narzuca poziom trudnosci gry 
    private class B1 implements ActionListener {

        Random s = new Random();

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            time = new Timer(co_ile_przycisk_sie_pojawia, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    int a = s.nextInt(24);
                    buttony[a].setVisible(true);
                    final Timer t = new Timer(co_ile_znika, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            buttony[a].setVisible(false);
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
//klikniecie myszy 
    private class MousePressed implements ActionListener {

        Menu2 m;
        SG frame;
        AudioClip sound;
        File wavFile = new File("sounds\\hammeronce.wav");

        MousePressed(SG _frame) {
            frame = _frame;
            try {
                sound = Applet.newAudioClip(wavFile.toURL());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
// owoce slodkie czyli arbuzy,maliny,truskawki
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            sound.play();
            if (actionEvent.getSource() == buttony[0] || actionEvent.getSource() == buttony[1] || actionEvent.getSource()
                    == buttony[2] || actionEvent.getSource() == buttony[4] || actionEvent.getSource() == buttony[10]
                    || actionEvent.getSource() == buttony[11]
                    || actionEvent.getSource() == buttony[12] || actionEvent.getSource() == buttony[14]
                    || actionEvent.getSource() == buttony[19] || actionEvent.getSource() == buttony[24]) {

                punkty++;
                lb1.setText("/" + punkty);
                if (punkty >=6) {
                    if (poziom > 7) {
                        // ukonczenie gry 
                        frame.dispose();
                        Congratulations adam = new Congratulations(m);
                        adam.setVisible(true);

                    } else {
                        // nastepny poziom az do 7
                        time.stop();
                        SG adam = new SG(co_ile_przycisk_sie_pojawia - 150, co_ile_znika - 200, poziom + 1, menu);
                        frame.dispose();
                        adam.setVisible(true);
                    }

                }
                // cytrusy
            } else if (actionEvent.getSource() == buttony[3] || actionEvent.getSource() == buttony[6]
                    || actionEvent.getSource() == buttony[8] || actionEvent.getSource() == buttony[13]
                    || actionEvent.getSource() == buttony[18] || actionEvent.getSource() == buttony[23]
                    || actionEvent.getSource() == buttony[22] || actionEvent.getSource() == buttony[21] || actionEvent.getSource() == buttony[20]) {
                punkty--;
                lb1.setText("/ " + punkty);
                zycia--;
                lb2.setText(" " + zycia);
                // przegranie gry
                if (zycia < 0) {
                    frame.dispose();
                     EndGame adam2 = new EndGame(frame);
                     adam2.setVisible(true);
                }
            } else {
                // pozostale czarne owoce
                
                punkty=punkty-2;
                  lb1.setText("/ " + punkty);
                zycia=zycia-2;
                    lb2.setText(" " + zycia);
                if (zycia < 0) {
                     // przegranie gry
                    frame.dispose();
                    EndGame adam2 = new EndGame(frame);
                    adam2.setVisible(true);
                }
            }

        }

    }

    private class Exit implements ActionListener {
// powrot do menu
        SG frame;

        public Exit(SG _frame) {
            frame = _frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            frame.dispose();
            menu.setVisible(true);

        }
    }
}
