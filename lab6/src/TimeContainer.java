import java.io.FileWriter;
import java.io.IOException;

public class TimeContainer {
    private long waitingTime = 0;

    public TimeContainer() {}

    public void updateWaitingTime(long start, long end) {
        this.waitingTime += (end - start);
    }

    public long getWaitingTime() {
        return this.waitingTime / 1000000;
    }

    public void saveResults(int numberOfEatings) {
        try {
            FileWriter myWriter = new FileWriter("results.txt", true);
            myWriter.write(String.format("%d %d", numberOfEatings, getWaitingTime()) + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
