public class BinarySemaphore {
    private boolean _stan = true;

    public BinarySemaphore() {
    }

    public synchronized void P() {
        while(!_stan) {
            try {
                wait();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        _stan = false;

    }

    public synchronized void V() {
        _stan = true;
        notify();
    }

}
