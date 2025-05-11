package nuclear;

public class SimulationParams {
  double L = 1e-6; // Rozmiar próbki (m)
  int N_U = 10000; // Liczba atomów uranu
  double TIME_STEP = 1e-9; // Krok czasowy (s)
  int MAX_ITER = 300; // Maksymalna liczba iteracji
  
  double A = 1e-3; // Prawdopodobieństwo wywołania rozpadu
  double B = 0.05; // Prawdopodobieństwo zmiany kierunku
  int N_NEUTRONS = 3; // Neutrony na rozpad
  
  double ENERGY_RELEASED = 200e6 * 1.6e-19; // Energia rozpadu (J)

  public SimulationParams(double L, int N_U, double TIME_STEP, int MAX_ITER, double A, double B, int N_NEUTRONS, double ENERGY_RELEASED) {
    this.L = L;
    this.N_U = N_U;
    this.TIME_STEP = TIME_STEP;
    this.MAX_ITER = MAX_ITER;
    this.A = A;
    this.B = B;
    this.N_NEUTRONS = N_NEUTRONS;
    this.ENERGY_RELEASED = ENERGY_RELEASED;

  }
}
