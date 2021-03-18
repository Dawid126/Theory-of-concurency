public class Producer extends Thread {
    private Buffer _buf;

    public Producer(Buffer buffer) {
        this._buf = buffer;
    }

    public void run() {
        for (int i = 0; i < 100; ++i) {
            _buf.put(new Item(i));
            try {
                sleep((int) (Math.random() * 70));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
