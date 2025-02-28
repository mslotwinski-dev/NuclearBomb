public class Atom {
  double x, y, z;
  boolean decayed = false;

  Atom(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  void React() {
    this.decayed = true;
  }
}
