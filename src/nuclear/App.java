package nuclear;

public class App {
  static Data data = new Data();
  public static void main(String[] args) throws Exception {


    SimulationParams params = new SimulationParams();
    Simulation S = new Simulation(params);

    for (int t = 0; t < params.MAX_ITER; t++) {
      S.NextStep();
      data = S.getData(data);
    }

    // SwingUtilities.invokeLater(() -> FileSaver.Save(data));
    // data = FileSaver.Open();

    GUI gui = new GUI();
		gui.setVisible(true);
  }
}
