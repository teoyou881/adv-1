package thread.start;

import static util.MyLogger.log;

public class InnerRunnableMainV1 {

  public static void main(String[] args) {
    log("main() start");

    MyRunnable myRunnable = new MyRunnable();
    Thread thread = new Thread(myRunnable);
    thread.start();
    log("main() end");
  }

  static class MyRunnable implements Runnable {
    
    @Override
    public void run() {
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      log("run()");
    }
  }

}
