package zl5.zl5_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericUtils {


  public static void main(String[] args) {

    String[] arrayString = {"a", "b", "c", "d", "e"};

    Integer[] integers = {0, 1, 25, 2355, 6433, 23556, 233, 356};

    List<String> stringList = new ArrayList<>();

    stringList.add("abc");
    stringList.add("zcd");
    stringList.add("edf");

    printAll(arrayString);

    printAll(integers);


    System.out.println(getLast(stringList));



  }

  public static <T> void printAll(T[] array) {
    for (T t : array) {
      System.out.println(t);
    }
  }

  public static <T> T getLast(List<T> list) {
    return list.get(list.size() - 1);
  }

  public static  <T> int countOccurrences(T[] array, T element) {
    int count = 0;
    for (T t : array) {
      if (element.equals(t)) {
        count++;
      }
    }
    return count;
  }

}


//Zadanie 3: narzędzia do tablic i list
//W klasie GenericUtils napisz statyczne metody:
//
//static <T> void printAll(T[] array)
//static <T> T getLast(List<T> list)
//static <T> int countOccurrences(T[] array, T element), która liczy wystąpienia elementu (porównuj przez equals)
