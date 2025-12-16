package k23cnt2.dvh.lesson08.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dvh_author")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DvhAuthor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long dvhId;

    String dvhCode;
    String dvhName;
    String dvhDescription;
    String dvhImgUrl;
    String dvhEmail;
    String dvhPhone;
    String dvhAddress;
    Boolean dvhActive;

    // mappedBy phải trỏ đến TÊN FIELD ở DvhBook: "dvhAuthors"
    @ManyToMany(mappedBy = "dvhAuthors")
    List<DvhBook> dvhBooks = new ArrayList<>();
}
