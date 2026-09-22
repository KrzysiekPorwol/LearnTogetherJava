package zl2;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {

    double weightInKilograms;
    double heightInCentimeters;

    Scanner input = new Scanner(System.in);

    System.out.println("Podaj wage w kg: ");
    weightInKilograms = input.nextInt();
    input.nextLine();

    System.out.println("Podaj wzrost w cm: ");
    heightInCentimeters = input.nextInt();

    if (heightInCentimeters < 30) {
      System.out.println("Jestes zbyt mały.");
    }
    if (heightInCentimeters > 250) {
      System.out.println("Jestes zbyt duży.");
    }

    input.nextLine();

    double heightInMeters = heightInCentimeters / 100.0;
    double bmi = weightInKilograms / (heightInMeters * heightInMeters);

    System.out.printf("Wynik: %.2f%n", bmi);


    if (bmi < 18.5) {
      System.out.println("Niedowaga");
    } else if (bmi >= 18.5 && bmi < 25) {
      System.out.println("Norma");
    } else if (bmi >= 25 && bmi < 30) {
      System.out.println("Nadwaga");
    } else if (bmi >= 30) {
      System.out.println("Otyłość");
    }
    input.close();
  }
}
//ZL-2 Kalkulator z różnymi typami
//
//Napisz program który:
//Wczyta od użytkownika wagę [kg] (double) i wzrost [cm] (double)
//Obliczy BMI = waga / (wzrost_w_metrach ^ 2)
//Wyświetli wynik z dokładnością do 2 miejsc po przecinku: "BMI: 22.45"
//Wyświetli kategorię: Niedowaga (<18.5), Norma (18.5-24.9), Nadwaga (25-29.9), Otyłość (>=30)
//Bonus: sprawdź czy wzrost jest sensowny (30-250 cm) — jeśli nie, wypisz ostrzeżenie