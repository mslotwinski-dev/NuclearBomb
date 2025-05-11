import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class GUI extends JFrame {

		private Boolean DEBUG = false;
		private Random rand = new Random();
		
		private JPanel panel_N,panel_E,panel_C;
		private JPanel panel_E1,panel_E2,panel_E2_1,panel_E2_2,panel_E2_2x;
		
		private JMenuItem zapisz,zapiszJako,otworz;
		private JMenuBar menu = new JMenuBar();
		private JMenu Open= new JMenu("Open");
		private JMenu Save= new JMenu("Save");
		
		private RoundedTextField A = new RoundedTextField(8,20);
		private RoundedTextField B = new RoundedTextField(8,20);
		private RoundedTextField N = new RoundedTextField(8,20);
		
		private JButton Run = new JButton("Run");
		private JButton T_PM = new JButton("P(masa)");
		private JButton T_PT = new JButton("P(czas)");
		
		public GUI() {
			super("Będzie boom");
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			this.setSize(500, 450);
			panel_N = new JPanel();
			panel_E = new JPanel();
			panel_C = new JPanel();
			panel_C.setBackground(Color.pink);
			
			this.add(panel_N, BorderLayout.NORTH);
			this.add(panel_E,BorderLayout.EAST);
			this.add(panel_C,BorderLayout.CENTER);
			
			zapisz = new JMenuItem("Zapisz");
			zapiszJako = new JMenuItem("Zapisz jako...");
			otworz = new JMenuItem("Otwórz");
			Save.add(zapisz);
			Save.add(zapiszJako);
			Open.add(otworz);
			menu.add(Save);
			menu.add(Open);
			this.setJMenuBar(menu);

			String[] description = { "Uran", "Pluton", "Ziemniaki"};
			JTextField poleTekstowe = new JTextField("Wpisz nazwe nowej pozycji");
			RoundedComboBox<String> comboBox = new RoundedComboBox<>(description,20);
			comboBox.setBackground(Color.white);
			JButton przycisk = new JButton("Dodaj pozycje");
			JLabel etykieta = new JLabel();
			
			
			
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
			
			
		}
		
}
