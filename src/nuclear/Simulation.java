package nuclear;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulation {
  SimulationParams params;
  
  Random rand = new Random();
  double yes = 0;
  double no = 0;
  
  double Energy = 0;
  double Power = 0;
  double Neutrons = 0;



  List<Atom> atoms = new ArrayList<>();
  List<Neutron> neutrons = new ArrayList<>();

  public Simulation(SimulationParams params) {
    this.params = params;

    for (int i = 0; i < params.N_U; i++) {
      atoms.add(new Atom(rand.nextDouble() * params.L, rand.nextDouble() * params.L, rand.nextDouble() * params.L));
    }
  }

  void NextStep() {
    double temptotal = Energy;
      
    // SPONTANICZNE ROZPADY
    for (Atom atom : atoms) {
      if (atom.React(params.A)) {
        Energy += params.ENERGY_RELEASED;
        for (int i = 0; i < params.N_NEUTRONS; i++) {
          neutrons.add(new Neutron(atom.x, atom.y, atom.z));
        }
      }
    }

    // NEUTRONY

    List<Neutron> newNeutrons = new ArrayList<>(); // Lista nowych neutronów

    for (Neutron neutron : neutrons) {
      if (neutron.OutsideBox(params.L)) {
        no++;
        continue; // Pomijamy neutrony, które wyszły poza próbkę
      }
      
      Atom atom = neutron.CheckReact(atoms);

      if (atom != null) {
        // System.out.println("Neutron zderzył się z atomem: " + atom.x + " " + atom.y + " " + atom.z);

        if (atom.React(100*params.A)) {
          Energy += params.ENERGY_RELEASED;
          for (int i = 0; i < params.N_NEUTRONS; i++) {
            newNeutrons.add(new Neutron(atom.x, atom.y, atom.z));
          }
          neutron.Kill();
          yes++;
        }
      } else {
        neutron.Scatter(params.B);
        neutron.Move(params.TIME_STEP); 
      }
    }

    for (Neutron neutron : newNeutrons) {
      neutrons.add(neutron); // Dodajemy nowe neutrony do listy
    }

    // Usunięcie z tablicy przereagowanych atomów i neutronów
    for (int i = 0; i < neutrons.size(); i++) {
      if (neutrons.get(i).decayed) {
        neutrons.remove(i);
        i--;
      }
    }
    
    for (int i = 0; i < atoms.size(); i++) {
      if (atoms.get(i).decayed) {
        atoms.remove(i);
        i--;
      }
    }

    System.out.println(new BigDecimal(Energy).toPlainString().replace(".", ","));


    Power = Energy - temptotal;
    Neutrons = neutrons.size();

        // n_energy.add(Energy); 
    // n_power.add(Energy - temptotal);
    // n_neutron.add((double) neutrons.size()); 
  
  }

  List<Double> getEnergy(List<Double> n) {
    n.add(Energy);
    return n;
  }

  List<Double> getPower(List<Double> n) {
    n.add(Power);
    return n;
  }

  List<Double> getNeutrons(List<Double> n) {
    n.add(Neutrons);
    return n;
  }


  public void Run() throws Exception {
    System.out.println("Hello, World!");

    System.out.println("Sukces neutronów: " + yes / (yes + no) * 100 + "%");
   
    

  }
}