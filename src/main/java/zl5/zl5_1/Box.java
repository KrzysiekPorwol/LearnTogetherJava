package zl5.zl5_1;

public class Box<T> {
  private T content;

  public Box(T content) {
    this.content = content;
  }

  public Box() {}

  public T getContent() {
    return content;
  }

  public void setContent(T content) {
    this.content = content;
  }

  public boolean isEmpty() {
    if (content == null) {
      return true;
    }
    return false;
  }

}

//Poziom 1: Klasa generyczna
//
//Zadanie 1: Box<T>
//Napisz klasę Box<T>, czyli pudełko, które przechowuje jeden obiekt dowolnego typu.
//
//pole T content
//metody put(T item), T get() i boolean isEmpty()
//w main utwórz Box<String>, Box<Integer> i pudełko z obiektem Twojej własnej klasy
//
//Spróbuj też włożyć Integer do Box<String> i przeczytaj błąd kompilatora.
// Na tym polega sens generyków: błąd pojawia się już przy kompilacji, a nie dopiero w działającym programie.