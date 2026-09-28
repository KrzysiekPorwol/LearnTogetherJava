package pd3;

import pd3.logic.MenuLogic;

import java.util.Scanner;

public class MathCalculator {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    MenuLogic menuLogic = new MenuLogic(scanner);

    menuLogic.navigateMenu();


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