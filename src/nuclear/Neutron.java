package nuclear;

import java.util.List;
import java.util.Random;

public class Neutron {
  static double speed = 2.2e7;

  private double x, y, z;
  private double vx, vy, vz;
  private boolean decayed = false; 

  Neutron(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
    
    Scatter(1);
  }

  void Move(double t) {
    this.x += this.vx * t;
    this.y += this.vy * t;
    this.z += this.vz * t;
  }

  boolean OutsideBox(double L) {
    if (this.x < 0 || this.x > L || this.y < 0 || this.y > L || this.z < 0 || this.z > L) {
      decayed = true;
      return true;
    };

    return false;
  }


  void Scatter(double B) {
    Random rand = new Random();

    if (rand.nextDouble() < B) {
      double theta = Math.PI * rand.nextDouble();
      double phi = 2 * Math.PI * rand.nextDouble();
      
      this.vx = speed * Math.sin(theta) * Math.cos(phi);
      this.vy = speed * Math.sin(theta) * Math.sin(phi);
      this.vz = speed * Math.cos(theta);
    }
    
  }

  Atom CheckReact(List<Atom> atoms) {
    for (Atom atom : atoms) {
      if (Intersects(atom)) {
        if (atom.isDecayed()) {
          continue;
        }
        return atom;
      }
    }
    return null;
  }
  
  boolean Intersects(Atom atom) {
    double dx = atom.x - this.x;
    double dy = atom.y - this.y;
    double dz = atom.z - this.z;

    // Współrzędne neutronu: R(t) = p_0 + v * t
    // Współrzędne atomu: C = (x, y, z), promień r
    // Warunek przecięcia: || R(t) - C ||^2 = r^2
  
    double a = vx * vx + vy * vy + vz * vz;
    double b = 2 * (vx * dx + vy * dy + vz * dz);
    double c = dx * dx + dy * dy + dz * dz - Atom.RADIUS * Atom.RADIUS;
  
    double discriminant = b * b - 4 * a * c;
  
    if (discriminant < 0) {
      return false;
    }
  
    double sqrtDisc = Math.sqrt(discriminant);
    double t1 = (-b - sqrtDisc) / (2 * a);
    double t2 = (-b + sqrtDisc) / (2 * a);
  
    return t1 >= 0 || t2 >= 0;
  }

  void Print() {
    System.out.println("Neutron: " + this.x + " " + this.y + " " + this.z);
  }

  void Kill() {
    this.decayed = true;
  }

  boolean isDecayed() {
    return decayed; 
  }

}
