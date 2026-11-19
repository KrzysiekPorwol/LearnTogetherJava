package pd14.dto;

public record MemberDto(
    Long id,
    String name,
    String email,
    int activeLoansCount
) {
}
