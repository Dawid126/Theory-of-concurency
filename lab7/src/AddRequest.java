public class AddRequest implements IMethodRequest {
    private Future future;
    private Servant servant;
    private Object object;

    public AddRequest(Future future, Servant servant, Object object) {
        this.future = future;
        this.servant = servant;
        this.object = object;
    }

    public void call() {
        servant.add(object);
        future.setResult(object);
    }
    public boolean guard() {
        return !servant.isFull();
    }
}
