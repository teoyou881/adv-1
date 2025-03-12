package thread.executor.future;

import java.util.Random;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

//Cannot return a result – Unlike Callable, a Runnable task cannot return a value or throw checked exceptions.
//No built-in exception handling – If an exception occurs inside the run() method, it is not propagated outside unless explicitly handled.
public class RunnableMain {

  public static void main(String[] args) throws InterruptedException {
    MyRunnable task = new MyRunnable();
    Thread thread = new Thread(task, "Thread-1");
    thread.start();
    thread.join();
    int result = task.value;
    log("result value = " + result);
  }

  static class MyRunnable implements Runnable {

    int value;

    @Override
    public void run() {
      log("Runnable start");
      sleep(2000);
      value = new Random().nextInt(10);
      log("create value = " + value);
      log("Runnable done");
    }
  }
}