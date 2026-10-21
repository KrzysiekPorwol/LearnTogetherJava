package pd6;

import pd6.exception.UserNotFoundException;

public class App {
  public static void main(String[] args) {
    InMemoryRepository<String, Product> productRepository = new InMemoryRepository<>();

    InMemoryRepository<Long, User> userRepository = new InMemoryRepository<>();

    UserService userService = new UserService(userRepository);

    Product product1 = new Product("abc1", "krzesło");
    Product product2 = new Product("abc2", "stół");

    User user1 = new User("Zdzichu");
    User user2 = new User("Dawidek");

    productRepository.save(product1);
    productRepository.save(product2);
    userRepository.save(user1);
    userRepository.save(user2);

    System.out.println("Wyswietlamy wszystkie obiekty: ");
    System.out.println(productRepository.findAll());
    System.out.println(userRepository.findAll());

    System.out.println("Wyswietlamy wybrany obiekt: ");
    System.out.println(userRepository.findById(user2.getId()));
    System.out.println(productRepository.findById(product2.getId()));

    System.out.println("Wyswietlamy nieistniejacy obiekt: ");
    System.out.println(userRepository.findById(15L));
    System.out.println(productRepository.findById("produktNieIstniejacy"));

    userRepository.deleteById(user1.getId());
    productRepository.deleteById(product1.getId());

    System.out.println("Wyswietlamy wszystkie obiekty po usunieciu jednego obiektu: ");
    System.out.println(productRepository.findAll());
    System.out.println(userRepository.findAll());

    try {
      userService.renameUser(user2.getId(), "Juz nie dawidek tylko Krzysiu");
    } catch (UserNotFoundException e) {
      System.out.println("Brak takiego użytkownika w repozytorium");
    }
    System.out.println("Wyswietlamy użytkownika po zmianie nazwy: ");
    System.out.println(userRepository.findById(user2.getId()));

    System.out.println("Teraz wyświetlimy nieudaną próbę zmiany nazwy nieistniejącego użytkownika");
    try {
      userService.renameUser(145L, "Nowa nazwa nieistniejącego użytkownika");
    } catch (UserNotFoundException e) {
      System.out.println("Brak takiego użytkownika w repozytorium");
    }
    System.out.println("Próbujemy wyświetlić użytkownika którego nie ma w bazie po zmianie nazwy: ");
    System.out.println(userRepository.findById(145L));
  }
}