package thread.bounded;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

import java.util.ArrayList;
import java.util.List;

public class BoundedMain {

  public static void main(String[] args) {
    // 1. Select the BoundedQueue implementation
//    BoundedQueue queue = new BoundedQueueV1(2);
//    BoundedQueue queue = new BoundedQueueV2(2);
//    BoundedQueue queue = new BoundedQueueV3(2);
//    BoundedQueue queue = new BoundedQueueV4(2);
//    BoundedQueue queue = new BoundedQueueV5(2);
    BoundedQueue queue = new BoundedQueueV6_1(2);

    // 2. Choose execution order for producer and consumer (Only select one!)
    //producerFirst(queue); // Run producer first
    consumerFirst(queue); // Run consumer first
  }

  private static void producerFirst(BoundedQueue queue) {
    log("== [Producer First Execution] Start, " + queue.getClass().getSimpleName() + " ==");
    List<Thread> threads = new ArrayList<>();
    startProducer(queue, threads);
    printAllState(queue, threads);
    startConsumer(queue, threads);
    printAllState(queue, threads);
    log("== [Producer First Execution] End, " + queue.getClass().getSimpleName() + " ==");
  }

  private static void consumerFirst(BoundedQueue queue) {
    log("== [Consumer First Execution] Start, " + queue.getClass().getSimpleName() + " ==");
    List<Thread> threads = new ArrayList<>();
    startConsumer(queue, threads);
    printAllState(queue, threads);
    startProducer(queue, threads);
    printAllState(queue, threads);
    log("== [Consumer First Execution] End, " + queue.getClass().getSimpleName() + " ==");
  }

  private static void startProducer(BoundedQueue queue, List<Thread> threads) {
    System.out.println();
    log("Starting Producer");
    for (int i = 1; i <= 3; i++) {
      Thread producer = new Thread(new ProducerTask(queue, "data" + i), "producer" + i);
      threads.add(producer);
      producer.start();
      sleep(100);
    }
  }

  private static void startConsumer(BoundedQueue queue, List<Thread> threads) {
    System.out.println();
    log("Starting Consumer");
    for (int i = 1; i <= 3; i++) {
      Thread consumer = new Thread(new ConsumerTask(queue), "consumer" + i);
      threads.add(consumer);
      consumer.start();
      sleep(100);
    }
  }

  private static void printAllState(BoundedQueue queue, List<Thread> threads) {
    System.out.println();
    log("Printing Current State, Queue Data: " + queue);
    for (Thread thread : threads) {
      log(thread.getName() + ": " + thread.getState());
    }
  }
}
