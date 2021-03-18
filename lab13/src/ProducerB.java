import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannelInt;
public class ProducerB implements CSProcess
{
    private One2OneChannelInt out;
    private int howMany;
    public ProducerB(final One2OneChannelInt out, final int howMany)
    {
        this.out = out;
        this.howMany = howMany;
    }
    public void run ()
    {
        for (int i = 0; i < howMany; i++)
        {
            int item = (int)(Math.random()*100)+1;
            out.out().write(item);
            //System.out.println("Producer " + item);
        }
    }
}