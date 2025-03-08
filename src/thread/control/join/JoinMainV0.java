package thread.control.join;

import static util.MyLogger.*;

import util.MyLogger;
import util.ThreadUtils;

public class JoinMainV0 {

  public static void main(String[] args) {
    log("start");
    new Thread(new Job(), "thread-1").start();
    new Thread(new Job(), "thread-2").start();

    log("end");
  }

  static class Job implements Runnable {


    @Override
    public void run() {
      log("job start");
      ThreadUtils.sleep(1500);
      log("job end");
    }
  }

}
