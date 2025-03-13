package thread.executor.future;

import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class FutureExceptionMain {

  public static void main(String[] args) {
    ExecutorService es = Executors.newFixedThreadPool(1);
    log("Submitting task");
    Future<Integer> future = es.submit(new ExCallable());
    sleep(1000); // Wait for a moment

    try {
      log("Attempting to call future.get(), future state: " + future.state());
      Integer result = future.get();
      log("Result value = " + result);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    } catch (ExecutionException e) {
      //e.printStackTrace();
      log("Exception occurred: " + e);
      Throwable cause = e.getCause(); // Original exception
      log("Cause of exception: " + cause);
    }

    es.close();
  }

  static class ExCallable implements Callable<Integer> {

    @Override
    public Integer call() {
      log("Executing Callable, an exception will be thrown");
      throw new IllegalStateException("ex!");
    }
  }
}
