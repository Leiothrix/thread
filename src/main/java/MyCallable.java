import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by adam on 2019/3/28 at 20:00.
 * PS: You may say that I'm a dreamer.But I'm not the only one.
 */
public class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("当前线程名称:" + Thread.currentThread().getName());
        return "Hello World";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("程序开始运行时线程名称:" + Thread.currentThread().getName());
        FutureTask<String> futureTask = new FutureTask<>(new MyCallable());
        Thread thread = new Thread(futureTask);
        thread.start();
        // 实现call方法有返回值，可以抛出异常
        System.out.println(futureTask.get());
    }
}
