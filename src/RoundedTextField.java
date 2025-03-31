import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class RoundedTextField extends JTextField {
    private static final long serialVersionUID = 1L;
    private int radius;
    private Color borderColor = Color.BLUE;
    private String placeholder = "";

    public RoundedTextField(int columns, int radius) {
        super(columns);
        this.radius = radius;
        setOpaque(false);

        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                repaint();
            }
        });
    }
    
    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        repaint();
    }

    public void setBorderColor(Color color) {
        this.borderColor = color;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, radius, radius);

        if (getText().isEmpty() && !isFocusOwner()) {
            g2.setColor(new Color(150, 150, 150, 150));
            g2.setFont(getFont().deriveFont(Font.ITALIC));
            g2.drawString(placeholder, getInsets().left, getHeight() / 2 + getFont().getSize() / 2 - 2);
        }

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
}