import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannelInt;
public class BufferA implements CSProcess
{
    private One2OneChannelInt in;
    private One2OneChannelInt out;
    private One2OneChannelInt jeszcze;
    public BufferA (final One2OneChannelInt in, final One2OneChannelInt out, final One2OneChannelInt jeszcze)
    {
        this.out = out;
        this.in = in;
        this.jeszcze = jeszcze;
    }
    public void run ()
    {
        while (true)
        {
            jeszcze.out().write(0);
            out.out().write(in.in().read());
        }
    }
}

