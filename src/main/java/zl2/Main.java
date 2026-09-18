package zl2;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    double weight;
    double heightInCentimeters;
    Scanner input = new Scanner(System.in);
    System.out.println("Podaj wage: ");
    weight = input.nextInt();
    input.nextLine();
    System.out.println("Podaj wzrost w cm: ");
    heightInCentimeters = input.nextInt();
    if (heightInCentimeters < 30) {
      System.out.print("Jestes zbyt mały.");
      System.out.println("");
    }
    if (heightInCentimeters > 250) {
      System.out.print("Jestes zbyt duży.");
      System.out.println("");
    }
    input.nextLine();

    double heightInMeters = heightInCentimeters / 100;
    double bmi = weight / (heightInMeters * heightInMeters);

    System.out.println(String.format("Wynik: %.2f", bmi));


    if (bmi < 18.5) {
      System.out.println("Niedowaga");
    } else if (bmi >= 18.5 && bmi < 24.9) {
      System.out.println("Norma");
    } else if (bmi >= 25 && bmi < 29.9) {
      System.out.println("Nadwaga");
    } else if (bmi >= 30) {
      System.out.println("Otyłość");
    }
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