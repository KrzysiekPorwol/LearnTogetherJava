package zl5.zl5_2;

public class Pair<K, V> {

  private final K key;
  private final V value;

  private Pair(K key, V value) {
    this.key = key;
    this.value = value;
  }

  public K getKey() {
    return key;
  }

  public V getValue() {
    return value;
  }

  public Pair<V, K> swap() {
    return new Pair<>(value, key);
  }

  public static <K, V> Pair<K, V> createNewPair(K key, V value) {
    return new Pair<>(key, value);
  }

  @Override
  public String toString() {
    return "Pair{" +
          "key=" + key +
          ", value=" + value +
          '}';
  }
}



//Zadanie 2: Pair<K, V>
//Napisz klasę z dwoma parametrami typu, na przykład Pair<String, Integer> dla pary „nazwa i ilość”.
//
//gettery getKey() i getValue()
//metoda swap(), która zwraca nową parę Pair<V, K> z zamienionymi miejscami
//metoda toString()
