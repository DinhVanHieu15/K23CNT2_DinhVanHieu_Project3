package k23cnt2.dvh.lesson08.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dvh_book")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DvhBook {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long dvhId;

    String dvhCode;
    String dvhName;
    String dvhDescription;
    String dvhImgUrl;
    Integer dvhQuantity;
    Double dvhPrice;
    Boolean dvhActive;

    // đặt tên field rõ ràng để dùng ở mappedBy phía Author
    @ManyToMany
    @JoinTable(
            name = "dvh_book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    List<DvhAuthor> dvhAuthors = new ArrayList<>();
}
