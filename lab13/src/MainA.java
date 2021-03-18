import org.jcsp.lang.Parallel;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannelInt;
import org.jcsp.lang.StandardChannelIntFactory;
public final class MainA
{
    public static void main (String[] args)
    {
        final int nBuffers = 10;
        final int nItems = 10000;
        StandardChannelIntFactory fact = new StandardChannelIntFactory();
        final One2OneChannelInt channels[] = fact.createOne2One(nBuffers + 1);
        CSProcess[] procList = new CSProcess[nBuffers + 2];
        procList[0] = new ProducerB(channels[0], nItems);
        procList[1] = new ConsumerB(channels[nBuffers], nItems);
        for (int i = 0; i < nBuffers; i++)
            procList[i + 2] = new BufferB(channels[i], channels[i + 1]);

        Parallel par = new Parallel(procList);
        par.run();
    }
}



