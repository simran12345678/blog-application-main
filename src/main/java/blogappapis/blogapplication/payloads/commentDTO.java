package blogappapis.blogapplication.payloads;

import blogappapis.blogapplication.entity.Post;
import blogappapis.blogapplication.entity.user;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class commentDTO {
    private Integer commentId;
    private String context;

}
