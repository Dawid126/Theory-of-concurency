import java.util.concurrent.locks.ReentrantLock;

public class SecondList {
    private Object val;
    private SecondList next;
    private static ReentrantLock lock = new ReentrantLock();
    private static long sleepTime;

    public SecondList(Object val, SecondList next) {
        this.val = val;
        this.next = next;
    }

    public boolean contains(Object o) throws InterruptedException {
        SecondList tmp = this;
        lock.lock();
        try {
            while (tmp != null) {
                if (val == o) {
                    Thread.sleep(sleepTime / 10);
                    return true;
                }
                tmp = tmp.next;
            }
        } finally {
            lock.unlock();
        }
        return false;
    }

    public boolean remove(Object o) throws InterruptedException {
        SecondList previous = null, tmp = this;
        lock.lock();
        try {
            while (tmp != null) {
                if (val == o) {
                    if (previous != null) {
                        previous.next = tmp.next;
                        tmp.next = null;
                    }
                    Thread.sleep(sleepTime / 3);
                    return true;
                }
                previous = tmp;
                tmp = tmp.next;
            }
        } finally {
            lock.unlock();
        }
        return false;
    }

    public boolean add(Object o) throws InterruptedException {
        if (o == null) {
            return false;
        }
        SecondList tmp = this, next = this.next;
        lock.lock();
        try {
            while (next != null) {
                tmp = next;
                next = next.next;
            }
            tmp.next = new SecondList(o, null);
            Thread.sleep(sleepTime);
            return true;
        } finally {
            lock.unlock();
        }
    }

    public static void setSleepTime(long sleepTime) {
        SecondList.sleepTime = sleepTime;
    }
}

