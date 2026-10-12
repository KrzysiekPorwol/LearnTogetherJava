package pd5;

import pd5.model.ConsultantNote;
import pd5.model.Email;
import pd5.model.Sendable;
import pd5.model.Sms;
import pd5.service.NotificationSender;

public class App {

  public static void main(String[] args) {
    NotificationSender sender = new NotificationSender();

    Email email1 = new Email("Umyj gary", "zadanie domowe", "MojeDziecko@gmail.com");
    Email email2 = new Email("Zrob task nr 4", "Podział zadań", "pracownikNr3@gmail.com");
    Sms sms1 = new Sms("Przyjde do domu pozniej", "+48668473268");
    Sms sms2 = new Sms("pomocy!", "997");
    Sms tooLongSms = new Sms("x".repeat(161), "+48500100200");

    System.out.println("=== Wysyłka tablicy powiadomień ===");
    Sendable[] notifications = {email1, sms1, email2, sms2, tooLongSms};
    sender.sendAll(notifications);

    System.out.println("\n=== Ponowna wysyłka tej samej tablicy ===");
    sender.sendAll(notifications);
    System.out.println("Czy za długi SMS jest oznaczony jako wysłany? " + tooLongSms.isSent());

    System.out.println("\n=== Notatka konsultanta ===");
    ConsultantNote note = new ConsultantNote("Trawa smakuje źle", "Di Caprio");
    System.out.println(note);
    note.changeContents("Trawa smakuje dobrze");
    System.out.println(note);
    note.archive();
    note.changeContents("Null jednak nie jest taki straszny!");
    note.archive();
    System.out.println(note);
  }
}

// Uzasadnienie decyzji projektowych:
//
// 1. Gdzie potrzebujesz wspólnego kontraktu i dlaczego?
//    Tylko przy wysyłce. NotificationSender.sendAll() musi wywołać send() na e-mailu i SMS-ie bez
//    sprawdzania typu, więc oba muszą mieć wspólny typ z metodą send() - interfejs Sendable.
//    Notatka konsultanta nie ma tego kontraktu, bo nie da się jej wysłać. Ma podobne pola i tę samą
//    walidację treści, ale wspólne dane to nie wspólne zachowanie. Gdyby dziedziczyła po Notification,
//    dostałaby send(), które dla notatki nie ma sensu. Walidację współdzielę przez kompozycję
//    (ContentValidator), a nie przez wspólnego rodzica.
//
// 2. Czy klasa abstrakcyjna wnosi coś poza usunięciem kilku powtórzeń?
//    Tak. Notification pilnuje reguły "wysyłka tylko raz" (Template Method). send() jest final, pole
//    sent jest prywatne i nie ma settera, więc żadna podklasa ani kod z zewnątrz nie może wysłać
//    powiadomienia dwa razy ani oznaczyć go jako wysłanego po nieudanej walidacji. Podklasy dostarczają
//    tylko swoją walidację (validateSend) i sposób doręczenia (deliver). Bez klasy abstrakcyjnej każdy
//    kanał musiałby sam pamiętać o tej regule.
//
// 3. Czy dodanie kolejnego kanału wysyłki wymagałoby zmiany metody przetwarzającej tablicę?
//    Nie. sendAll() zależy tylko od interfejsu Sendable. Nowy kanał  rozszerza Notification o dadatkowa klase,
//    implementuje validateSend() i deliver() i od razu działa.
