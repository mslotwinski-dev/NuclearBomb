package nuclear;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class RoundedJButton extends JButton {
	
	private static final long serialVersionUID = 1L;
    private int radius;
    private Color borderColor = Color.BLUE;
    private Boolean flashing = true;
    private Thread flashingThread;

    public RoundedJButton(String text, Icon icon) {
    	super(text, icon);
        this.radius = 20;
        setOpaque(false);
        setContentAreaFilled(false);
        
        setForeground(Color.BLACK); 
        setBackground(new Color(200, 200, 255));
        
    }
    
    public RoundedJButton(String text) {
    	super(text);
        this.radius = 20;
        setOpaque(false);
        setContentAreaFilled(false);
        
        setForeground(Color.BLACK); 
        setBackground(new Color(200, 200, 255));
        
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
                    setBackgroundColor(new Color(200, 200, 255));
                });
            }
        });

        flashingThread.start();
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        Color baseColor = getBackground();
        Color gradientTop = baseColor.brighter();
        Color gradientBottom = baseColor.darker();

        GradientPaint gp = new GradientPaint(0, 0, gradientTop, 0, height, gradientBottom);
        g2.setPaint(gp);
        g2.fillRoundRect(0, 0, width - 1, height - 1, radius, radius);

        super.paintComponent(g2);

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
