package nuclear;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class SimulationMultithreaded {
  // TECHNICZNE
  static final double L = 0.1; // Rozmiar próbki (m)
  static final int N_U = 100000; // Liczba atomów uranu
  static final double TIME_STEP = 1e-9; // Krok czasowy (s)
  static final int MAX_ITER = 300; // Maksymalna liczba iteracji
  
  // WPISANE PRZEZ UŻYTKOWNIKA
  static final double A = 0.01; // Prawdopodobieństwo wywołania rozpadu
  static final double B = 0.05; // Prawdopodobieństwo zmiany kierunku
  static final int N_NEUTRONS = 3; // Neutrony na rozpad
  
  // POLICZONE ? 
  static final double ENERGY_RELEASED = 200e6 * 1.6e-19; // Energia rozpadu (J)

  // OBLICZENIOWE
  Random rand = new Random();
  double Energy = 0;

  // NEUTRONY
  double yes = 0;
  double no = 0;

  ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

  public void RunMulti() throws Exception {
    System.out.println("Hello, World!");

    List<Atom> atoms = new ArrayList<>();
    List<Neutron> neutrons = new ArrayList<>();

    for (int i = 0; i < N_U; i++) {
      atoms.add(new Atom(rand.nextDouble() * L, rand.nextDouble() * L, rand.nextDouble() * L));
    }

    for (int t = 0; t < MAX_ITER; t++) {
      
      // SPONTANICZNE ROZPADY (Multithreaded)
      List<Callable<Void>> tasks = new ArrayList<>();
      for (Atom atom : atoms) {
        tasks.add(() -> {
          if (atom.React(A)) {
            synchronized (SimulationMultithreaded.this) {
              Energy += ENERGY_RELEASED;
              for (int i = 0; i < N_NEUTRONS; i++) {
                neutrons.add(new Neutron(atom.x, atom.y, atom.z));
              }
            }
          }
          return null;
        });
      }
      executorService.invokeAll(tasks); // Execute spontaneous decays in parallel

      // NEUTRONY (Multithreaded)
      List<Callable<Void>> neutronTasks = new ArrayList<>();
      for (Neutron neutron : neutrons) {
        neutronTasks.add(() -> {
          if (neutron.OutsideBox(L)) {
            synchronized (SimulationMultithreaded.this) {
              no++;
            }
            return null; // Skip neutron outside the box
          }

          Atom atom = neutron.CheckReact(atoms);

          if (atom != null) {
            // Neutron reacts with atom
            if (atom.React(1)) {
              synchronized (SimulationMultithreaded.this) {
                Energy += ENERGY_RELEASED;
                for (int i = 0; i < N_NEUTRONS; i++) {
                  neutrons.add(new Neutron(atom.x, atom.y, atom.z));
                }
                neutron.Kill();
                yes++;
              }
            }
          } else {
            // Neutron scatters and moves
            neutron.Scatter(B);
            neutron.Move(TIME_STEP); 
            synchronized (SimulationMultithreaded.this) {
              no++;
            }
          }
          return null;
        });
      }
      executorService.invokeAll(neutronTasks); // Execute neutron reactions and movements in parallel

      // Remove decayed atoms and neutrons
      synchronized (SimulationMultithreaded.this) {
        neutrons.removeIf(Neutron::isDecayed);
        atoms.removeIf(Atom::isDecayed);
      }

      // WYPISANIE ENERGII
      System.out.println(new BigDecimal(Energy).toPlainString().replace(".", ","));

    }

    // Shutdown executor service
    executorService.shutdown();

    System.out.println("Sukces neutronów: " + yes / (yes + no) * 100 + "%");
    System.out.println("Sukces neutronów: " + yes);
  }
}