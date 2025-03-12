package thread.collection.java;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapMain {

  public static void main(String[] args) {

    ConcurrentHashMap<Integer, String> map1 = new ConcurrentHashMap<>();
    map1.put(1, "one");
    map1.put(2, "two");
    map1.put(3, "three");

    System.out.println("map1 = " + map1);

    ConcurrentHashMap<Integer, String> map2 = new ConcurrentHashMap<>();
    map2.put(2, "two");
    map2.put(3, "three");
    map2.put(1, "one");

    System.out.println("map2 = " + map2);
  }

}
