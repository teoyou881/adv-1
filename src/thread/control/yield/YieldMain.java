package thread.control.yield;

import static util.ThreadUtils.sleep;

public class YieldMain {

  static final int THREAD_COUNT = 1000;

  public static void main(String[] args) {
    for (int i = 0; i < THREAD_COUNT; i++) {
      Thread thread = new Thread(new MyRunnable());
      thread.start();
    }
  }

  static class MyRunnable implements Runnable {

    public void run() {
      for (int i = 0; i < 10; i++) {
        System.out.println(Thread.currentThread().getName() + " - " +
            i);
        // 1. empty

        // 2. sleep
        //sleep(1);

        // 3. yield
        Thread.yield();
      }
    }
  }
}