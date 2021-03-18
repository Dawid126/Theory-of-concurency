import java.util.LinkedList;

public class Proxy {
    Scheduler scheduler;
    Servant servant;

    public Proxy(Scheduler scheduler, Servant servant){
        this.scheduler = scheduler;
        this.servant = servant;
    }
    public Future add(Object object) {
        Future future = new Future();
        IMethodRequest addRequest = new AddRequest(future, servant, object);
        scheduler.enqueue(addRequest);
        return future;
    }

    public Future remove() {
        Future future = new Future();
        IMethodRequest removeRequest = new RemoveRequest(future, servant);
        scheduler.enqueue(removeRequest);
        return future;
    }
}
