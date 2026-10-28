package pd8.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class UserAccountV4 extends UserAccount {

  @Getter
  @Setter
  private String status;

  public UserAccountV4(Long id, String email, String displayName, String status) {
    super(id, email, displayName);
    this.status = status;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    UserAccountV4 other = (UserAccountV4) obj;
    return Objects.equals(id, other.id)
        && Objects.equals(status, other.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, status);
  }
}
