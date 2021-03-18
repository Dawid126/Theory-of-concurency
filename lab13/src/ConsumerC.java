public class ConsumerC extends Thread
{
    private BufferC buff;
    private int howMany;

    public ConsumerC (final BufferC buff, final int howMany)
    {
        this.buff = buff;
        this.howMany = howMany;
    }

    public void run()
    {
        long start = System.nanoTime();
        for (int i = 0; i < howMany; i++)
        {
            int item = buff.get();
            // System.out.println(item);
        }
        long end = System.nanoTime();
        System.out.println((end - start) / 1000000);
        System.exit(0);
    }
}

