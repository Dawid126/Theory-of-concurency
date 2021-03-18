import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Filozof extends Thread {
    private int _licznik = 0;
    private int ID = 0;
    private final TimeContainer timeContainer;
    private final ArrayList<Widelec> widelce;
    private final Semaphore sem;

    public Filozof(int ID, TimeContainer timeContainer, ArrayList<Widelec> widelce, Semaphore sem){
        this.ID = ID;
        this.timeContainer = timeContainer;
        this.widelce = widelce;
        this.sem = sem;
    }

    public void run() {
        int lewyWidelec = ID;
        int prawyWidelec = (ID+1)%5;
        while (true) {

            long start = System.nanoTime();

            try {
                sem.acquire(1);

                widelce.get(lewyWidelec).podnies();
                widelce.get(prawyWidelec).podnies();

                ++_licznik;

                widelce.get(lewyWidelec).odloz();
                widelce.get(prawyWidelec).odloz();

                timeContainer.updateWaitingTime(start, System.nanoTime());

                if (_licznik % 100000 == 0) {
                    System.out.println("Filozof: " + this.ID +
                            " jadlem " + _licznik + " razy");
                    timeContainer.saveResults(_licznik);
                }

                sem.release(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
