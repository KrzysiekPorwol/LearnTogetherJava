package pd6;

import pd6.exception.UserNotFoundException;

public class UserService {

  private final InMemoryRepository<Long, User> userRepository;

  public UserService(InMemoryRepository<Long, User> userRepository) {
    this.userRepository = userRepository;
  }

  public void renameUser(Long id, String newName) {
    User user = userRepository.findById(id)
          .orElseThrow(() -> new UserNotFoundException("Nie ma użytkownika o takim ID."));
    user.setName(newName);
    userRepository.save(user);
  }
}