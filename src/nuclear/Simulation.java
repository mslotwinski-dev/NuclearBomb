package nuclear;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulation {
  Random rand = new Random();

  SimulationParams params;
  double Energy = 0;
  double Power = 0;
  double Neutrons = 0;

  double yes = 0;
  double no = 0;

  List<Atom> atoms = new ArrayList<>();
  List<Neutron> neutrons = new ArrayList<>();

  public Simulation(SimulationParams params) {
    this.params = params;

    for (int i = 0; i < params.N_U; i++) {
      atoms.add(new Atom(rand.nextDouble() * params.L, rand.nextDouble() * params.L, rand.nextDouble() * params.L));
    }
  }

  Data getData(Data data) {
    data.energy.add(Energy);
    data.power.add(Power);
    data.neutrons.add(Neutrons);

    return data;
  }

  public void Success() throws Exception {
    System.out.println("Sukces neutronów: " + yes / (yes + no) * 100 + "%");
  }

  void Decays() {
    for (Atom atom : atoms) {
      if (atom.React(params.A)) {
        Energy += params.ENERGY_RELEASED;
        for (int i = 0; i < params.N_NEUTRONS; i++) {
          neutrons.add(new Neutron(atom.x, atom.y, atom.z));
        }
      }
    }
  }

  void Collisions() {
    List<Neutron> newNeutrons = new ArrayList<>();

    for (Neutron neutron : neutrons) {
      if (neutron.OutsideBox(params.L)) {
        no++;
        continue;
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
      neutrons.add(neutron);
    }
  }

  void Clean() {
    for (int i = 0; i < neutrons.size(); i++) {
      if (neutrons.get(i).isDecayed()) {
        neutrons.remove(i);
        i--;
      }
    }
    
    for (int i = 0; i < atoms.size(); i++) {
      if (atoms.get(i).isDecayed()) {
        atoms.remove(i);
        i--;
      }
    }
  }

  void Log() {
    System.out.println(new BigDecimal(Energy).toPlainString().replace(".", ","));
  }

  void NextStep() {
    double temptotal = Energy;
      
    Decays(); // Spontaniczne rozpady atomów uranu
    Collisions(); // Zderzenia neutronów z atomami
    Clean(); // Usunięcie neutronów i atomów, które uległy rozpadowi
    
    Log(); // Wypisanie energii

    Power = Energy - temptotal;
    Neutrons = neutrons.size();
  }



}