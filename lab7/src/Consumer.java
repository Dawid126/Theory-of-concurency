public class Consumer extends Thread {
    private int ID;
    private Proxy proxy;

    public Consumer(int ID, Proxy proxy) {
        this.ID = ID;
        this.proxy = proxy;
    }

    public void run(){
        while(true){
            Future consumed = proxy.remove();
            while(!consumed.isAvaiable()){
                System.out.println("Consument " + ID + " is waiting.");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Consument " + ID
                    + " consumed: " + consumed.getResult());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
