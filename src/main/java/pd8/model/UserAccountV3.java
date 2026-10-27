package pd8.model;

import java.util.Objects;

public class UserAccountV3 extends UserAccount {
  public UserAccountV3(Long id, String email, String displayName) {
    super(id, email, displayName);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    UserAccountV3 other = (UserAccountV3) obj;
    return Objects.equals(id, other.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
