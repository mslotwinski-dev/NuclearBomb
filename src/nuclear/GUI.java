package nuclear;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI extends JFrame {

		private static final long serialVersionUID = 1L;

		private Boolean miga = false;
		
		private JPanel panel_N,panel_E,panel_C;
		private JPanel panel_E1,panel_E2,panel_E2_1,panel_E2_2,panel_E2_2x;
		
		private RoundedTextField A = new RoundedTextField(8,20);
		private RoundedTextField B = new RoundedTextField(8,20);
		private RoundedTextField N = new RoundedTextField(8,20);
		
		private RoundedJButton Run;
		ImageIcon icon;
		private RoundedJButton T_PM = new RoundedJButton("P(masa)");
		private RoundedJButton T_PT = new RoundedJButton("P(czas)");
		
		public GUI() {
			super("Będzie boom");
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			ImageIcon icon = new ImageIcon(new ImageIcon(GUI.class.getResource("_nuclear.png")).getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT));
			Run =  new RoundedJButton("Run",icon);

			this.setSize(800, 600);
			panel_N = new JPanel();
			panel_E = new JPanel();
			panel_C = new JPanel();
			panel_C.setBackground(Color.pink);
			
			this.add(panel_N, BorderLayout.NORTH);
			this.add(panel_E,BorderLayout.EAST);
			this.add(panel_C,BorderLayout.CENTER);
			
			this.setJMenuBar(Menu.createMenuBar(this));

			String[] description = { "Uran", "Pluton", "Ziemniaki"};
			RoundedComboBox<String> comboBox = new RoundedComboBox<>(description,20);
			comboBox.setBackground(Color.white);
			
			panel_E1 = new JPanel();
			panel_E2 = new JPanel();
			panel_E2_1 = new JPanel();
			panel_E2_2 = new JPanel();
			panel_E2_2x = new JPanel();
			
			panel_E.setBackground(Color.white);
			panel_E1.setBackground(Color.white);
			panel_E2.setBackground(Color.white);
			panel_E2_1.setBackground(Color.white);
			panel_E2_2.setBackground(Color.white);
			panel_E2_2x.setBackground(Color.white);
			
			panel_E.setLayout(new GridLayout(2,1,5,10));
			panel_E.add(panel_E1);
			panel_E.add(panel_E2);

			panel_E1.setLayout(new GridLayout(4,1,5,10));
			panel_E1.add(comboBox);
			panel_E1.add(A);
			panel_E1.add(B);
			panel_E1.add(N);
			
			A.setPlaceholder("A");
			B.setPlaceholder("B");
			N.setPlaceholder("N");
			
			panel_E2.setLayout(new GridLayout(2,1));
			panel_E2.add(panel_E2_1);
			panel_E2.add(panel_E2_2);
			
			panel_E2_2.setLayout(new GridLayout(2,1,5,5));
			panel_E2_2.add(panel_E2_2x);
			panel_E2_2.add(Run);

			panel_E2_2x.setLayout(new GridLayout(1,2,5,5));
			panel_E2_2x.add(T_PM);
			panel_E2_2x.add(T_PT);
			
			Object[] elements = {
	                Run, T_PT, T_PM, N, B, A, comboBox
	            };
			
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
}
