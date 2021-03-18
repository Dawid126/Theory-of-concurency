import java.util.Random;

public class Producer extends Thread{
    private int ID;
    private Proxy proxy;
    private static Random rand = new Random();

    public Producer(int ID, Proxy proxy) {
        this.ID = ID;
        this.proxy = proxy;
    }

    public void run(){
        while(true){
            int tmp = rand.nextInt(150);
            Future added = proxy.add(tmp);

            System.out.println("Worker " + ID + " created " + tmp);

            while(!added.isAvaiable()){
                System.out.println("Worker " + ID + " is waiting.");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Worker " + ID
                    + " added: " + added.getResult());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
