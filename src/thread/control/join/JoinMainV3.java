package thread.control.join;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class JoinMainV3 {

  public static void main(String[] args) throws InterruptedException {
    log("Start");
    SumTask task1 = new SumTask(1, 50);
    SumTask task2 = new SumTask(51, 100);
    Thread thread1 = new Thread(task1, "thread-1");
    Thread thread2 = new Thread(task2, "thread-2");
    thread1.start();
    thread2.start();

    log("join() - The main thread waits for thread1 and thread2 to finish");
    thread1.join();
    thread2.join();
    log("main finished to wait");
    log("task1.result = " + task1.result);
    log("task2.result = " + task2.result);
    int sumAll = task1.result + task2.result;
    log("task1 + task2 = " + sumAll);
    log("End");
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