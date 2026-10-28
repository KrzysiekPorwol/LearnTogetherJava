package pd8;

import pd8.model.UserAccountV1;
import pd8.model.UserAccountV2;
import pd8.model.UserAccountV3;
import pd8.model.UserAccountV4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class TestEqualsAndHashcode {
  public static void main(String[] args) {

    testV1();
    testV2();
    testV3();
    testV4();

  }

  /**
   * Eksperyment A – brak nadpisanych {@code equals()} i {@code hashCode()}.
   *
   * <p><b>Wynik:</b> rozmiar seta = 2, {@code equals()} = {@code false}.
   *
   * <p>{@link UserAccountV1} nie nadpisuje {@code equals()} ani {@code hashCode()},
   * więc używa wersji z klasy {@link Object}:
   * <ul>
   *   <li>{@code equals()} z {@code Object} sprawdza tylko, czy to ten sam obiekt w pamięci –
   *       a to są dwa osobne obiekty (dwa razy {@code new}), więc {@code false},
   *       mimo identycznych danych,</li>
   *   <li>{@code hashCode()} z {@code Object} też jest inny dla każdego obiektu, więc
   *       {@code HashSet} umieszcza je w różnych "szufladkach" i traktuje jako dwa różne
   *       elementy.</li>
   * </ul>
   */
  public static void testV1() {
    UserAccountV1 userAccountV1_1 = new UserAccountV1(12345L, "example1@gmail.com", "user1");
    UserAccountV1 userAccountV1_2 = new UserAccountV1(12345L, "example1@gmail.com", "user1");

    Set<UserAccountV1> userAccountV1s = new HashSet<>();
    userAccountV1s.add(userAccountV1_1);
    userAccountV1s.add(userAccountV1_2);

    System.out.println("\nTest V1");
    System.out.println("Rozmiar seta V1: "
        + userAccountV1s.size()
        + " | Porównanie dwóch takich samych userAccountV1: "
        + userAccountV1_1.equals(userAccountV1_2));
  }

  /**
   * Eksperyment B – nadpisane tylko {@code equals()}, bez {@code hashCode()}.
   *
   * <p><b>Wynik:</b> rozmiar seta = 2, {@code contains(new UserAccountV2(...))} = {@code false}.
   *
   * <p>{@link UserAccountV2} nadpisuje {@code equals()} (porównuje pola), ale
   * {@code hashCode()} nadal pochodzi z klasy {@link Object}, więc każdy obiekt dostaje
   * inną wartość, nawet przy identycznych danych.
   *
   * <p>{@code HashSet} najpierw używa {@code hashCode()}, żeby wybrać "szufladkę", a dopiero
   * w niej porównuje obiekty przez {@code equals()}. Dwa logicznie identyczne obiekty trafiają
   * do różnych szufladek, więc {@code equals()} nie zostaje nawet wywołane – set dodaje oba
   * (rozmiar 2) i nie znajduje nowego obiektu w {@code contains()}.
   *
   * <p>Co ciekawe, pętla po secie znajduje użytkownika dwa razy. Dzieje się tak dlatego,
   * że nadpisaliśmy {@code equals()}, a pętla nie porównuje przy pomocy {@code hashCode()},
   * tylko wykorzystuje samo {@code equals()} do porównania danych.
   *
   */
  public static void testV2() {
    UserAccountV2 userAccountV2_1 = new UserAccountV2(12345L, "example1@gmail.com", "user1");
    UserAccountV2 userAccountV2_2 = new UserAccountV2(12345L, "example1@gmail.com", "user1");

    Set<UserAccountV2> userAccountV2s = new HashSet<>();
    userAccountV2s.add(userAccountV2_1);
    userAccountV2s.add(userAccountV2_2);

    System.out.println("\nTest V2");
    System.out.println("Rozmiar seta V2: "
        + userAccountV2s.size()
        + " | Porównanie contains z nowym obiektem V2: "
        + userAccountV2s.contains(new UserAccountV2(12345L, "example1@gmail.com", "user1")));

    System.out.println("Próba szukania tego samego (nowego) użytkownika przy pomocy pętli");
    for (UserAccountV2 user : userAccountV2s) {
      if (user.equals(new UserAccountV2(12345L, "example1@gmail.com", "user1"))) {
        System.out.println("Znaleziony: " + user.getDisplayName());
      }
    }
  }

  /**
   * Eksperyment C – poprawnie nadpisane {@code equals()} i {@code hashCode()}.
   *
   * <p><b>Wynik:</b> rozmiar seta = 1, rozmiar mapy = 1, {@code contains()} /
   * {@code containsKey()} = {@code true}, {@code get()} zwraca usera.
   *
   * <p>{@link UserAccountV3} nadpisuje {@code equals()} i {@code hashCode()} na podstawie
   * tego samego pola ({@code id}):
   * <ul>
   *   <li>dwa obiekty z tym samym {@code id} mają ten sam {@code hashCode()}, bo
   *       {@code Objects.hash(id)} liczy go z wartości pola, a nie z adresu w pamięci –
   *       trafiają więc do tej samej "szufladki",</li>
   *   <li>w tej szufladce {@code equals()} zwraca {@code true}, więc set uznaje drugi obiekt
   *       za duplikat, a mapa nadpisuje wartość pod istniejącym kluczem zamiast dodawać
   *       nowy wpis,</li>
   *   <li>{@code contains()} / {@code containsKey()} / {@code get()} z NOWYM obiektem działają
   *       tak samo: hash prowadzi do właściwej szufladki, {@code equals()} znajduje w niej
   *       pasujący element.</li>
   * </ul>
   *
   * <p>Do {@code equals()} / {@code hashCode()} wybrane jest tylko {@code id}, bo to
   * identyfikator użytkownika – {@code email} czy {@code displayName} mogą się zmienić,
   * a to nadal ta sama osoba (stąd {@code containsKey()} z innym emailem i nazwą też
   * zwraca {@code true}).
   *
   */
  public static void testV3() {
    UserAccountV3 userAccountV3_1 = new UserAccountV3(12345L, "example1@gmail.com", "user1");
    UserAccountV3 userAccountV3_2 = new UserAccountV3(12345L, "example1@gmail.com", "user1");

    Set<UserAccountV3> userAccountV3Set = new HashSet<>();
    userAccountV3Set.add(userAccountV3_1);
    userAccountV3Set.add(userAccountV3_2);

    Map<UserAccountV3, UserAccountV3> userAccountV3Map = new HashMap<>();
    userAccountV3Map.put(userAccountV3_1, userAccountV3_1);
    userAccountV3Map.put(userAccountV3_2, userAccountV3_2);

    System.out.println("\nTest V3");
    System.out.println("Rozmiar seta V3: "
        + userAccountV3Set.size()
        + " | Porównanie contains (set) z nowym obiektem V3 z takimi samymi danymi: "
        + userAccountV3Set.contains(new UserAccountV3(12345L, "example1@gmail.com", "user1")));

    System.out.println("Rozmiar mapy V3: "
        + userAccountV3Map.size()
        + " | Porównanie containsKey (map) z nowym obiektem V3 z takim samym kluczem: "
        + userAccountV3Map.containsKey(new UserAccountV3(12345L, "cosinnego@gmail.com", "userInny")));

    UserAccountV3 getUser = userAccountV3Map.get(new UserAccountV3(12345L, "example1@gmail.com", "user1"));
    System.out.println("Pobranie usera poprzez get: " + getUser.getDisplayName() + " |  " + getUser.getEmail() + " |  " + getUser.getId());
  }

  /**
   * Eksperyment D – mutowalne pole {@code status} uwzględnione w {@code equals()}
   * i {@code hashCode()}.
   *
   * <p><b>Wynik:</b> {@code contains()} = {@code false}, a po {@code remove()} rozmiar seta
   * nadal = 1.
   *
   * <p>Co poszło nie tak:
   * <ol>
   *   <li>przy {@code add()} set policzył {@code hashCode()} dla {@code (12345, "online")}
   *       i odłożył obiekt do szufladki X – ten hash set zapamiętuje u siebie i już go
   *       nie przelicza,</li>
   *   <li>{@code setStatus("offline")} zmienia stan obiektu, więc {@code hashCode()} zwraca
   *       teraz inną wartość, wskazującą na szufladkę Y,</li>
   *   <li>{@code contains()} i {@code remove()} liczą hash od nowa i szukają w Y, która jest
   *       pusta – do {@code equals()} w ogóle nie dochodzi. Obiekt nadal leży w X, ale nie da
   *       się go już ani znaleźć, ani usunąć przez API seta.</li>
   * </ol>
   *
   * <p>Skutki: iteracja po secie dalej go pokaże ("duch" w kolekcji), ponowne {@code add()}
   * tej samej instancji się uda (rozmiar 2 – ten sam obiekt dwa razy w secie), a w
   * {@code HashMap} tak samo "gubi się" wartość pod zmienionym kluczem ({@code get()} zwraca
   * {@code null}, wyciek pamięci).
   *
   * <p><b>Wniosek:</b> do {@code equals()} / {@code hashCode()} używamy pól niemutowalnych,
   * określających tożsamość obiektu (tu {@code id}, które jest {@code final}). {@code status}
   * opisuje stan, a nie to, kim jest obiekt.
   */
  public static void testV4() {
    UserAccountV4 userAccountV4_1 = new UserAccountV4(12345L, "example1@gmail.com", "user1", "online");

    Set<UserAccountV4> userAccountV4Set = new HashSet<>();
    userAccountV4Set.add(userAccountV4_1);

    userAccountV4_1.setStatus("offline");

    System.out.println("\nTest V4");
    System.out.println("Rozmiar seta V4: "
        + userAccountV4Set.size()
        + " | Porównanie contains (set) z obiektem który już jest w zbiorze z (po zmianie statusu): "
        + userAccountV4Set.contains(userAccountV4_1));

    userAccountV4Set.remove(userAccountV4_1);
    System.out.println("Rozmiar seta V4 po zrobieniu remove: "
        + userAccountV4Set.size());

  }
}