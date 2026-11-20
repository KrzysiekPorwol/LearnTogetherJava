package pd14.repository;

import pd14.domain.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MemberRepository {

  private final Map<Long, Member> members = new HashMap<>();
  private long nextId = 1L;

  public Member save(Member member) {
    if (member == null) {
      throw new IllegalArgumentException("Member cannot be null");
    }
    if (member.getId() == null) {
      member.assignId(nextId++);
    }
    members.put(member.getId(), member);
    return member;
  }

  public Optional<Member> findById(Long id) {
    return Optional.ofNullable(members.get(id));
  }

  public boolean existsByEmail(String email) {
    return members.values().stream()
        .anyMatch(member -> member.getEmail().equalsIgnoreCase(email));
  }

  public List<Member> findAll() {
    return List.copyOf(members.values());
  }
}
