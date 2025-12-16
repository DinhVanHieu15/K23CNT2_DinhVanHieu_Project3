package K23CNT2_DinhVanHieu_2310900122.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "dvh_brand")
public class DvhBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dvhId; // Đổi từ id -> dvhId

    private String dvhName; // Đổi từ name -> dvhName
    private String dvhLogo; // Đổi từ logo -> dvhLogo

    // --- GETTER & SETTER (Đã cập nhật tên mới) ---
    public Long getDvhId() { return dvhId; }
    public void setDvhId(Long dvhId) { this.dvhId = dvhId; }

    public String getDvhName() { return dvhName; }
    public void setDvhName(String dvhName) { this.dvhName = dvhName; }

    public String getDvhLogo() { return dvhLogo; }
    public void setDvhLogo(String dvhLogo) { this.dvhLogo = dvhLogo; }
}