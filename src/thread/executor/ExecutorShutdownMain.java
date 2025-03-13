package thread.executor;

import static util.MyLogger.log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorShutdownMain {

  public static void main(String[] args) {

    ExecutorService es = Executors.newFixedThreadPool(2);

    es.execute(new RunnableTask("taskA"));
    es.execute(new RunnableTask("taskB"));
    es.execute(new RunnableTask("taskC"));
    es.execute(new RunnableTask("longTask", 100_000)); // 100s

    ExecutorUtils.printState(es);
    log("--- shut down start ---");
    shutdownAndAwaitTermination(es);
    log("--- shut down done---");
    ExecutorUtils.printState(es);
  }

  private static void shutdownAndAwaitTermination(ExecutorService es) {

    //shutdown() does not accept new tasks.
    //It processes tasks that are already running or waiting in the queue,
    //and then shuts down the pool's threads.
    es.shutdown();

    try {
      //awaitTermination() waits for the thread pool to terminate within the specified time.
      //If all tasks are completed within the time limit, it returns true; otherwise, it returns false.
      if (!es.awaitTermination(10, TimeUnit.SECONDS)) {
        //What if a normal shutdown fails and takes too long?
        log(" fails to shut down properly => attempt to do a forced termination ");
        es.shutdownNow();
        //Wait for the task to be canceled.
        if (!es.awaitTermination(10, TimeUnit.SECONDS)) {
          log("The service did not shut down properly. ");
        }
      }
    } catch (InterruptedException e) {
      // The current thread waiting with awaitTermination() may be interrupted.
      es.shutdownNow();
    }
  }

}
