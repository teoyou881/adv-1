package thread.start.test;

import util.MyLogger;

public class StartTest1Main {

  public static void main(String[] args) {
    CounterThread counterThread = new CounterThread();
    counterThread.start();
  }


  static class CounterThread extends Thread {

    @Override
    public void run() {
      for (int i = 0; i < 5; i++) {
        try {
          Thread.sleep(1000);
          MyLogger.log(i + 1);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }
}