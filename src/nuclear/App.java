package nuclear;

import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;


public class App {
  static final double L = 1e-6; // Rozmiar próbki (m)
  static final int N_U = 10000; // Liczba atomów uranu
  static final double TIME_STEP = 1e-9; // Krok czasowy (s)
  static final int MAX_ITER = 300; // Maksymalna liczba iteracji
  
  
  static final double A = 1e-3; // Prawdopodobieństwo wywołania rozpadu
  static final double B = 0.05; // Prawdopodobieństwo zmiany kierunku
  static final int N_NEUTRONS = 3; // Neutrony na rozpad
  
  static final double ENERGY_RELEASED = 200e6 * 1.6e-19; // Energia rozpadu (J)

  static List<Double> n_energy = new ArrayList<>(); // Lista energii
  static List<Double> n_neutron = new ArrayList<>(); // Lista neutronów
  static List<Double> n_power = new ArrayList<>(); // Lista mocy

  
  public static void main(String[] args) throws Exception {


    SimulationParams params = new SimulationParams(L, N_U, TIME_STEP, MAX_ITER, A, B, N_NEUTRONS, ENERGY_RELEASED);
    
    Simulation S = new Simulation(params);
    S.Run();

    for (int t = 0; t < MAX_ITER; t++) {
      S.NextStep();

      n_energy = S.getEnergy(n_energy);
      n_power.add(S.Power);
      n_neutron.add(S.Neutrons);

    }

    FileSaverFX save = new FileSaverFX();
    SwingUtilities.invokeLater(() -> save.save(n_energy, n_power, n_neutron));


    GUI gui = new GUI();
		gui.setVisible(true);

   

  }

  
}
