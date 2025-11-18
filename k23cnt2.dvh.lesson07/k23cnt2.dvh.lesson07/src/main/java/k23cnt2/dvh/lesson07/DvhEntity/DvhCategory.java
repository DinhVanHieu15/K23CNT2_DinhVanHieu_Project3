package k23cnt2.dvh.lesson07.DvhEntity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "categories")
@Data @NoArgsConstructor @AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DvhCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "category_name", nullable = false, length = 150)
    String categoryName;

    @Column(name = "category_status", nullable = false)
    Boolean categoryStatus = true;
}
