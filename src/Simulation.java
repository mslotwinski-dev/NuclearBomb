import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulation {
  static final double L = 0.1; // Rozmiar próbki (m)
  static final int N_U = 100000; // Liczba atomów uranu
  
  static final double A = 0.01; // Prawdopodobieństwo wywołania rozpadu
  static final double B = 0.05; // Prawdopodobieństwo zmiany kierunku
  
  static final double ENERGY_RELEASED = 200e6 * 1.6e-19; // Energia rozpadu (J)
  
  static final int N_NEUTRONS = 3; // Neutrony na rozpad
  static final double TIME_STEP = 1e-9; // Krok czasowy (s)
  static final int MAX_ITER = 10000; // Maksymalna liczba iteracji
  
  public void Run() throws Exception {
    System.out.println("Hello, World!");

    Random rand = new Random();
    List<Atom> atoms = new ArrayList<>();

  }
}
