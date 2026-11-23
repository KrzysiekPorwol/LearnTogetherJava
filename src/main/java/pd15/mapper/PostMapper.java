package pd15.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pd15.domain.Post;
import pd15.dto.PostResponse;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PostMapper {

  public static Post toDomain(PostResponse postResponse) {
    return new Post(postResponse.id(), postResponse.title());
  }
}
