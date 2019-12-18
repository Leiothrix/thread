import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by adam on 2019/3/28 at 19:40.
 * PS: You may say that I'm a dreamer.But I'm not the only one.
 */
public class MyRunnable implements Runnable {

    /**
     * 10个业务员一起卖100000张票
     */
    private volatile AtomicInteger ticket = new AtomicInteger(100000);

    @Override
    public void run() {
        // task表示每个业务员分配的业务量
        int task = 10000;
        for (int i = 0; i < task; i++) {
            ticket.getAndDecrement();
        }
    }

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        int threadNum = 10;
        for (int i = 0; i < threadNum; i++) {
            new Thread(myRunnable).start();
        }
        // 保证前面的线程都执行完
        // 在IDEA环境下运行程序除了Main进程还会会多出一个Monitor Ctrl-break线程，所以判断工作线程执行完毕的activeCount指定为2
        // debug模式下可以将这个值置为1
        threadNum = 2;
        while (Thread.activeCount() > threadNum) {
//            Thread.currentThread().getThreadGroup().list();
            Thread.yield();
        }
        System.out.println("最后的ticket数为" + myRunnable.ticket);
    }
}
