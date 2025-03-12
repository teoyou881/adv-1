package thread.cas.spinlock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class SpinLockBad {

  private volatile boolean lock = false;

  // problem:
  // There are two places where atomic issues arise

  public void lock() {
    log("attempt to lock");
    while (true) {
      if (!lock) { // 1. check lock
        sleep(100); // thread wait
        lock = true; // 2. change lock's status
        break;
      } else {

        log("fail to get lock");
      }
    }
    log("Lock acquisition success");
  }

  public void unlock() {
    // atomic operation
    lock = false;
    log("return lock");
  }
}