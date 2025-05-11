package nuclear;

import java.util.Random;

public class Atom {
  static double RADIUS = 1e-8;
  Random rand = new Random();

  double x, y, z;
  boolean decayed = false;  

  Atom(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  boolean React(double A) {

    if (!decayed && rand.nextDouble() < A) {
      decayed = true;
     
      return true; // Atom uległ rozpadowi
    } else {
      return false; // Atom nie uległ rozpadowi
    }
  }

  void Print() {
    System.out.println("Atom: " + this.x + " " + this.y + " " + this.z);
  }

  boolean isDecayed() {
    return decayed;
  }
  
}
