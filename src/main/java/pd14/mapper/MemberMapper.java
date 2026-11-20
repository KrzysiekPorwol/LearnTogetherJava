package pd14.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pd14.domain.Member;
import pd14.dto.MemberDto;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MemberMapper {

  public static MemberDto toDto(Member member) {
    return new MemberDto(
        member.getId(),
        member.getName(),
        member.getEmail(),
        member.getActiveLoans().size()
    );
  }
}
