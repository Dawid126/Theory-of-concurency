public class RemoveRequest implements IMethodRequest{
    private Future future;
    private Servant servant;

    public RemoveRequest(Future future, Servant servant) {
        this.future = future;
        this.servant = servant;
    }

    public void call() {
        future.setResult(servant.remove());
    }
    public boolean guard() {
        return !servant.isEmpty();
    }
}
