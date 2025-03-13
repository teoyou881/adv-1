package thread.executor.test.order;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class OldOrderService {

  public void order(String orderNo) {
    InventoryWork inventoryWork = new InventoryWork(orderNo);
    ShippingWork shippingWork = new ShippingWork(orderNo);
    AccountingWork accountingWork = new AccountingWork(orderNo);

    // Execute tasks
    Boolean inventoryResult = inventoryWork.call();
    Boolean shippingResult = shippingWork.call();
    Boolean accountingResult = accountingWork.call();

    // Check results
    if (inventoryResult && shippingResult && accountingResult) {
      log("All order processing tasks were completed successfully.");
    } else {
      log("Some tasks failed.");
    }
  }

  static class InventoryWork {

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

  static class ShippingWork {

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

  static class AccountingWork {

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
