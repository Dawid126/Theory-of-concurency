import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannelInt;

public class BufferB implements CSProcess
{
    private One2OneChannelInt in, out;
    public BufferB (final One2OneChannelInt in, final One2OneChannelInt out)
    {
        this.out = out;
        this.in = in;
    }
    public void run ()
    {
        while (true)
            out.out().write(in.in().read());
    }
}

