package thread.start;

public class BadThreadMain {

  public static void main(String[] args) {
    System.out.println(Thread.currentThread().getName() + ": main() start");
    HelloThread helloThread = new HelloThread();
    System.out.println(Thread.currentThread().getName() + ": before calling start() ");
    helloThread.run(); // run() 직접 실행
    System.out.println(Thread.currentThread().getName() + ": after calling start() ");
    System.out.println(Thread.currentThread().getName() + ": main() end");
  }
}