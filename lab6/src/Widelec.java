public class Widelec {
    boolean podniesiony = false;

    public synchronized void podnies() {
        try{
            while(podniesiony)
            {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        podniesiony = true;

    }
    public synchronized void odloz() {
        podniesiony = false;
        notifyAll();
    }
}
