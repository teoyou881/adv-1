package thread.start;

public class DaemonThreadMain {

  public static void main(String[] args) {
    System.out.println(Thread.currentThread().getName() + ": main() start");
    DaemonThread daemonThread = new DaemonThread();
//    daemonThread.setDaemon(true); // set deamon thread
    daemonThread.start();
    System.out.println(Thread.currentThread().getName() + ": main() end");
  }

  static class DaemonThread extends Thread {
    // you can set daemon here as well.
//    public DaemonThread() {
//      setDaemon(true); // ✅ Set daemon before start()
//    }

    @Override
    public void run() {
      //in run, you can't set daemon...
      //setDaemon(true);
      System.out.println(Thread.currentThread().getName() + ": run() start");
      try {
        Thread.sleep(10000); // 10 seconds
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      System.out.println(Thread.currentThread().getName() + ": run() end");
    }
  }
}