package nuclear;

import java.util.ArrayList;
import java.util.List;

public class Data {
    
  List<Double> energy = new ArrayList<>();
  List<Double> power = new ArrayList<>();
  List<Double> neutrons = new ArrayList<>();

  Data(List<Double> energy, List<Double> power, List<Double> neutrons) {
    this.energy = energy;
    this.power = power;
    this.neutrons = neutrons;
  }

  Data() { }
}
