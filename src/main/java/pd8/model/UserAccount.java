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