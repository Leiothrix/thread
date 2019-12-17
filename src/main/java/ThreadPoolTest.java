import java.util.concurrent.*;

/**
 * @author adam
 * @date 2019/12/17
 */
public class ThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        // 核心线程为5,最大线程数为10,超出核心线程数的线程存活时间为5s,设定任务有界队列长度为50,任务队列满了之后,开启新线程执行任务
        // 如果线程数开到最大后,任务队列还是满了,线程池将拒绝接收新任务,注意观察处理任务的线程数增长情况以及偶尔发生的拒绝任务执行情况
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<>(50), Thread::new, new ThreadPoolExecutor.AbortPolicy());
        int task = 1000;
        for (int i = 0; i < task; i++) {
            final int index = i + 1;
            threadPoolExecutor.submit(() -> {
                //线程打印输出
                System.out.println("我是线程" + Thread.currentThread().getName() + ",正在执行任务:" + index);
                try {
                    //模拟线程执行时间，10ms
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            // 稍微限制一下提交任务的时间间隔
            Thread.sleep(1);
        }
        threadPoolExecutor.shutdown();
    }
}
