package pd8.model;

import java.util.Objects;

public class UserAccountV2 extends UserAccount {
  public UserAccountV2(Long id, String email, String displayName) {
    super(id, email, displayName);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    UserAccountV2 other = (UserAccountV2) obj;
    return Objects.equals(id, other.id);
  }
}
