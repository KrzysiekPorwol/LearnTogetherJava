package pd3.logic;

public class MathLibrary {

  public static long factorialIterator(int n) {

    long result = n;

    for (int i = n; i > 1; i--) {
      result = result * (i - 1);
    }
    return result;
  }

  public static long factorialRecursion(int n) {

    if (n <= 1) {
      return 1;
    } else {
      return n * factorialRecursion(n - 1);
    }
  }

  public static boolean isPrime(int n) {
    if (n < 2) {
      return false;
    }
    double rootNumber = Math.sqrt(n);
    int roundedNumber = (int) Math.floor(rootNumber);
    for (int i = 2; i <= roundedNumber; i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }

  public static int gcd(int a, int b) {

    if (b == 0) {
      return a;
    } else {
      int remainderDivision = a % b;
      return gcd(b, remainderDivision);
    }
  }

  public static double power(long a, int n) {
    long result = 1;
    while (n > 0) {
      if (n % 2 == 1) {
        result *= a;
      }
      a *= a;
      n /= 2;
    }
    return result;
  }


  public static double compareSpeedFactorial() {

    int n = 20;
    int repetitions = 20;

    for (int i = 0; i < 10_000; i++) {
      factorialIterator(n);
      factorialRecursion(n);
    }

    long startIterator = System.nanoTime();
    for (int i = 0; i < repetitions; i++) {
      factorialIterator(n);
    }
    long iteratorInNanoSeconds = System.nanoTime() - startIterator;

    long startRecursion = System.nanoTime();
    for (int i = 0; i < repetitions; i++) {
      factorialRecursion(n);
    }
    long recursionInNanoSeconds = System.nanoTime() - startRecursion;


    return (double) (recursionInNanoSeconds - iteratorInNanoSeconds) / repetitions;
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
