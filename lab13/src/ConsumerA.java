import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannelInt;
import org.jcsp.lang.Guard;
import org.jcsp.lang.Alternative;

public class ConsumerA implements CSProcess
{
    private One2OneChannelInt in[];
    private int howMany;
    public ConsumerA(final One2OneChannelInt in[], final int howMany)
    {
        this.in = in;
        this.howMany = howMany;
    }
    public void run ()
    {
        long start = System.nanoTime();
        final Guard[] guards = new Guard[in.length];
        for (int i = 0; i < in.length; i++)
            guards[i] = in[i].in();
        final Alternative alt = new Alternative(guards);
        for (int i = 0; i < howMany; i++)
        {
            int index = alt.select();
            int item = in[index].in().read();
            //System.out.println(index + ": " + item);
        }
        long end = System.nanoTime();
        System.out.println((end - start) / 1000000);
        System.exit(0);
    }
}

