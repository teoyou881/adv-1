package thread.control.join;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class JoinMainV4 {

  public static void main(String[] args) throws InterruptedException {
    log("Start");
    SumTask task1 = new SumTask(1, 50);
    Thread thread1 = new Thread(task1, "thread-1");
    thread1.start();

    log("join(1000) - The main thread waits for thread1 to finish for only 1 second.");
    thread1.join(1000);
    log("main finished to wait");
    log("task1.result = " + task1.result);
  }

  static class SumTask implements Runnable {

    int startValue;
    int endValue;
    int result = 0;

    public SumTask(int startValue, int endValue) {
      this.startValue = startValue;
      this.endValue = endValue;
    }

    @Override
    public void run() {
      log("job start");
      sleep(1500);

      int sum = 0;
      for (int i = startValue; i <= endValue; i++) {
        sum += i;
      }
      result = sum;
      log("job end");
    }
  }
}