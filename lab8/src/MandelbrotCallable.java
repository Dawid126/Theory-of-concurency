import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class MandelbrotCallable implements Callable {

    private final int MAX_ITER = 6000;
    private double zx, zy, cX, cY, tmp;
    private final int heightStart;
    private final int heightEnd;
    private final int width;
    private final double ZOOM;
    private BufferedImage I;

    public MandelbrotCallable(int heightStart, int heightEnd, int width, int ZOOM) {
        this.heightStart = heightStart;
        this.heightEnd = heightEnd;
        this.width = width;
        this.ZOOM = ZOOM;
        I = new BufferedImage(width, heightEnd-heightStart, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public BufferedImage call(){
        for (int y = heightStart; y < heightEnd; y++) {
            for (int x = 0; x < width; x++) {

                zx = zy = 0;
                cX = (x - 400) / ZOOM;
                cY = (y - 300) / ZOOM;
                int iter = MAX_ITER;
                while (zx * zx + zy * zy < 4 && iter > 0) {
                    tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter--;
                }
                I.setRGB(x, y-heightStart, iter | (iter << 8));
            }
        }
        return I;
    }

}
