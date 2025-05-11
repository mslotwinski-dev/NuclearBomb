package nuclear;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class FileSaverFX {

    private static boolean initialized = false;

    public void save(List<Double> n_energy, List<Double> n_power, List<Double> n_neutron) {
        if (!initialized) {
            new JFXPanel(); // Uruchom JavaFX tylko raz
            initialized = true;
        }

        CountDownLatch latch = new CountDownLatch(1);
        final File[] selectedFile = new File[1];

        Platform.runLater(() -> {
            try {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Select File to Save");
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

                selectedFile[0] = fileChooser.showSaveDialog(new Stage());

                latch.countDown(); // sygnał do kontynuacji

            } catch (Exception e) {
                e.printStackTrace();
                latch.countDown(); // mimo błędu kontynuuj
            }
        });

        try {
            latch.await(); // czekaj aż użytkownik wybierze plik
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        File file = selectedFile[0];
        if (file == null) return;

        if (!file.getName().endsWith(".txt")) {
            file = new File(file.getAbsolutePath() + ".txt");
        }

        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(file), StandardCharsets.UTF_8)) {

            for (int i = 0; i < n_energy.size(); i++) {
                String line = String.format("%s %s %s%n",
                        new BigDecimal(n_energy.get(i)).toPlainString(),
                        new BigDecimal(n_power.get(i)).toPlainString(),
                        new BigDecimal(n_neutron.get(i)).toPlainString());
                writer.write(line);
            }

            JOptionPane.showMessageDialog(null, "File saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}