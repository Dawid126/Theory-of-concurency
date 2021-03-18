import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannelInt;
import org.jcsp.lang.Guard;
import org.jcsp.lang.Alternative;

public class ProducerA implements CSProcess
{
    private One2OneChannelInt out[];
    private One2OneChannelInt jeszcze[];
    private int howMany;
    public ProducerA(final One2OneChannelInt out[], final One2OneChannelInt jeszcze[], final int howMany)
    {
        this.out = out;
        this.jeszcze = jeszcze;
        this.howMany = howMany;
    }
    public void run ()
    {
        final Guard[] guards = new Guard[jeszcze.length];
        for (int i = 0; i < out.length; i++)
            guards[i] = jeszcze[i].in();

        final Alternative alt = new Alternative(guards);
        for (int i = 0; i < howMany; i++)
        {
            int index = alt.select();
            jeszcze[index].in().read();
            int item = (int)(Math.random()*100)+1;
            out[index].out().write(item);
        }
    }
}
