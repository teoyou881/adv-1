package thread.sync.lock;

import java.util.concurrent.locks.LockSupport;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class LockSupportMainV2 {

  public static void main(String[] args) {
    Thread thread1 = new Thread(new ParkTask(), "Thread-1");
    thread1.start();

    // Wait for a short time to allow thread1 to enter the park state.
    sleep(100);

    log("Thread-1 state: " + thread1.getState());
  }

  static class ParkTask implements Runnable {

    @Override
    public void run() {
      log("park started, waiting for 2 seconds");
      log("Interrupt status: " + Thread.currentThread().isInterrupted());
      LockSupport.parkNanos(2000_000000); // Using parkNanos
      log("park ended, state: " + Thread.currentThread().getState());
      log("Interrupt status: " + Thread.currentThread().isInterrupted());
    }
  }
}
