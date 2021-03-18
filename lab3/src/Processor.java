public class Processor extends Thread{
    private Buffer _buf;
    private final int stageOfProductionID;
    private int lastPosition = 0;
    private int sleepTime;

    public Processor(Buffer buffer, int ID, int sleepTime) {
        this._buf = buffer;
        this.stageOfProductionID = ID;
        this.sleepTime = sleepTime;
    }

    public int getLastPosition(){
        return lastPosition;
    }

    public int getStageOfProductionID() {
        return stageOfProductionID;
    }

    public void setLastPosition(int lastPosition) {
        this.lastPosition = lastPosition;
    }

    public void run() {
        for (int i = 0; i < 100; ++i) {
            try {
                _buf.processItem(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                sleep((int) (Math.random() * sleepTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
