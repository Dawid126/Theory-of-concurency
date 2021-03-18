public class Semaphore {
    int counter;

    public Semaphore(int startingValue) {
        this.counter = startingValue;
    }

    public synchronized void P(int who) {
        while (counter <=0) {
            try {
                if(who == 0) {
                    System.out.println("Thread " + Thread.currentThread().getId() + " waiting to put items");
                }
                else {
                    System.out.println("Thread " + Thread.currentThread().getId() + " waiting to get items");
                }
                wait();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.counter--;
    }

    public synchronized void V() {
        counter++;
        notify();
    }
}
