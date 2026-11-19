package pd14.mapper;

import pd14.domain.Member;
import pd14.dto.MemberDto;

public final class MemberMapper {

  private MemberMapper() {
  }

  public static MemberDto toDto(Member member) {
    return new MemberDto(
        member.getId(),
        member.getName(),
        member.getEmail(),
        member.getActiveLoans().size()
    );
  }
}
