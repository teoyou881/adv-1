package thread.bounded;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

import java.util.ArrayDeque;
import java.util.Queue;

public class BoundedQueueV3 implements BoundedQueue {

  private final Queue<String> queue = new ArrayDeque<>();
  private final int max;

  public BoundedQueueV3(int max) {
    this.max = max;
  }

  public synchronized void put(String data) {
    while (queue.size() == max) {
      log("queue is full, producer wait");
      try {
        // runnable ==> waiting. returning lock.
        wait();
        log("producer runnable");
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    queue.offer(data);
    log("producer made data. call notify()");
    // make one of threads runnable.
    notify();
  }

  public synchronized String take() {
    while (queue.isEmpty()) {
      log("queue is empty, consumer wait");
      try {
        wait();
        log("consumer runnable");
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    String data = queue.poll();
    log("consumer get data. call notify()");
    notify();
    return data;
  }


  @Override
  public String toString() {
    return queue.toString();
  }
}