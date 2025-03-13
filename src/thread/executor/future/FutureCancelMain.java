package thread.executor.future;

import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class FutureCancelMain {

  //  private static boolean mayInterruptIfRunning = true;
  private static boolean mayInterruptIfRunning = false;

  public static void main(String[] args) {
    ExecutorService es = Executors.newFixedThreadPool(1);
    Future<String> future = es.submit(new MyTask());
    log("Future state: " + future.state());

    // Attempt to cancel after a few seconds
    sleep(3000);

    // Call cancel
    log("Calling future.cancel(" + mayInterruptIfRunning + ")");
    boolean cancelResult1 = future.cancel(mayInterruptIfRunning);
    log("Future state: " + future.state());
    log("cancel(" + mayInterruptIfRunning + ") result: " + cancelResult1);

    // Get result
    try {
      log("Future result: " + future.get());
      // Runtime exception
    } catch (CancellationException e) {
      log("Future has already been canceled.");
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }

    // Shutdown the Executor
    es.close();
  }

  static class MyTask implements Callable<String> {

    @Override
    public String call() {
      try {
        for (int i = 0; i < 10; i++) {
          log("Processing: " + i);
          Thread.sleep(1000); // Sleep for 1 second
        }
      } catch (InterruptedException e) {
        log("InterruptedException occurred");
        return "Interrupted";
      }
      return "Completed";
    }
  }
}
