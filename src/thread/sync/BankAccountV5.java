package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccountV5 implements BankAccount {

  private final Lock lock = new ReentrantLock();
  private int balance;

  public BankAccountV5(int initialBalance) {
    this.balance = initialBalance;
  }

  //It is better to separate the validation logic from the success execution logic.
  @Override
  public boolean withdraw(int amount) {
    log("Transaction started: " + getClass().getSimpleName());

    //The tryLock() method attempts to acquire the lock immediately, but if another thread already holds the lock, it fails and returns false.
    if (!lock.tryLock()) {
      log("failed to acquire lock");
      return false;
    } else {
      log("succeeded to acquired lock");
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
