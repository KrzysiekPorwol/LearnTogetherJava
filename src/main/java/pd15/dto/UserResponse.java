package pd15.dto;

public record UserResponse(Long id, String name, String email, AddressResponse address) {
}
