public class Reader extends Thread {
    private int ID;
    private Library library;

    public Reader(int ID, Library library) {
        this.ID = ID;
        this.library = library;
    }

    public void run() {
        for (int i = 0; i < 500; i++) {
            library.beginReading();
            library.endReading();
        }
    }
}

