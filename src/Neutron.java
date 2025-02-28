import java.util.Random;

public class Neutron {
  static double speed = 2200;

  double x, y, z;
  double vx, vy, vz;

  Neutron(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
    
    Scatter();
  }

  void Move(double t) {
    this.x += this.vx * t;
    this.y += this.vy * t;
    this.z += this.vz * t;
  }

  boolean InBox(double L) {
    return this.x < 0 || this.x > L || this.y < 0 || this.y > L || this.z < 0 || this.z > L;
  }

  void Scatter() {
    Random rand = new Random();

    double theta = Math.PI * rand.nextDouble();
    double phi = 2 * Math.PI * rand.nextDouble();
    
    this.vx = speed * Math.sin(theta) * Math.cos(phi);
    this.vy = speed * Math.sin(theta) * Math.sin(phi);
    this.vz = speed * Math.cos(theta);
  }
}
