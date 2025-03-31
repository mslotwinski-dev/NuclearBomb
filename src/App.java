public class App {
  public static void main(String[] args) throws Exception {
    Simulation S = new Simulation();
    S.Run();

    GUI gui = new GUI();
		gui.setVisible(true);
  }

  
}
