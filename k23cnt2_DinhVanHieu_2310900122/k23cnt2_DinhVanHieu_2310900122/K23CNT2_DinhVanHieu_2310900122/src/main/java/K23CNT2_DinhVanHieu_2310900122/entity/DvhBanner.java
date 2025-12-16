package K23CNT2_DinhVanHieu_2310900122.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "dvh_banner")
public class DvhBanner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dvhId;

    private String dvhImage; // Tên file ảnh
    private Boolean dvhActive; // true: Hiển thị, false: Ẩn

    // Getter & Setter
    public Long getDvhId() { return dvhId; }
    public void setDvhId(Long dvhId) { this.dvhId = dvhId; }
    public String getDvhImage() { return dvhImage; }
    public void setDvhImage(String dvhImage) { this.dvhImage = dvhImage; }
    public Boolean getDvhActive() { return dvhActive; }
    public void setDvhActive(Boolean dvhActive) { this.dvhActive = dvhActive; }
}