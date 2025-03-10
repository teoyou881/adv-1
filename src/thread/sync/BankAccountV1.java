package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV1 implements BankAccount {

  private int balance;

  // ❌ Incorrect: volatile does not guarantee atomicity for operations like balance updates
  //volatile private int balance;

  public BankAccountV1(int initialBalance) {
    this.balance = initialBalance;
  }

  //It is better to separate the validation logic from the success execution logic.
  @Override
  public boolean withdraw(int amount) {
    log("Transaction started: " + getClass().getSimpleName());

    // ✅ Step 1: Validate the withdrawal amount against the current balance
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
    log("Transaction finished");

    return true;
  }

  @Override
  public int getBalance() {
    return balance;
  }
}
