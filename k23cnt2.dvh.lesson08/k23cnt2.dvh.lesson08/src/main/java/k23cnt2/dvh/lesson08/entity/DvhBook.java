package k23cnt2.dvh.lesson08.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class DvhBook {
    @Id
            @GeneratedValue(strategy = GenerationType.AUTO)
    Long dvhid;
    String dvhCode;
    String dvhName;
    String dvhDescription;
    String dvhImgUrl;
    Integer dvhQuantity;
    Double dvhPrice;
    Boolean dvhActive;

    // thiet ke quan he voi bang dvhAuthor

    @ManyToMany
    @JoinTable(
            name = "dvh_book_author",
            joinColumns = @JoinColumn(name = "dvhBookId"),
            inverseJoinColumns = @JoinColumn(name = "dvhAuthorId")
    )

    List<DvhAuthor> DvhAuthor = new ArrayList<>();

}
