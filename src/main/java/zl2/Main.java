package zl2;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    double weight;
    double heightInCentimeters;
    Scanner input = new Scanner(System.in);
    System.out.println("Podaj wage w kg: ");
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