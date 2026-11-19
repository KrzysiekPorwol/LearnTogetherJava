package pd14.service;

import pd14.dto.LoanDto;
import pd14.dto.MemberDto;
import pd14.dto.RegisterMemberRequest;

import java.util.List;

public interface MemberService {

  MemberDto register(RegisterMemberRequest request);

  MemberDto findById(Long id);

  List<LoanDto> getActiveLoans(Long memberId);
}
