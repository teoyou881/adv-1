package thread.start.test;

import static util.MyLogger.log;

public class StartTest2Main {

  public static void main(String[] args) {

//    CounterRunnable counter = new CounterRunnable();
//    Thread thread = new Thread(counter);
//    thread.setName("counter");
//    thread.start();

    Thread thread = new Thread(new CounterRunnable(), "counter");
    thread.start();

  }

  static class CounterRunnable implements Runnable {

    @Override
    public void run() {
      for (int i = 0; i < 5; i++) {
        try {
          Thread.sleep(1000);
          log(i + 1);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }
}