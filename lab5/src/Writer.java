public class Writer extends Thread {
    private int ID;
    private Library library;

    public Writer(int ID, Library library) {
        this.ID = ID;
        this.library = library;
    }

    public void run() {
        for (int i = 0; i < 500; i++) {
            library.beginWriting();
            library.endWriting();
        }
    }
}
