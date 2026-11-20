package pd14.service.impl;

import lombok.RequiredArgsConstructor;
import pd14.domain.Loan;
import pd14.domain.Member;
import pd14.dto.LoanDto;
import pd14.dto.MemberDto;
import pd14.dto.RegisterMemberCommand;
import pd14.exception.MemberAlreadyExistsException;
import pd14.exception.MemberNotFoundException;
import pd14.mapper.LoanMapper;
import pd14.mapper.MemberMapper;
import pd14.repository.MemberRepository;
import pd14.service.MemberService;

import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;

  @Override
  public MemberDto register(RegisterMemberCommand request) {
    if (request == null) {
      throw new IllegalArgumentException("Request cannot be null");
    }
    if (memberRepository.existsByEmail(request.email())) {
      throw new MemberAlreadyExistsException(request.email());
    }

    Member member = Member.create(request.name(), request.email());
    Member saved = memberRepository.save(member);
    return MemberMapper.toDto(saved);
  }

  @Override
  public MemberDto findById(Long id) {
    return MemberMapper.toDto(requireMember(id));
  }

  @Override
  public List<LoanDto> getActiveLoans(Long memberId) {
    return requireMember(memberId).getActiveLoans().stream()
        .sorted(Comparator.comparing(Loan::getDueDate))
        .map(LoanMapper::toDto)
        .toList();
  }

  private Member requireMember(Long id) {
    return memberRepository.findById(id)
        .orElseThrow(() -> new MemberNotFoundException(id));
  }
}
