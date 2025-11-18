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
public class DvhAuthor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long dvhid;
    String dvhCode;
    String dvhName;
    String dvhDescription;
    String dvhImgUrl;
    String dvhEmail;
    String dvhPhone;
    String dvhAddress;
    Boolean dvhActive;

    // tao moi quan he voi bang dvhbook
    @ManyToMany(mappedBy = "DvhAuthor")
    List<DvhBook> dvhBooks = new ArrayList<>();
}
