public class Future {
    private Object object;

    public boolean isAvaiable() {
        return object!=null;
    }

    public void setResult(Object object) {
        this.object = object;
    }

    public Object getResult() {
        return object;
    }
}
