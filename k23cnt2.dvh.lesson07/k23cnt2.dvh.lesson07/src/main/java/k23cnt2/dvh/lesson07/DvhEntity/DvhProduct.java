package k23cnt2.dvh.lesson07.DvhEntity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor @AllArgsConstructor
public class DvhProduct {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String imageUrl;
    private Integer quantity;
    private BigDecimal price;
    private String content;
    private Boolean status = true;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)  // FK nằm ở products
    private DvhCategory category;
}
