package pd6;

public class User implements Entity<Long> {

  private static long nextId = 0;

  private final long id;
  private String name;

  public User(String name) {
    this.id = nextId++;
    this.name = name;
  }

  @Override
  public Long getId() {
    return id;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "User{id=" + id + ", name='" + name + "'}";
  }
}
