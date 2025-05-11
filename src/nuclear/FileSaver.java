package nuclear;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.awt.*;

public class FileSaver {

    public static void Save(Data data) {
        String filename = "";

        try {
            FileDialog dialog = new FileDialog((Frame) null, "Select File to Save", FileDialog.SAVE);
            dialog.setFile("data.txt");
            dialog.setVisible(true);
            


            if (dialog.getFile() != null) {
                filename = dialog.getDirectory() + dialog.getFile();
            } else {
                System.out.println("No file selected");
                return;
            }

            try (OutputStreamWriter osw = new OutputStreamWriter(
                    new FileOutputStream(filename),
                    Charset.forName("UTF-8").newEncoder())) {
                for (int i = 0; i < data.energy.size(); i++) {
                    String line = String.format("%s %s %s%n",
                            new BigDecimal(data.energy.get(i)).toPlainString(),
                            new BigDecimal(data.power.get(i)).toPlainString(),
                            new BigDecimal(data.neutrons.get(i)).toPlainString());
                    osw.write(line);
                }
            }

        } catch (FileNotFoundException e1) {
            System.err.println("File not found: " + e1.getMessage());
        } catch (IOException e1) {
            System.err.println("I/O error: " + e1.getMessage());
        }
    }

    public static Data Open() {
        String filename = "";

        FileDialog dialog = new FileDialog((Frame) null, "Select File to Open", FileDialog.LOAD);
        dialog.setFile("*.txt");
        dialog.setVisible(true);

        if (dialog.getFile() != null) {
            filename = dialog.getDirectory() + dialog.getFile();
        } else {
            System.out.println("No file selected");
            return null;
        }

        List<Double> n_energy = new ArrayList<>();
        List<Double> n_power = new ArrayList<>();
        List<Double> n_neutron = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filename), Charset.forName("UTF-8")))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length == 3) {
                    try {
                        n_energy.add(Double.parseDouble(parts[0]));
                        n_power.add(Double.parseDouble(parts[1]));
                        n_neutron.add(Double.parseDouble(parts[2]));
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid number format in line: " + line);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            return null;
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
            return null;
        }

        return new Data(n_energy, n_power, n_neutron);
    }

}