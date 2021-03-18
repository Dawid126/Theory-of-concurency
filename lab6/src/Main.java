import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        ArrayList<Filozof> filozofowie = new ArrayList<>();
        ArrayList<Widelec> widelce = new ArrayList<>();
        Semaphore sem = new Semaphore(4);

        for(int i=0; i<5; i++) {
            widelce.add(new Widelec());
        }

        for(int i=0; i<5; i++) {
            Filozof filozof = new Filozof(i, new TimeContainer(), widelce, sem);
            filozofowie.add(filozof);
            filozof.start();
        }

        for(int i=0; i<5; i++) {
            try {
                filozofowie.get(i).join();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


