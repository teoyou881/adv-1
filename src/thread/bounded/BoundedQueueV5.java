package thread.bounded;

import static util.MyLogger.log;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedQueueV5 implements BoundedQueue {

  private final Lock lock = new ReentrantLock();
  // wait place
  private final Condition producerCond = lock.newCondition();
  private final Condition consumerCond = lock.newCondition();

  private final Queue<String> queue = new ArrayDeque<>();
  private final int max;

  public BoundedQueueV5(int max) {
    this.max = max;
  }

  public void put(String data) {
    lock.lock();
    try {
      while (queue.size() == max) {

        log("queue is full, producer wait");

        try {
          producerCond.await();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }

        log("producer runnable");
      }
      queue.offer(data);
      consumerCond.signal();
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
          consumerCond.await();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        log("consumer runnable");

      }
      String data = queue.poll();
      producerCond.signal();
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