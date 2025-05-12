package nuclear;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class GUI extends JFrame {

		private static final long serialVersionUID = 1L;

		private Boolean miga = false;
		
		private JPanel panel_N,panel_E,panel_C;
		private JPanel panel_wielkosci,panel_materialu,panel_danych,panel_na3;
		private JPanel panel_e1,panel_e2,panel_e3;
		
		private JLabel ikonka_materialu;
		
		private RoundedTextField size = new RoundedTextField(8,20);
		private RoundedTextField A = new RoundedTextField(8,20);
		private RoundedTextField B = new RoundedTextField(8,20);
		private RoundedTextField N = new RoundedTextField(8,20);
		
		private RoundedJButton Run;
		ImageIcon icon,iconU,iconP,iconZ;
		private RoundedJButton energia = new RoundedJButton("E(t)");
		private RoundedJButton moc = new RoundedJButton("P(t)");
		private RoundedJButton neutrony = new RoundedJButton("n(t)");

		RoundedComboBox<String> comboBox_wielkosci;
		RoundedComboBox<String> comboBox;
		
		public GUI() {
			super("Będzie boom");
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			icon = new ImageIcon(new ImageIcon(GUI.class.getResource("_nuclear.png")).getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT));
			iconU = new ImageIcon(new ImageIcon(GUI.class.getResource("_uran.png")).getImage().getScaledInstance(48, 48, Image.SCALE_DEFAULT));
			iconP = new ImageIcon(new ImageIcon(GUI.class.getResource("_pluton.png")).getImage().getScaledInstance(48, 48, Image.SCALE_DEFAULT));
			iconZ = new ImageIcon(new ImageIcon(GUI.class.getResource("_ziemniak.png")).getImage().getScaledInstance(48, 48, Image.SCALE_DEFAULT));

			Run =  new RoundedJButton("Run",icon);

			this.setSize(900, 600);
			panel_N = new JPanel();
			panel_E = new JPanel();
			panel_C = new JPanel();
			panel_C.setBackground(Color.pink);
			
			this.add(panel_N, BorderLayout.NORTH);
			this.add(panel_E,BorderLayout.EAST);
			this.add(panel_C,BorderLayout.CENTER);
			
			this.setJMenuBar(Menu.createMenuBar(this));
			
			String[] wielkosci = { "Mała", "Duża", "Ogromna"};
			comboBox_wielkosci = new RoundedComboBox<>(wielkosci,20);
			comboBox_wielkosci.setSelectedIndex(0);
			size.setText("1 kg");
			
			String[] description = { "Uran", "Pluton", "Ziemniaki"};
			comboBox = new RoundedComboBox<>(description,20);
			comboBox.setSelectedIndex(0);

			comboBox.setBackground(Color.white);
			comboBox_wielkosci.setBackground(Color.white);
			
			panel_e1 = new JPanel();
			panel_e2 = new JPanel();
			panel_e3 = new JPanel();
			
			panel_na3 = new JPanel();
			panel_wielkosci = new JPanel();
			panel_materialu = new JPanel();
			ikonka_materialu = new JLabel(iconU);
			panel_danych = new JPanel();
			
			panel_E.setBackground(Color.white);
			panel_na3.setBackground(Color.white);
			panel_wielkosci.setBackground(Color.white);
			panel_materialu.setBackground(Color.white);
			panel_danych.setBackground(Color.white);
			panel_e1.setBackground(Color.white);
			panel_e2.setBackground(Color.white);
			panel_e3.setBackground(Color.white);
			
			panel_E.setLayout(new GridLayout(3,1,5,10));
			panel_wielkosci.setLayout(new GridLayout(1,2,5,10));
			panel_wielkosci.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Rozmiar", TitledBorder.CENTER,TitledBorder.TOP));
			panel_materialu.setLayout(new GridLayout(1,2,5,10));
			panel_materialu.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Materiał", TitledBorder.CENTER,TitledBorder.TOP));
			panel_danych.setLayout(new GridLayout(3,1,5,10));
			panel_danych.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Ustawienia", TitledBorder.CENTER,TitledBorder.TOP));
			panel_na3.setLayout(new GridLayout(1,3,5,10));
		    panel_na3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Typ wykresu", TitledBorder.CENTER,TitledBorder.TOP));
		    panel_e1.setLayout(new GridLayout(2,1,5,10));
		    panel_e2.setLayout(new GridLayout(1,1,5,10));
		    panel_e3.setLayout(new GridLayout(2,1,5,10));
		    
		    panel_E.add(panel_e1);
		    panel_E.add(panel_e2);
		    panel_E.add(panel_e3);
		    
			panel_e1.add(panel_wielkosci);
			panel_e1.add(panel_materialu);
			panel_e2.add(panel_danych);

			panel_danych.add(A);
			panel_danych.add(B);
			panel_danych.add(N);
			panel_e3.add(panel_na3);
			panel_e3.add(Run);

			panel_wielkosci.add(comboBox_wielkosci);
			panel_wielkosci.add(size);
			panel_materialu.add(comboBox);
			panel_materialu.add(ikonka_materialu);
			panel_na3.add(energia);
			panel_na3.add(moc);
			panel_na3.add(neutrony);

			A.setPlaceholder("A");
			B.setPlaceholder("B");
			N.setPlaceholder("N");
			
			Object[] elements = {
	                Run, neutrony, moc, energia, N, B, A, comboBox, size, comboBox_wielkosci
	            };
			
			comboBox_wielkosci.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                updateTextFieldValue();
	            }
	        });
			
			comboBox.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                updateIcon();
	            }
	        });
			
			Run.addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			        miga = !miga;
			        Run.setText(miga ? "Stop" : "Run");

			        for (Object obj : elements) {
			            if (obj instanceof RoundedJButton b) b.setFlashing(miga);
			            else if (obj instanceof RoundedTextField tf) tf.setFlashing(miga);
			            else if (obj instanceof RoundedComboBox<?> cb) cb.setFlashing(miga);
			        }

			        if (miga) {
			            Color kolor1 = Color.RED;
			            Color kolor2 = Color.ORANGE;

			            new Thread(() -> {
			                for (Object obj : elements) {
			                    if (!miga) break;

			                    SwingUtilities.invokeLater(() -> {
			                        if (obj instanceof RoundedJButton b) b.FlashingColor(kolor1, kolor2);
			                        else if (obj instanceof RoundedTextField tf) tf.FlashingColor(kolor1, kolor2);
			                        else if (obj instanceof RoundedComboBox<?> cb) cb.FlashingColor(kolor1, kolor2);
			                    });

			                    try {
			                        Thread.sleep(200);
			                    } catch (InterruptedException ex) {
			                        Thread.currentThread().interrupt();
			                    }
			                }
			            }).start();
			        }
			    }
			});
		}
		private void updateTextFieldValue() {
	        String selectedSize = (String) comboBox_wielkosci.getSelectedItem();
	        switch (selectedSize) {
	            case "Mała":
	                size.setText("1 kg");
	                break;
	            case "Duża":
	            	size.setText("10 kg");
	                break;
	            case "Ogromna":
	            	size.setText("100 kg");
	                break;
	        }
	    }
		private void updateIcon() {
			String selectedMaterial = (String) comboBox.getSelectedItem();
	        switch (selectedMaterial) {
	            case "Uran":
	                ikonka_materialu.setIcon(iconU);
	                break;
	            case "Pluton":
	                ikonka_materialu.setIcon(iconP);
	                break;
	            case "Ziemniaki":
	                ikonka_materialu.setIcon(iconZ);
	                break;
	        }
		}
}
