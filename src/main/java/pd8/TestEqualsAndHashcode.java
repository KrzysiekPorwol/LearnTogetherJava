package pd8;

import pd8.model.UserAccountV1;
import pd8.model.UserAccountV2;
import pd8.model.UserAccountV3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TestEqualsAndHashcode {
  public static void main(String[] args) {

    testV1();
    testV2();

  }


  public static void testV1() {
    UserAccountV1 userAccountV1_1 = new UserAccountV1(12345L, "example1@gmail.com", "user1");
    UserAccountV1 userAccountV1_2 = new UserAccountV1(12345L, "example1@gmail.com", "user1");

    Set<UserAccountV1> userAccountV1s = new HashSet<>();
    userAccountV1s.add(userAccountV1_1);
    userAccountV1s.add(userAccountV1_2);

    // Wynik: rozmiar seta = 2, equals = false.
    // UserAccountV1 nie nadpisuje equals() ani hashCode(), więc używa wersji z klasy Object.
    // equals() z Object sprawdza tylko, czy to ten sam obiekt w pamięci – a to są dwa
    // osobne obiekty (dwa razy "new"), więc false, mimo identycznych danych.
    // hashCode() z Object też jest inny dla każdego obiektu, więc HashSet umieszcza je
    // w różnych "szufladkach" i traktuje jako dwa różne elementy.

    System.out.println("Test V1");
    System.out.println("Rozmiar seta V1: "
        + userAccountV1s.size()
        + " | Porównanie dwóch takich samych userAccountV1: "
        + userAccountV1_1.equals(userAccountV1_2));
  }

  public static void testV2() {
    UserAccountV2 userAccountV2_1 = new UserAccountV2(12345L, "example1@gmail.com", "user1");
    UserAccountV2 userAccountV2_2 = new UserAccountV2(12345L, "example1@gmail.com", "user1");

    Set<UserAccountV2> userAccountV2s = new HashSet<>();
    userAccountV2s.add(userAccountV2_1);
    userAccountV2s.add(userAccountV2_2);

    // Wynik: rozmiar seta = 2, contains(new UserAccountV2(...)) = false.
    // UserAccountV2 nadpisuje equals() (porównuje pola), ale hashCode() nadal pochodzi
    // z klasy Object, więc każdy obiekt dostaje inną wartość, nawet przy identycznych danych.
    // HashSet najpierw używa hashCode(), żeby wybrać "szufladkę", a dopiero w niej porównuje
    // obiekty przez equals(). Dwa logicznie identyczne obiekty trafiają do różnych szufladek,
    // więc equals() nie zostaje nawet wywołane – set dodaje oba (rozmiar 2) i nie znajduje
    // nowego obiektu w contains().
    // Wniosek: samo equals() nie wystarczy. Obiekty równe według equals() muszą mieć
    // równy hashCode() – to kontrakt tych dwóch metod w Javie.

    System.out.println("Test V2");
    System.out.println("Rozmiar seta V2: "
        + userAccountV2s.size()
        + " | Porównanie contains nowego obiektu V2: "
        + userAccountV2s.contains(new UserAccountV2(12345L, "example1@gmail.com", "user1")));
  }

  public static void testV3() {
    UserAccountV3 userAccountV3_1 = new UserAccountV3(12345L, "example1@gmail.com", "user1");
    UserAccountV3 userAccountV3_2 = new UserAccountV3(12345L, "example1@gmail.com", "user1");

    Set<UserAccountV3> userAccountV3Set = new HashSet<>();
    userAccountV3Set.add(userAccountV3_1);
    userAccountV3Set.add(userAccountV3_2);

    Map<Long, UserAccountV3> userAccountV3Map = new HashMap();
    userAccountV3Map.put(userAccountV3_1.getId(), userAccountV3_1);
    userAccountV3Map.put(userAccountV3_2.getId(), userAccountV3_2);


  }
}


//PD-8
//Wykaż, że poprawne działanie HashSet i HashMap zależy od poprawnej implementacji equals() i hashCode()
//
//Przygotuj klasę UserAccount z polami:
//Long id
//String email
//String displayName`
//
//Następnie wykonaj 4 eksperymenty:
//
//Eksperyment A (UserAccountV1)
//Utwórz dwa obiekty z tymi samymi danymi, ale bez nadpisywania equals() i hashCode().
//Dodaj oba do HashSet i sprawdź:
//rozmiar seta
//wynik equals()
//Dodaj komentarz wyjaśniający, dlaczego kolekcja zachowała się właśnie w taki sposób
//
//Eksperyment B (UserAccountV2)
//Nadpisz tylko equals(), ale nie nadpisuj hashCode().
//Ponownie dodaj dwa logicznie identyczne obiekty do HashSet i sprawdź:
//rozmiar seta
//wynik contains(new UserAccount(...))
//Dodaj komentarz wyjaśniający, dlaczego samo equals() nie wystarczyło
//
//Eksperyment C (UserAccountV3)
//Nadpisz poprawnie zarówno equals(), jak i hashCode().
//Sprawdź działanie:
//HashSet
//    HashMap
//contains(...)
//get(...)
//Dodaj komentarz wyjaśniający, dlaczego teraz kolekcje działają zgodnie z intuicją