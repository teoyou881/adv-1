package thread.collection.java;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetMain {

  public static void main(String[] args) {
    Set<Integer> copySet = new CopyOnWriteArraySet<>();
    copySet.add(2);
    copySet.add(1);
    copySet.add(3);

    System.out.println("copySet = " + copySet);

    Set<Integer> skipSet = new CopyOnWriteArraySet<>();
    skipSet.add(1);
    skipSet.add(3);
    skipSet.add(2);

    System.out.println("skipSet = " + skipSet);
  }

}
