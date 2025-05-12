package nuclear;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {

    public static JMenuBar createMenuBar(JFrame parentFrame) {
        JMenuItem zapisz_txt = new JMenuItem("Zapisz dane");
        JMenuItem zapisz_jpg = new JMenuItem("Zapisz jako obraz");
        JMenuItem otworz = new JMenuItem("Otwórz");
        JMenuItem polski = new JMenuItem("Polski");
        JMenuItem angielski = new JMenuItem("English");
        JMenuItem japonski = new JMenuItem("日本語");


        JMenu saveMenu = new JMenu("Zapisz");
        JMenu openMenu = new JMenu("Otwórz");
        JMenu languageMenu = new JMenu("Język");

        saveMenu.add(zapisz_txt);
        saveMenu.add(zapisz_jpg);
        openMenu.add(otworz);
        languageMenu.add(polski);
        languageMenu.add(angielski);
        languageMenu.add(japonski);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(saveMenu);
        menuBar.add(openMenu);
        menuBar.add(languageMenu);

        zapisz_txt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        zapisz_jpg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        otworz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        polski.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        angielski.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        japonski.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        return menuBar;
    }
}