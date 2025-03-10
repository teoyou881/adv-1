package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BoundedQueueV2 implements BoundedQueue {

  private final Queue<String> queue = new ArrayDeque<>();
  private final int max;

  public BoundedQueueV2(int max) {
    this.max = max;
  }

  public synchronized void put(String data) {
    while (queue.size() == max) {
      log("queue is full, producer wait");
      sleep(1000);
    }
    queue.offer(data);
  }

  public synchronized String take() {
    while (queue.isEmpty()) {
      log("queue is empty, consumer wait");
      sleep(1000);
    }
    return queue.poll();
  }

  @Override
  public String toString() {
    return queue.toString();
  }
}