package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccountV6 implements BankAccount {

  private final Lock lock = new ReentrantLock();
  private int balance;

  public BankAccountV6(int initialBalance) {
    this.balance = initialBalance;
  }

  //It is better to separate the validation logic from the success execution logic.
  @Override
  public boolean withdraw(int amount) {
    log("Transaction started: " + getClass().getSimpleName());

    try {
      //If tryLock() is called with time and TimeUnit as parameters, it must handle InterruptedException.
      if (!lock.tryLock(500, TimeUnit.MILLISECONDS)) {
        log("failed to acquire lock");
        return false;
      } else {
        log("succeeded to acquired lock");
      }
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    //Once a lock is acquired, you must always use `try-finally` to ensure that `unlock()` is called inside the `finally` block.
    //Because if an error occurs while executing the code, `unlock()` will not be called, causing other threads to remain in a waiting state indefinitely.
    try {
      log("[Validation Start] Withdrawal amount: " + amount + ", Current balance: " + balance);
      if (balance < amount) {
        log("[Validation Failed] Withdrawal amount: " + amount + ", Current balance: " + balance);
        return false;
      }

      log("[Validation Passed] Withdrawal amount: " + amount + ", Current balance: " + balance);

      // 🕐 Simulating time taken for withdrawal process
      sleep(1000);

      // ⚠️ Issue: This is not thread-safe! Balance update is not atomic
      balance = balance - amount;

      log("[Withdrawal Completed] Withdrawn amount: " + amount + ", Updated balance: " + balance);

    } finally {
      lock.unlock();

    }

    log("Transaction finished");

    return true;
  }

  @Override
  public int getBalance() {
    lock.lock();
    try {
      return balance;
    } finally {
      lock.unlock();
    }
  }
}
