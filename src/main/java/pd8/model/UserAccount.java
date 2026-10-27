package pd8.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserAccount {

  protected final Long id;
  protected final String email;
  protected final String displayName;

}

//PD-8
//Wykaż, że poprawne działanie HashSet i HashMap zależy od poprawnej implementacji equals() i hashCode()
//
//Przygotuj klasę UserAccount z polami:
//Long id
//String email
//String displayName`
//
//Następnie wykonaj 4 eksperymenty:
//
//Eksperyment A (UserAccountV1)
//Utwórz dwa obiekty z tymi samymi danymi, ale bez nadpisywania equals() i hashCode().
//Dodaj oba do HashSet i sprawdź:
//rozmiar seta
//wynik equals()
//Dodaj komentarz wyjaśniający, dlaczego kolekcja zachowała się właśnie w taki sposób
//
//Eksperyment B (UserAccountV2)
//Nadpisz tylko equals(), ale nie nadpisuj hashCode().
//Ponownie dodaj dwa logicznie identyczne obiekty do HashSet i sprawdź:
//rozmiar seta
//wynik contains(new UserAccount(...))
//Dodaj komentarz wyjaśniający, dlaczego samo equals() nie wystarczyło
//
//Eksperyment C (UserAccountV3)
//Nadpisz poprawnie zarówno equals(), jak i hashCode().
//Sprawdź działanie:
//HashSet
//    HashMap
//contains(...)
//get(...)
//Dodaj komentarz wyjaśniający, dlaczego teraz kolekcje działają zgodnie z intuicją
