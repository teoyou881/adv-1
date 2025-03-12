package thread.cas.spinlock;

import java.util.concurrent.atomic.AtomicBoolean;

import static util.MyLogger.log;

public class SpinLock {

  private final AtomicBoolean lock = new AtomicBoolean(false);

  public void lock() {
    log("attempt to lock");
    while (!lock.compareAndSet(false, true)) {
      // Wait for the lock to be acquired.
      log("fail to get lock");
    }
    log("Lock acquisition success");
  }

  public void unlock() {
    lock.set(false);
    log("return lock");
  }
}