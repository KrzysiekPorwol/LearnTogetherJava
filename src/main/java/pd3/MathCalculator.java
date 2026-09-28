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
