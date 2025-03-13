package thread.executor.test.order;

import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class NewOrderService {

  private final ExecutorService es = Executors.newFixedThreadPool(10);

  public void order(String orderNo) throws ExecutionException, InterruptedException {
    InventoryWork inventoryWork = new InventoryWork(orderNo);
    ShippingWork shippingWork = new ShippingWork(orderNo);
    AccountingWork accountingWork = new AccountingWork(orderNo);

    try {
      // Submit tasks to ExecutorService
      Future<Boolean> inventoryFuture = es.submit(inventoryWork);
      Future<Boolean> shippingFuture = es.submit(shippingWork);
      Future<Boolean> accountingFuture = es.submit(accountingWork);

      // Wait for task completion
      Boolean inventoryResult = inventoryFuture.get();
      Boolean shippingResult = shippingFuture.get();
      Boolean accountingResult = accountingFuture.get();

      // Check results
      if (inventoryResult && shippingResult && accountingResult) {
        log("All order processing tasks were completed successfully.");
      } else {
        log("Some tasks failed.");
      }
    } finally {
      es.close();
    }
  }

  static class InventoryWork implements Callable<Boolean> {

    private final String orderNo;

    public InventoryWork(String orderNo) {
      this.orderNo = orderNo;
    }

    @Override
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

    @Override
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

    @Override
    public Boolean call() {
      log("Updating accounting system: " + orderNo);
      sleep(1000);
      return true;
    }
  }
}
