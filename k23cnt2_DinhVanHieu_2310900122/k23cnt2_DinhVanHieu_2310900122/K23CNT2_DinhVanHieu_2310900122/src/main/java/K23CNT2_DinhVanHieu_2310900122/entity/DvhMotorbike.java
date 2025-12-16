package K23CNT2_DinhVanHieu_2310900122.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "dvh_motorbike")
public class DvhMotorbike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dvhId;

    private String dvhCode;
    private String dvhName;
    private String dvhImage;
    private BigDecimal dvhPrice;
    private Integer dvhQuantity;

    // --- CÁC TRƯỜNG CHI TIẾT SẢN PHẨM ---
    private String dvhType;        // Loại xe (Tay ga, Xe số...)
    private String dvhColor;       // Màu sắc
    private Integer dvhYear;       // Năm sản xuất
    private String dvhCondition;   // Tình trạng (NEW/USED)

    @Column(length = 5000)
    private String dvhDescription; // Mô tả chi tiết

    // --- TRƯỜNG MỚI: THƯƠNG HIỆU (QUAN TRỌNG ĐỂ LỌC) ---
    private String dvhBrand;       // Honda, Yamaha, VinFast...

    // ==========================================================
    // GETTER & SETTER (Bắt buộc phải có đầy đủ)
    // ==========================================================

    public Long getDvhId() { return dvhId; }
    public void setDvhId(Long dvhId) { this.dvhId = dvhId; }

    public String getDvhCode() { return dvhCode; }
    public void setDvhCode(String dvhCode) { this.dvhCode = dvhCode; }

    public String getDvhName() { return dvhName; }
    public void setDvhName(String dvhName) { this.dvhName = dvhName; }

    public String getDvhImage() { return dvhImage; }
    public void setDvhImage(String dvhImage) { this.dvhImage = dvhImage; }

    public BigDecimal getDvhPrice() { return dvhPrice; }
    public void setDvhPrice(BigDecimal dvhPrice) { this.dvhPrice = dvhPrice; }

    public Integer getDvhQuantity() { return dvhQuantity; }
    public void setDvhQuantity(Integer dvhQuantity) { this.dvhQuantity = dvhQuantity; }

    public String getDvhType() { return dvhType; }
    public void setDvhType(String dvhType) { this.dvhType = dvhType; }

    public String getDvhColor() { return dvhColor; }
    public void setDvhColor(String dvhColor) { this.dvhColor = dvhColor; }

    public Integer getDvhYear() { return dvhYear; }
    public void setDvhYear(Integer dvhYear) { this.dvhYear = dvhYear; }

    public String getDvhCondition() { return dvhCondition; }
    public void setDvhCondition(String dvhCondition) { this.dvhCondition = dvhCondition; }

    public String getDvhDescription() { return dvhDescription; }
    public void setDvhDescription(String dvhDescription) { this.dvhDescription = dvhDescription; }

    // Getter & Setter cho dvhBrand
    public String getDvhBrand() { return dvhBrand; }
    public void setDvhBrand(String dvhBrand) { this.dvhBrand = dvhBrand; }
}