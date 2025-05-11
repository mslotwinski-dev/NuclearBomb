package nuclear;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {

    public static JMenuBar createMenuBar(JFrame parentFrame) {
        JMenuItem zapisz_txt = new JMenuItem("Zapisz dane");
        JMenuItem zapisz_jpg = new JMenuItem("Zapisz jako obraz");
        JMenuItem otworz = new JMenuItem("Otwórz");

        JMenu saveMenu = new JMenu("Save");
        JMenu openMenu = new JMenu("Open");

        saveMenu.add(zapisz_txt);
        saveMenu.add(zapisz_jpg);
        openMenu.add(otworz);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(saveMenu);
        menuBar.add(openMenu);

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

        

        return menuBar;
    }
}