package pd11;

public class App {

  public static void main(String[] args) {

  }
}


//System wczytuje dane sprzedaży z CSV, przetwarza i eksportuje w dwóch formatach.
//
//WEJŚCIE (sales.csv, min. 30 wierszy, 5 kategorii):
//date,product_id,product_name,category,quantity,unit_price
//
//WYMAGANIA:
//Wczytaj CSV przez Files.lines() + Stream (leniwe)
//Pomiń błędne wiersze z logowaniem na stderr (nie crash!)
//Oblicz przez Stream API:
//a) Całkowity przychód (quantity * unit_price)
//b) Top 3 produkty według przychodu
//c) Przychód per kategoria (posortowany malejąco)
//d) Średnia cena per kategoria
//e) Dzień z najwyższą sprzedażą
//Wyeksportuj wyniki do:
//a) report.json — przez Jackson (wcięcia, daty ISO)
//b) summary.csv — tabela kategorii z przychodem i średnią ceną
//
//Klasa Report z polami odpowiadającymi wynikom (do serializacji Jackson).