package zl5;

import zl5.model.OperationResult;
import zl5.model.Product;
import zl5.model.User;

import java.util.List;

public class App {

  public static void main(String[] args) {

    OperationResult<User> user = OperationResult.success(new User("użytkownik1"));
    OperationResult<Double> number = OperationResult.success(1.0);
    OperationResult<List<String>> userList = OperationResult.success(List.of("maslo", "pompa", "bicek"));
    OperationResult<Product> product = OperationResult.success(new Product("krzesło"));

    // !- Odczytanie value bez rzutowania -!

    // Kompilator dzięki generykom wie jakie typy siedzą w user, number, userlist
    // dzieki czemu nie trzeba rzutować, a w przypadku złego przypisania
    // na etapie kompilacji wywali błąd:
    // User incorrectUser = number.getValue();
    User user1 = user.getValue();
    Double number1 = number.getValue();
    List<String> userList1 = userList.getValue();

    System.out.println(user1.getUserName() + " | " + number1 + " | " + userList1);

    // !- Celowe błędne przypisanie -!

    // Dzięki temu że mamy generyki, już w czasie kompilacji
    // wiemy że próbujemy przypisać do konkretnego typu
    // typ który jest inny (incompatible types)
    // product = user;


    // !- Dodatkowe próby przypisania w celu lepszego zrozumienia -!

    // zmienna product oczekuje obiektu klasy OperationResult z typem genercznym Product - dostaje User, co powoduje
    // błąd w czasie kompilacji.
    // OperationResult<Product> product = new OperationResult<>(true, "znaleziono produkt o nazwie: ", new User("szafka"));

    // Ten sposób działa, ale powoduje ze znów przyjmujemy obojętnie jaki object,
    // i zmusza nas znowu do pilnowania, rzutowania, i błędów podczas runtime.
    OperationResult user2 = OperationResult.success(new User("szafka"));
    User castingUser = (User) user2.getValue();
    //Bez rzutowania nie działa.
    //User somebody2 = user2.getValue();

    // !- Test fabryk -!
    // success
    OperationResult<User> user3 = OperationResult.success(new User("uzytkownik3"));

    // Tutaj przez pomyłkę napisałem w <> Number zamiast double i o dziwo zadziałało.
    // kompilator patrzy na typ zmiennej po lewej stronie,
    // wnioskuje ze number2 to Number a double to Number więc przeszło.
    OperationResult<Number> number2 = OperationResult.success(5.0);

    // failure
    OperationResult<User> user4 = OperationResult.failure("Nie udało się wczytać użytkownika");

    //!- Wykorzystanie klasy generycznej -!
    List<User> users = List.of(new User("user1"), new User("user2"), new User("user3"));

    OperationResult<User> userFound = findUser("user2", users);
    System.out.println(userFound.getValue().getUserName());

  }

  public static OperationResult<User> findUser(String name, List<User> users) {
    for (User u : users) {
      if (name.equals(u.getUserName())) {
        return OperationResult.success(u);
      }
    }
    return OperationResult.failure("Nie znaleziono osoby o imieniu " + name);
  }
}
