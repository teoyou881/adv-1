package thread.cas.increment;

import java.util.concurrent.atomic.AtomicInteger;

public class MyAtomicInteger implements IncrementInteger {

  //Initializes an AtomicInteger with a specified value. If omitted, it defaults to 0.
  AtomicInteger atomicInteger = new AtomicInteger(0);

  @Override
  public void increment() {
    //Atomically increments the value by one and returns the updated value.
    atomicInteger.incrementAndGet();
  }

  @Override
  public int get() {
    return atomicInteger.get();
  }
}
