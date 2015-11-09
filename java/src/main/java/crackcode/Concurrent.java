package crackcode;

import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;

public class Concurrent {

    static class MyThread extends Thread {
        private final CountDownLatch mLatch;

        public MyThread(CountDownLatch latch) {
            mLatch = latch;
        }

        @Override
        public void run() {
            int i = 0;
            while (true) {
                try {
                    sleep(1000);
                    i++;
                    System.out.println(i);
                    if (i == 10) {
                        mLatch.countDown();
                        break;
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    static class Producer extends Thread {
        private final LinkedList<Integer> mList;
        int max = 5;

        public Producer(LinkedList<Integer> list) {
            mList = list;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                try {
                    produce(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            interrupt();
        }

        void produce(int i) throws InterruptedException {
            synchronized (mList) {
                while (mList.size() == max) {
                    System.out.println("wait for consume");
                    mList.wait();
                }
            }

            synchronized (mList) {
                mList.add(i);
                System.out.println(i + " produced");
                mList.notifyAll();
            }

        }
    }

    static class Consumer extends Thread {
        private final LinkedList<Integer> mList;

        public Consumer(LinkedList<Integer> l) {
            mList = l;

        }

        @Override
        public void run() {
            while (true) {
                try {
                    consume();
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        private void consume() throws InterruptedException {

            synchronized (mList) {
                while (mList.isEmpty()) {
                    System.out.println("wait for producing");
                    mList.wait();
                }
            }

            synchronized (mList) {
                int c = mList.removeFirst();
                System.out.println(c + " is consumed");
                mList.notifyAll();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        // CountDownLatch latch = new CountDownLatch(1);
        // MyThread t = new MyThread(latch);
        // t.start();
        // latch.await();

        LinkedList<Integer> list = new LinkedList<Integer>();
        Producer p = new Producer(list);
        Consumer c = new Consumer(list);
        p.start();
        // Thread.sleep(10);
        c.start();


    }

}
