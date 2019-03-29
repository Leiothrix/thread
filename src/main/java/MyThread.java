/**
 * Created by adam on 2019/3/28 at 15:26.
 * PS: You may say that I'm a dreamer.But I'm not the only one.
 */
public class MyThread extends Thread{

    private String name;
    private int ticket = 5;

    private MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + ":T公司" + name + "卖了一张票，编号为t" + (ticket--));
            }
        }
    }

    public static void main(String[] args){
        new MyThread("1号业务员").start();
        new MyThread("2号业务员").start();
    }
}
