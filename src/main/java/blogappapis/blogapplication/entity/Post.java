package blogappapis.blogapplication.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    private String title;
    private String content;
    private String imgName;
    private Date date;

    @ManyToOne
    private Category category;
    @ManyToOne
    private  user user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Comment> comments=new HashSet<>();

}
