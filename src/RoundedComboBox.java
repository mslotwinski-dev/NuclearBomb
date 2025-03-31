import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

public class RoundedComboBox<E> extends JComboBox<E> {
    private static final long serialVersionUID = 1L;
    private int radius;
    private Color borderColor = Color.BLUE;

    public RoundedComboBox(E[] description,int radius) {
        super(new DefaultComboBoxModel<>(description));
        this.radius = radius;
        setOpaque(false);
        setUI(new RoundedComboBoxUI());
    }

    public void setBorderColor(Color color) {
        this.borderColor = color;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, radius, radius);

        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(borderColor);
        g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, radius, radius);
        g2.dispose();
    }

    @Override
    public Insets getInsets() {
        int padding = radius / 3;
        return new Insets(padding, padding * 2, padding, padding * 2);
    }

    // 🔹 Własne UI dla ComboBox, aby poprawnie rysować strzałkę
    private class RoundedComboBoxUI extends BasicComboBoxUI {
        @Override
        protected JButton createArrowButton() {
            JButton arrowButton = new JButton("▼");
            arrowButton.setBorderPainted(false);
            arrowButton.setContentAreaFilled(false);
            arrowButton.setFocusPainted(false);
            arrowButton.setOpaque(false);
            return arrowButton;
        }
    }
    
}