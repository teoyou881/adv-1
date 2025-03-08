package thread.start.test;

import static util.MyLogger.*;

import util.MyLogger;

public class StartTest4Main {

  public static void main(String[] args) {

    Thread threadA = new Thread(
        () -> {
          while (true) {
            try {
              Thread.sleep(1000);
            } catch (InterruptedException e) {
              throw new RuntimeException(e);
            }
            log("A");
          }
        }
        , "Thread-A");
    Thread threadB = new Thread(
        () -> {
          while (true) {
            try {
              Thread.sleep(500);
            } catch (InterruptedException e) {
              throw new RuntimeException(e);
            }
            log("B");
          }
        }
        , "Thread-B");

    threadA.start();
    threadB.start();

  }
}
