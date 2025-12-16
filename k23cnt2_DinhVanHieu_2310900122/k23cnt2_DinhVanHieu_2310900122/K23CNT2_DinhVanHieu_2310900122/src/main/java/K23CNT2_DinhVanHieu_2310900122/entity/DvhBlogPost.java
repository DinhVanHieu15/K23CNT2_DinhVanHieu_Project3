package K23CNT2_DinhVanHieu_2310900122.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat; // 1. IMPORT CÁI NÀY
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "dvh_blog_post")
public class DvhBlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dvhId;

    private String dvhTitle;

    @Column(length = 5000)
    private String dvhContent;

    private String dvhImage;

    private Long dvhAuthorId;

    private LocalDateTime dvhCreatedAt;

    // --- 2. THÊM DÒNG NÀY ĐỂ SỬA LỖI 400 ---
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dvhCreatedDate;

    // --- Constructor ---
    @PrePersist
    public void prePersist() {
        if (dvhCreatedAt == null) dvhCreatedAt = LocalDateTime.now();
        if (dvhCreatedDate == null) dvhCreatedDate = LocalDate.now();
    }

    // --- GETTER & SETTER ---
    public Long getDvhId() { return dvhId; }
    public void setDvhId(Long dvhId) { this.dvhId = dvhId; }

    public String getDvhTitle() { return dvhTitle; }
    public void setDvhTitle(String dvhTitle) { this.dvhTitle = dvhTitle; }

    public String getDvhContent() { return dvhContent; }
    public void setDvhContent(String dvhContent) { this.dvhContent = dvhContent; }

    public String getDvhImage() { return dvhImage; }
    public void setDvhImage(String dvhImage) { this.dvhImage = dvhImage; }

    public Long getDvhAuthorId() { return dvhAuthorId; }
    public void setDvhAuthorId(Long dvhAuthorId) { this.dvhAuthorId = dvhAuthorId; }

    public LocalDateTime getDvhCreatedAt() { return dvhCreatedAt; }
    public void setDvhCreatedAt(LocalDateTime dvhCreatedAt) { this.dvhCreatedAt = dvhCreatedAt; }

    public LocalDate getDvhCreatedDate() { return dvhCreatedDate; }
    public void setDvhCreatedDate(LocalDate dvhCreatedDate) { this.dvhCreatedDate = dvhCreatedDate; }
}