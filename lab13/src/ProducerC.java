public class ProducerC extends Thread
{
    private BufferC buff;
    private int howMany;
    public ProducerC (final BufferC buff, final int howMany)
    {
        this.buff = buff;
        this.howMany = howMany;
    }

    public void run()
    {
        for (int i = 0; i < howMany; i++)
        {
            int item = (int)(Math.random()*100)+1;
            buff.put(item);
        }
    }
}

