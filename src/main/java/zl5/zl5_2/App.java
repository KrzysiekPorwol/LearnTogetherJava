package zl5.zl5_2;

public class App {
  public static void main(String[] args) {

    Pair<String, Integer> pair = Pair.createNewPair("haslo", 100);

    System.out.println(pair.getKey() + " | " + pair.getValue());

    Pair<Integer, String> swapedPair = pair.swap();

    System.out.println(swapedPair.getKey() + " | " + swapedPair.getValue());




  }
}

//Zadanie 2: Pair<K, V>
//Napisz klasę z dwoma parametrami typu, na przykład Pair<String, Integer> dla pary „nazwa i ilość”.
//
//gettery getKey() i getValue()
//metoda swap(), która zwraca nową parę Pair<V, K> z zamienionymi miejscami
//metoda toString()