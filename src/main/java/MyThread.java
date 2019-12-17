import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by adam on 2019/3/28 at 15:26.
 * PS: You may say that I'm a dreamer.But I'm not the only one.
 */
public class MyThread extends Thread {

    /**
     * 假设总共卖10张票
     */
    private AtomicInteger ticket = new AtomicInteger(10);

    @Override
    public void run() {
        // 每个业务员安排的业务量
        int task = 5;
        for (int i = 0; i < task; i++) {
            if (ticket.intValue() > 0) {
                System.out.println("T公司" + Thread.currentThread().getName() + "卖了一张票，编号为t" + (ticket.getAndDecrement()));
            }
        }
    }

    /**
     * 使用Thread也可以实现资源共享😯
     */
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        new Thread(myThread, "1号业务员").start();
        new Thread(myThread, "2号业务员").start();
    }
}
