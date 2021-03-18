import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        int writersNumber = Integer.parseInt(args[0]);
        int readersNumber = Integer.parseInt(args[1]);


        ArrayList<Writer> writers = new ArrayList<>();
        ArrayList<Reader> readers = new ArrayList<>();

        TimeContainer timeContainer = new TimeContainer();
        final Library library = new Library(timeContainer);

        for (int i = 0; i < writersNumber; ++i) {
            writers.add(new Writer(i, library));
            writers.get(i).start();

        }
        for (int i = 0; i < readersNumber; ++i) {
            readers.add(new Reader(i, library));
            readers.get(i).start();
        }

        for (int i = 0; i < writersNumber; ++i) {
            try {
                writers.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < readersNumber; ++i) {
            try {
                readers.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long meanReaderWaitingTime = timeContainer.getReaderWaitingTime() / readersNumber;
        long meanWriterWaitingTime = timeContainer.getWriterWaitingTime() / writersNumber;

        try {
            FileWriter myWriter = new FileWriter("results.txt", true);
            myWriter.write(String.format("%d %d %d %d", writersNumber, readersNumber, meanWriterWaitingTime, meanReaderWaitingTime) + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }
}
