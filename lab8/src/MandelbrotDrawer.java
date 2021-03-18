import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class MandelbrotDrawer extends JFrame {
    private final int MAX_ITER = 5700;
    private final double ZOOM = 150;
    private BufferedImage combined;
    private double zx, zy, cX, cY, tmp;

    public MandelbrotDrawer(int width, int height){
        super("Mandelbrot Set");
        setBounds(100, 100, width, height); //800x600
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        combined = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
    }

    public void addImage(BufferedImage imageToAdd, int height) {
        Graphics g = combined.getGraphics();
        g.drawImage(imageToAdd, 0, height, null);
        this.repaint();
    }


    @Override
    public void paint(Graphics g) {
        g.drawImage(combined, 0, 0, this);
    }
}
