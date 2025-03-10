package thread.bounded;

import static util.MyLogger.log;

public class ProducerTask implements Runnable {

  private BoundedQueue queue;
  private String request;

  public ProducerTask(BoundedQueue queue, String request) {
    this.queue = queue;
    this.request = request;
  }

  @Override
  public void run() {
    log("attempt to produce " + request + " -> " + queue);
    queue.put(request);
    log("complete to produce " + request + " -> " + queue);
  }
}