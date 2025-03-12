package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static thread.executor.ExecutorUtils.*;
import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ExecutorBasicMain {

  public static void main(String[] args) throws InterruptedException {
    ExecutorService es = new ThreadPoolExecutor(2, 2, 0,
        TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    log("== initial status ==");
    printState(es);
    es.execute(new RunnableTask("taskA"));
    es.execute(new RunnableTask("taskB"));
    es.execute(new RunnableTask("taskC"));
    es.execute(new RunnableTask("taskD"));
    log("== working on task==");
    printState(es);
    sleep(3000);
    log("== finished task==");
    printState(es);
    es.close();
    log("== shutdown ==");
    printState(es);
  }
}