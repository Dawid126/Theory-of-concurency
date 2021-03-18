class Consumer extends Thread {
    private Buffer _buf;
    private int lastPosition = 0;
    private int counter = 0;

    public Consumer(Buffer buffer) {
        this._buf = buffer;
    }

    public int getLastPosition(){
        return lastPosition;
    }

    public void setLastPosition(int lastPosition) {
        this.lastPosition = lastPosition;
    }

    public void run() {
        for (int i = 0; i < 100; ++i) {
            _buf.get(this);
            try {
                sleep((int) (Math.random() * 70));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}