public class TimeContainer {
    private long readerWaitingTime = 0;
    private long writerWaitingTime = 0;

    public TimeContainer() {}

    public void updateReaderWaitingTime(long start, long end) {
        this.readerWaitingTime += (end - start);
    }

    public void updateWriterWaitingTime(long start, long end) {
        this.writerWaitingTime += (end - start);
    }

    public long getReaderWaitingTime() {
        return this.readerWaitingTime / 1000000;
    }

    public long getWriterWaitingTime() {
        return this.writerWaitingTime / 1000000;
    }
}
