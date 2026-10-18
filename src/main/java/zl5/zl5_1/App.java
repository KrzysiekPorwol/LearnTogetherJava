package zl5.zl5_1;

import zl5.model.Product;

public class App {

  public static void main(String[] args) {

    Box<String> box = new Box<>("ale");

    Box<Integer> integerBox = new Box<>(50);

//    Box<String> incorrectBox = new Box<>(50);

    Box<Product> productBox = new Box<>(new Product("krzeslo"));

    Box<String> emptyBox = new Box<>();

    if (emptyBox.isEmpty()) {
      System.out.println("To pudełko jest puste");
    } else {
      System.out.println("Nie jest puste");
    };

    if (productBox.isEmpty()) {
      System.out.println("To pudełko jest puste");
    } else {
      System.out.println("Nie jest puste");
    };



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