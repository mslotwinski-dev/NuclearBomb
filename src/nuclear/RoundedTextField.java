package nuclear;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class RoundedTextField extends JTextField {
    private static final long serialVersionUID = 1L;
    private int radius;
    private Color borderColor = Color.BLUE;
    private String placeholder = "";
    private Boolean flashing = true;
    private Thread flashingThread;
    
    public RoundedTextField(int columns, int radius) {
        super(columns);
        this.radius = radius;
        setOpaque(false);

        setForeground(Color.BLACK); 
        setBackground(Color.WHITE);
        setCaretColor(Color.BLACK);
        
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
    
    public void setBackgroundColor(Color color) {
    	this.setBackground(color);
    	repaint();
    }
    
    public void setFlashing(boolean aa) {
        this.flashing = aa;
        if (!aa && flashingThread != null) {
            flashingThread.interrupt();
            flashingThread = null;
        }
    }
    
    public void FlashingColor(Color kolor1, Color kolor2) {
        if (flashingThread != null && flashingThread.isAlive()) return;

        flashing = true;
        flashingThread = new Thread(() -> {
            int i = 0;
            boolean forward = true;

            try {
                while (flashing && !Thread.currentThread().isInterrupted()) {
                    if (forward) {
                        i++;
                        if (i >= 100) forward = false;
                    } else {
                        i--;
                        if (i <= 0) forward = true;
                    }

                    float t = (float) i / 100;
                    int r = (int) ((1 - t) * kolor1.getRed() + t * kolor2.getRed());
                    int g = (int) ((1 - t) * kolor1.getGreen() + t * kolor2.getGreen());
                    int b = (int) ((1 - t) * kolor1.getBlue() + t * kolor2.getBlue());

                    Color kolor = new Color(r, g, b);
                    Color kolorTlo = new Color(r, g, b, 48);

                    SwingUtilities.invokeLater(() -> {
                        setBorderColor(kolor);
                        setBackgroundColor(kolorTlo);
                    });

                    Thread.sleep(25);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                SwingUtilities.invokeLater(() -> {
                    setBorderColor(Color.BLUE);
                    setBackgroundColor(Color.WHITE);
                });
            }
        });

        flashingThread.start();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, radius, radius);

        super.paintComponent(g);

        
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