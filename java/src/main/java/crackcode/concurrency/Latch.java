package crackcode.concurrency;

import java.util.concurrent.CountDownLatch;

/**
 * Created by darren on 2016. 9. 12..
 */
public class Latch {

    static class Worker implements Runnable{

        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;

        public Worker(CountDownLatch start, CountDownLatch done){
            this.startSignal = start;
            this.doneSignal = done;
        }

        @Override
        public void run() {
            try {
                startSignal.countDown();
                startSignal.await();
                System.out.println("Started workder");
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getId() + ":"+i);
                }

                doneSignal.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int n = 2;
        final CountDownLatch startSignal = new CountDownLatch(n);
        final CountDownLatch doneSignal = new CountDownLatch(n);

        for (int i = 0; i < n; i++) {
            new Thread(new Worker(startSignal, doneSignal)).start();
        }
    }
}
