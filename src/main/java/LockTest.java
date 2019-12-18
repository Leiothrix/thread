import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author adam
 * @date 2019/12/18
 */
public class LockTest implements Runnable {
    /**
     * 10个业务员一起卖100000张票
     */
    private int ticket = 100000;

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        // task表示每个业务员分配的业务量
        int task = 10000;
        for (int i = 0; i < task; i++) {
            lock.lock();
            try {
                ticket--;
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        LockTest lockTest = new LockTest();
        int threadNum = 10;
        for (int i = 0; i < threadNum; i++) {
            new Thread(lockTest).start();
        }
        threadNum = 2;
        while (Thread.activeCount() > threadNum) {
            Thread.yield();
        }
        System.out.println("最后的ticket数为" + lockTest.ticket);
    }
}
