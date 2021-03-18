import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main{
    public static void main(String[] args){
        int width = 800;
        int height = 600;
        //MandelbrotDrawer mandelbrotDrawer = new MandelbrotDrawer(width, height);
        int numOfThreads = Integer.parseInt(args[0]) * 10;
        ExecutorService pool = Executors.newFixedThreadPool(numOfThreads);
        //ExecutorService pool = Executors.newCachedThreadPool();
        //ExecutorService pool = Executors.newWorkStealingPool();
        //ExecutorService pool = Executors.newSingleThreadExecutor();

        List<Future<BufferedImage>> futureList = new ArrayList<>();
        Callable<BufferedImage> callable;

        for(int i=1; i<=numOfThreads; i++) {
            if(height % numOfThreads != 0 && i == numOfThreads) {
                callable = new MandelbrotCallable(height/numOfThreads*i - height/numOfThreads,
                                                 height,800,150);
            } else {
                callable = new MandelbrotCallable(height/numOfThreads*i - height/numOfThreads,
                                        height/numOfThreads*i,800,150);
            }

            Future<BufferedImage> future = pool.submit(callable);
            futureList.add(future);
        }

        int i = 1;
        long start = System.nanoTime();
        try {
            for(Future<BufferedImage> future : futureList) {
                //mandelbrotDrawer.addImage(future.get(), height/numOfThreads*i - height/numOfThreads);
                BufferedImage im = future.get();
                im.getGraphics();
                i++;
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.nanoTime();
        long timeElapsed = end - start;

        try {
            FileWriter myWriter = new FileWriter("results.txt", true);
            myWriter.write(String.valueOf(timeElapsed/1000000) + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.exit(1);

    }
}
