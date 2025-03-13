package thread.executor.test.order;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyOrderService {

  ExecutorService ex = Executors.newFixedThreadPool(10);

  public void order(String orderNo) {
    InventoryWork inventoryWork = new InventoryWork(orderNo);
    ShippingWork shippingWork = new ShippingWork(orderNo);
    AccountingWork accountingWork = new AccountingWork(orderNo);

    List<Callable<Boolean>> list = List.of(inventoryWork, shippingWork, accountingWork);

    List<Future<Boolean>> futures;
    try {
      futures = ex.invokeAll(list);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    boolean allSuccess = false;

    for (Future<Boolean> booleanCallable : futures) {
      try {
        if (!booleanCallable.get()) {
          allSuccess = false;
        } else {
          allSuccess = true;
        }
      } catch (InterruptedException | ExecutionException e) {
        throw new RuntimeException(e);
      }

    }
    if (allSuccess) {
      log("All order processing tasks were completed successfully.");
    } else {
      log("Some tasks failed.");
    }
    ex.close();

  }

  static class InventoryWork implements Callable<Boolean> {

    private final String orderNo;

    public InventoryWork(String orderNo) {
      this.orderNo = orderNo;
    }

    public Boolean call() {
      log("Updating inventory: " + orderNo);
      sleep(1000);
      return true;
    }
  }

  static class ShippingWork implements Callable<Boolean> {

    private final String orderNo;

    public ShippingWork(String orderNo) {
      this.orderNo = orderNo;
    }

    public Boolean call() {
      log("Notifying shipping system: " + orderNo);
      sleep(1000);
      return true;
    }
  }

  static class AccountingWork implements Callable<Boolean> {

    private final String orderNo;

    public AccountingWork(String orderNo) {
      this.orderNo = orderNo;
    }

    public Boolean call() {
      log("Updating accounting system: " + orderNo);
      sleep(1000);
      return true;
    }
  }
}
