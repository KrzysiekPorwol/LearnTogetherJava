package pd3.model;

import java.util.Optional;

public enum Menu {
  FACTORIAL_ITERATOR(1, "Silnia - metoda iteracyjna"),
  FACTORIAL_RECURSION(2, "Silnia - metoda rekurencyjna"),
  ISPRIME(3, "Optymalizacja do sqrt(n)"),
  SIEVEOFERATOSTHENES(4, "Sito Eratostenesa"),
  GCD(5, "Algorytm Euklidesa - metoda rekurencyjna"),
  POWER(6, "Szybkie potęgowanie"),
  BENCHMARK(7, "Porównanie szybkości rozwiązania rekurencyjnego i iteracyjnego dla 20!"),
  QUIT_PROGRAM(0, "Wyjście z aplikacji");

  private final int number;
  private final String description;

  Menu(int number, String description) {
    this.number = number;
    this.description = description;
  }

  public int getNumber() {
    return number;
  }

  public String getDescription() {
    return description;
  }

  public static Optional<Menu> findByNumber(int number) {
    for (Menu m : Menu.values()) {
      if (number == m.getNumber()) {
        return Optional.of(m);
      }
    }
    return Optional.empty();
  }
}


//Utwórz klasę MathLibrary z metodami:
//static long factorial(int n) — OBIE wersje: iteracyjna i rekurencyjna
//static boolean isPrime(int n) — optymalizacja do sqrt(n)
//static int[] sieveOfEratosthenes(int limit) — sito Eratostenesa
//static int gcd(int a, int b) — algorytm Euklidesa (rekurencja)
//static double power(double base, int exp) — szybkie potęgowanie
//Interaktywne menu: użytkownik wybiera funkcję, podaje parametry, widzi wynik
//Benchmark: porównaj czas factorial iteracyjny vs rekurencyjny dla n=20