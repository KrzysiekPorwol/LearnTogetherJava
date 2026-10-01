package pd3.logic;

public class MathLibrary {

  /** Calculates the factorial of n using a loop. */
  public static long factorialIterator(int n) {

    long result = n;

    for (int i = n; i > 1; i--) {
      result = result * (i - 1);
    }
    return result;
  }

  /** Calculates the factorial of n using recursion. */
  public static long factorialRecursion(int n) {

    if (n <= 1) {
      return 1;
    } else {
      return n * factorialRecursion(n - 1);
    }
  }

  /** Checks whether n is a prime number by testing divisors up to sqrt(n). */
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

  /** Calculates the greatest common divisor of a and b using the Euclidean algorithm. */
  public static int gcd(int a, int b) {

    if (b == 0) {
      return a;
    } else {
      int remainderDivision = a % b;
      return gcd(b, remainderDivision);
    }
  }

  /** Raises base to the power of exp using fast exponentiation. */
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


  /** Compares the running time of iterative and recursive factorial for n = 20. */
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