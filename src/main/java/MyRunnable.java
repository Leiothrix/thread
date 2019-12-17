/**
 * Created by adam on 2019/3/28 at 19:40.
 * PS: You may say that I'm a dreamer.But I'm not the only one.
 */
public class MyRunnable implements Runnable {

    /**
     * 五个业务员一起卖10张票
     */
    private int ticket = 10;

    @Override
    public void run() {
        // task表示每个业务员分配的业务量
        int task = 2;
        for (int i = 0; i < task; i++) {
            if (ticket > 0) {
                System.out.println("R公司" + Thread.currentThread().getName() + "卖了一张票，编号为r" + (ticket--));
            }
        }
    }

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        new Thread(myRunnable, "1号业务员").start();
        new Thread(myRunnable, "2号业务员").start();
        new Thread(myRunnable, "3号业务员").start();
        new Thread(myRunnable, "4号业务员").start();
        new Thread(myRunnable, "5号业务员").start();
    }
}
