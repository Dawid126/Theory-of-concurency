import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Library {
    Semaphore reading = new Semaphore(1);
    Semaphore writing = new Semaphore(1);

    TimeContainer timeContainer;

    private ReentrantLock libraryLock = new ReentrantLock();
    private Condition readers = libraryLock.newCondition();
    private Condition writers = libraryLock.newCondition();

    private int isReading = 0;
    private int isWriting = 0;
    private int writerWaiting = 0;
    private int readerWaiting = 0;

    int readersNumber = 0;

    public Library(TimeContainer timeContainer) {
        this.timeContainer = timeContainer;
    }

    public void beginReading() {
        try {
            long start = System.nanoTime();
            reading.acquire(1);
            timeContainer.updateReaderWaitingTime(start, System.nanoTime());
            readersNumber++;
            if(readersNumber == 1)
                writing.acquire();
            //System.out.println("Reading ID" + Thread.currentThread().getId());
            reading.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void endReading() {
        try {
            long start = System.nanoTime();
            reading.acquire(1);
            timeContainer.updateReaderWaitingTime(start, System.nanoTime());
            readersNumber--;
            if(readersNumber == 0)
                writing.release();
            reading.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void beginWriting() {
        try {
            long start = System.nanoTime();
            writing.acquire(1);
            //System.out.println("Writing ID" + Thread.currentThread().getId());
            timeContainer.updateWriterWaitingTime(start, System.nanoTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void endWriting() {
        writing.release();
        //System.out.println("Writing ID" + Thread.currentThread().getId());
    }
     /*

    public void beginReading() {
        long start = System.nanoTime();
        libraryLock.lock();

        try {
            while (writerWaiting > 0 || isWriting > 0) {
                ++readerWaiting;
                readers.await();
            }
            //program.setReaderWaitingTime(program.getReaderWaitingTime() + System.nanoTime() - tmp);
            timeContainer.updateReaderWaitingTime(start, System.nanoTime());
            //System.out.println("Reading ID" + Thread.currentThread().getId());
            isReading += 1;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            libraryLock.unlock();
        }
    }

    public void endReading() {
        long start = System.nanoTime();
        libraryLock.lock();
        timeContainer.updateReaderWaitingTime(start, System.nanoTime());

        try {
            isReading -= 1;
            if (isReading == 0) {
                if (writerWaiting > 0) {
                    --writerWaiting;
                }
                writers.signal();
            }
        } finally {
            libraryLock.unlock();
        }
    }

    public void beginWriting() {
        long start = System.nanoTime();
        libraryLock.lock();

        try {
            while (isReading + isWriting > 0) {
                ++writerWaiting;
                writers.await();
            }
            timeContainer.updateWriterWaitingTime(start, System.nanoTime());
            //System.out.println("Writing ID" + Thread.currentThread().getId());
            isWriting = 1;

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            libraryLock.unlock();
        }
    }

    public void endWriting() {
        long start = System.nanoTime();
        libraryLock.lock();
        timeContainer.updateWriterWaitingTime(start, System.nanoTime());

        try {
            isWriting = 0;
            if (readerWaiting == 0) {
                if (writerWaiting > 0) {
                    --writerWaiting;
                }
                writers.signal();
            } else {
                readerWaiting = 0;
                readers.signalAll();
            }
        } finally {
            libraryLock.unlock();
        }
    }*/
}

