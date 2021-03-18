import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannelInt;
public class ConsumerB implements CSProcess
{
    private One2OneChannelInt in;
    private int howMany;
    public ConsumerB (final One2OneChannelInt in, final int howMany)
    {
        this.in = in;
        this.howMany = howMany;
    }
    public void run ()
    {
        long start = System.nanoTime();
        for (int i = 0; i < howMany; i++)
        {
            int item = in.in().read();
            //System.out.println("Consumer " + item);
        }
        long end = System.nanoTime();
        System.out.println((end - start) / 1000000);
        System.exit(0);
    }
}