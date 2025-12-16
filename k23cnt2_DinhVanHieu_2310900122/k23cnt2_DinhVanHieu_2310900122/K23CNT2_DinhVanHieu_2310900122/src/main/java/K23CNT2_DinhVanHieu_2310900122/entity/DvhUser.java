package K23CNT2_DinhVanHieu_2310900122.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "dvh_user")
public class DvhUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dvhId;

    @Column(unique = true)
    private String dvhUsername;
    private String dvhPassword;
    private String dvhFullName;
    private String dvhEmail;
    private String dvhPhone;

    // --- BỔ SUNG TRƯỜNG NÀY ĐỂ SỬA LỖI ---
    private String dvhAddress;

    private String dvhRole; // ADMIN, STAFF, USER
    private Boolean dvhActive;

    // ==================================================
    // GETTER & SETTER (Bắt buộc phải có để HTML đọc được)
    // ==================================================

    public Long getDvhId() { return dvhId; }
    public void setDvhId(Long dvhId) { this.dvhId = dvhId; }

    public String getDvhUsername() { return dvhUsername; }
    public void setDvhUsername(String dvhUsername) { this.dvhUsername = dvhUsername; }

    public String getDvhPassword() { return dvhPassword; }
    public void setDvhPassword(String dvhPassword) { this.dvhPassword = dvhPassword; }

    public String getDvhFullName() { return dvhFullName; }
    public void setDvhFullName(String dvhFullName) { this.dvhFullName = dvhFullName; }

    public String getDvhEmail() { return dvhEmail; }
    public void setDvhEmail(String dvhEmail) { this.dvhEmail = dvhEmail; }

    public String getDvhPhone() { return dvhPhone; }
    public void setDvhPhone(String dvhPhone) { this.dvhPhone = dvhPhone; }

    // --- Getter/Setter cho dvhAddress (QUAN TRỌNG) ---
    public String getDvhAddress() { return dvhAddress; }
    public void setDvhAddress(String dvhAddress) { this.dvhAddress = dvhAddress; }

    public String getDvhRole() { return dvhRole; }
    public void setDvhRole(String dvhRole) { this.dvhRole = dvhRole; }

    public Boolean getDvhActive() { return dvhActive; }
    public void setDvhActive(Boolean dvhActive) { this.dvhActive = dvhActive; }
}