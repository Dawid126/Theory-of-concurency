import java.util.concurrent.locks.ReentrantLock;

public class FirstList {
    private Object val;
    private FirstList next;
    private ReentrantLock lock;
    private static long sleepTime;

    public FirstList(Object val, FirstList next) {
        this.val = val;
        this.next = next;
        lock = new ReentrantLock();
    }

    public boolean contains(Object o) throws InterruptedException {
        FirstList previous = null, tmp = this;
        lock.lock();
        try {
            while (tmp != null) {
                if (val == o) {
                    Thread.sleep(sleepTime / 10);
                    return true;
                }
                previous = tmp;
                tmp = tmp.next;
                try {
                    if (tmp != null) {
                        tmp.lock.lock();
                    }
                } finally {
                    previous.lock.unlock();
                }
            }
        } finally {
            if (tmp != null) {
                tmp.lock.unlock();
            }
        }
        return false;
    }


    public boolean remove(Object o) throws InterruptedException {
        FirstList prevprev = null, previous = null, tmp = this;
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
                prevprev = previous;
                previous = tmp;
                tmp = tmp.next;
                try {
                    if (tmp != null) {
                        tmp.lock.lock();
                    }
                } finally {
                    if (prevprev != null) {
                        prevprev.lock.unlock();
                    }
                }
            }
        } finally {
            if (previous != prevprev) {
                previous.lock.unlock();
            }
            if (tmp != null) {
                tmp.lock.unlock();
            }
        }
        return false;
    }

    public boolean add(Object o) throws InterruptedException {
        if (o == null) {
            return false;
        }
        FirstList tmp = this, next = this.next;
        lock.lock();
        try {
            while (next != null) {
                try {
                    next.lock.lock();
                } finally {
                    tmp.lock.unlock();
                }
                tmp = next;
                next = next.next;
            }
            tmp.next = new FirstList(o, null);
            Thread.sleep(sleepTime);
            return true;
        } finally {
            tmp.lock.unlock();
            if (next != tmp && next != null) {
                next.lock.unlock();
            }
        }
    }

    public static void setSleepTime(long sleepTime) {
        FirstList.sleepTime = sleepTime;
    }
}

