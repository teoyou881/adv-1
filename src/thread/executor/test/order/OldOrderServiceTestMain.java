package thread.executor.test.order;

import java.util.concurrent.ExecutionException;

public class OldOrderServiceTestMain {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    String orderNo = "Order#1234"; // Example order number
    OldOrderService orderService = new OldOrderService();
    MyOrderService myOrderService = new MyOrderService();
    NewOrderService newOrderService = new NewOrderService();
    orderService.order(orderNo);
    myOrderService.order(orderNo);
    newOrderService.order(orderNo);


  }
}
