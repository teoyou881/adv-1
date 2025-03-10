package thread.bounded;

import static util.MyLogger.log;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedQueueV4 implements BoundedQueue {

  private final Lock lock = new ReentrantLock();
  // wait place
  private final Condition condition = lock.newCondition();

  private final Queue<String> queue = new ArrayDeque<>();
  private final int max;

  public BoundedQueueV4(int max) {
    this.max = max;
  }

  public void put(String data) {
    lock.lock();
    try {
      while (queue.size() == max) {

        log("queue is full, producer wait");

        try {
          condition.await();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }

        log("producer runnable");
      }
      queue.offer(data);
      condition.signal();
      log("producer made data. call condition.signal()");
    } finally {
      lock.unlock();
    }
  }

  public String take() {
    lock.lock();
    try {
      while (queue.isEmpty()) {
        log("queue is empty, consumer wait");
        try {
          condition.await();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        log("consumer runnable");

      }
      String data = queue.poll();
      condition.signal();
      log("consumer get data. condition.signal()");
      return data;
    } finally {
      lock.unlock();
    }


  }


  @Override
  public String toString() {
    return queue.toString();
  }
}