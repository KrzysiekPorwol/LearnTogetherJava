package pd10_1;

public class App {
}

//PD-10.1
//Sklep z promocjami
//metoda split() na String podzieli wam text
//order;customer;tier;product;qty;price
//O1;C1;GOLD;MILK;3;4,50
//O2;C2;std;MILK;5;4.50
//O1;C1;GOLD;BREAD;1;3.20
//O3;;GOLD;EGGS;10;0.90
//O4;C3;SILVER;BEER;-2;3.00
//O2;C2;STD;BREAD;1;3.20
//Magazyn: MILK=6, BREAD=5, EGGS=30, BEER=10. Dopisz własne wiersze
//
//Zakazy: pętle, try/catch w streamie, if w promocjach, mutowalny stan w lambdach.
//Narzucone nazwy: ThrowingFunction, Result, Promotion.
//
//Parsowanie: każda linia to Result. Błędy i poprawne linie rozdzielasz jednym przejściem.
// Linie jednego zamówienia mogą być rozrzucone po pliku.
//Promocje: Promotion to własny interfejs funkcyjny ze składaniem i warunkiem. Promocje:
//MILK 3 w cenie 2
//GOLD −10%
//zamówienie > 50 zł → −5 zł
//łączny rabat ≤ 30%
//Kolejność promocji zmienia wynik, więc wybierz ją i uzasadnij.
//
//Alokacja:
//Kolejność obsługi: najpierw GOLD, potem wartość malejąco.
//GOLD może dostać zamówienie częściowo, reszta wszystko albo nic.
//Magazynu nie modyfikujesz, każdy krok zwraca nowy stan.
//Raport: per tier podajesz przychód, odrzucone zamówienia i top 2 zamówienia. Top 2 zbiera własny Collector.of, combiner ma działać.
//
//    Odpowiedzialność:
//Zamówienie nie zna magazynu, a promocje nie znają ani magazynu, ani siebie nawzajem.
//Nie robisz jednego Service od wszystkiego.

// =====================================================================================
// PD-10.1 — TO SAMO, TYLKO PO LUDZKU
// =====================================================================================
//
// OGÓLNIE: piszesz mini-sklep, który robi cztery rzeczy po kolei:
//   1) wczytuje linie tekstu (CSV ze średnikami) i zamienia je na pozycje zamówień,
//   2) skleja pozycje w zamówienia i nalicza im promocje,
//   3) próbuje zrealizować zamówienia z magazynu (którego jest za mało dla wszystkich),
//   4) drukuje raport pogrupowany po tierze klienta (GOLD / SILVER / STD).
// Wszystko w stylu funkcyjnym: streamy, lambdy, niemutowalne obiekty.
//
// -------------------------------------------------------------------------------------
// ETAP 1 — PARSOWANIE
// -------------------------------------------------------------------------------------
// Format linii: numerZamówienia;klient;tier;produkt;ilość;cenaZaSztukę
// Pierwsza linia to nagłówek — nie jest danymi.
//
// Dane są CELOWO brudne. Przyjrzyj się im — część wierszy ma pułapki, np.:
//   - cena z przecinkiem zamiast kropki ("4,50"),
//   - tier małymi literami ("std" vs "STD"),
//   - pusty klient,
//   - ujemna ilość.
// Sam zdecyduj, co jest do naprawienia (znormalizowania), a co jest błędem.
//
// Każda linia ma dać Result — czyli obiekt "albo się udało (jest wartość),
// albo się nie udało (jest opis błędu)". Program NIE wywala się na złej linii.
//
// ThrowingFunction = Twój interfejs funkcyjny podobny do Function, ale którego
// metoda może rzucić wyjątek (checked). Po co? Bo w lambdzie w streamie nie wolno
// Ci pisać try/catch. Więc try/catch ma siedzieć RAZ, w jednym miejscu poza streamem
// (np. w metodzie pomocniczej, która zamienia ThrowingFunction na funkcję zwracającą
// Result). Stream zostaje czysty.
//
// "Jednym przejściem" = jeden stream dzieli wyniki na dwie kupki: poprawne i błędne.
// Nie przechodzisz po liście dwa razy (raz filtrując sukcesy, raz błędy).
//
// "Linie jednego zamówienia rozrzucone po pliku" = O1 ma pozycje w linii 1 i 3,
// O2 w linii 2 i 6. Musisz je zgrupować po numerze zamówienia, żeby dostać całe
// zamówienie z wieloma produktami.
//
// -------------------------------------------------------------------------------------
// ETAP 2 — PROMOCJE
// -------------------------------------------------------------------------------------
// Promotion = Twój własny interfejs funkcyjny: "dostaję zamówienie/kwotę, zwracam
// rabat (albo cenę po rabacie)". Ma mieć dodatkowo (jako metody default):
//   - SKŁADANIE — możliwość połączenia dwóch promocji w jedną (jak andThen w Function),
//   - WARUNEK — możliwość powiedzenia "ta promocja działa tylko gdy ..." (predykat).
// Dlaczego? Bo w samych promocjach nie wolno używać if. Warunek ma być wyrażony
// przez metodę interfejsu, a nie przez if w środku lambdy.
//
// Cztery reguły:
//   - MILK "3 w cenie 2" — co trzecie mleko gratis (6 mleka = płacisz za 4),
//   - klient GOLD — 10% taniej,
//   - zamówienie powyżej 50 zł — 5 zł mniej,
//   - limit: łączny rabat nie może przekroczyć 30% wartości zamówienia przed rabatami.
//
// Kolejność ma znaczenie: -10% a potem -5 zł daje inną kwotę niż -5 zł a potem -10%.
// Do tego "> 50 zł" — liczysz od ceny przed czy po wcześniejszych rabatach?
// Masz wybrać kolejność i w komentarzu napisać DLACZEGO taka.
//
// -------------------------------------------------------------------------------------
// ETAP 3 — ALOKACJA (realizacja z magazynu)
// -------------------------------------------------------------------------------------
// Stan magazynu: MILK=6, BREAD=5, EGGS=30, BEER=10. Nie wystarczy dla wszystkich.
//
// Kolejka obsługi: najpierw wszystkie zamówienia GOLD, potem reszta;
// w ramach grupy — od najdroższego do najtańszego.
//
// Zasady wydawania:
//   - GOLD: dostaje tyle, ile się da (np. chce 3 mleka, zostały 2 → dostaje 2),
//   - pozostali: wszystko albo nic — jeśli choć jednej pozycji brakuje,
//     zamówienie jest ODRZUCONE i magazyn się nie zmienia.
//
// "Magazynu nie modyfikujesz" = Warehouse jest niemutowalny. Wydanie towaru nie
// zmienia starego obiektu, tylko zwraca NOWY Warehouse z pomniejszonymi stanami.
// Każde zamówienie dostaje magazyn "po poprzednim" i oddaje magazyn "po sobie".
// (Podpowiedź kierunku: to jest zwijanie listy do jednego wyniku krok po kroku.)
//
// -------------------------------------------------------------------------------------
// ETAP 4 — RAPORT
// -------------------------------------------------------------------------------------
// Dla każdego tieru (GOLD, SILVER, STD) wypisz:
//   - przychód — suma tego, co faktycznie sprzedano (po promocjach),
//   - odrzucone zamówienia,
//   - 2 najdroższe zamówienia.
//
// Top 2 masz zebrać WŁASNYM kolektorem zrobionym przez Collector.of(...)
// (a nie sorted().limit(2)). Collector.of wymaga m.in. "combinera" — funkcji, która
// łączy dwa częściowe wyniki (używana przy parallel stream). Ma działać poprawnie,
// czyli: połączenie dwóch "top 2" ma dać prawidłowe "top 2" z całości.
//
// -------------------------------------------------------------------------------------
// ODPOWIEDZIALNOŚĆ (podział na klasy)
// -------------------------------------------------------------------------------------
//   - Order (zamówienie) nic nie wie o magazynie,
//   - każda promocja jest samodzielna — nie wie o magazynie ani o innych promocjach
//     (łączy je dopiero składanie),
//   - zamiast jednej klasy-kombajnu "ShopService" — kilka małych, każda od jednego
//     etapu (parsowanie / promocje / alokacja / raport).
//
// -------------------------------------------------------------------------------------
// ZAKAZY (w skrócie)
// -------------------------------------------------------------------------------------
//   - żadnych pętli for/while — tylko streamy,
//   - żadnego try/catch w lambdach streamu,
//   - żadnego if w promocjach,
//   - lambdy nie zmieniają zmiennych z zewnątrz (żadnego licznika++ czy list.add() w forEach).
//
// "Dopisz własne wiersze" = dorzuć swoje linie testowe, żeby sprawdzić przypadki
// brzegowe (np. zamówienie, które przebija limit 30%, albo którego magazyn nie pokryje).
//
// DO ROZSTRZYGNIĘCIA SAMEMU (zadanie tego nie mówi — zdecyduj i zapisz decyzję):
//   - "wartość malejąco" przy kolejce — przed czy po promocjach?
//   - GOLD dostał zamówienie częściowo — czy promocje przeliczasz od nowa
//     (np. MILK 3w2 przy 2 mlekach już nie działa)?
//   - limit 30% liczysz per zamówienie (najbardziej sensowne) czy inaczej?
// =====================================================================================
