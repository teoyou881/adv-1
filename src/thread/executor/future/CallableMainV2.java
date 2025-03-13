package thread.executor.future;

import java.util.Random;
import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class CallableMainV2 {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    ExecutorService es = Executors.newFixedThreadPool(1);
    log("call submit()");
    Future<Integer> future = es.submit(new MyCallable());
    log("return future immediately, future = " + future);
    // main thread is blocking? or waiting?
    log("future.get() start to call  -> main thread WAITING");
    // future.get()
    // ==> the calling thread will wait (block) until the operation completes and returns the result.
    Integer result = future.get();
    log("future.get() finish ->  main 스레드 RUNNABLE");
    log("result value = " + result);
    log("future 완료, future = " + future);
    es.close();
  }

  static class MyCallable implements Callable<Integer> {

    @Override
    public Integer call() {
      log("Callable start");
      sleep(2000);
      int value = new Random().nextInt(10);
      log("create value = " + value);
      log("Callable done");
      return value;
    }
  }
}