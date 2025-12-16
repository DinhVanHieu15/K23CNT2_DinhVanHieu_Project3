package K23CNT2_DinhVanHieu_2310900122.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "dvh_motorbike_image")
public class DvhMotorbikeImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dvh_image_id")
    private Long dvhImageId;

    @Column(name = "dvh_image_url", nullable = false, length = 255)
    private String dvhImageUrl;

    @Column(name = "dvh_is_primary")
    private Boolean dvhIsPrimary;

    @ManyToOne
    @JoinColumn(name = "dvh_motorbike_id", nullable = false)
    private DvhMotorbike dvhMotorbike;

    public DvhMotorbikeImage() {
    }

    public Long getDvhImageId() {
        return dvhImageId;
    }

    public void setDvhImageId(Long dvhImageId) {
        this.dvhImageId = dvhImageId;
    }

    public String getDvhImageUrl() {
        return dvhImageUrl;
    }

    public void setDvhImageUrl(String dvhImageUrl) {
        this.dvhImageUrl = dvhImageUrl;
    }

    public Boolean getDvhIsPrimary() {
        return dvhIsPrimary;
    }

    public void setDvhIsPrimary(Boolean dvhIsPrimary) {
        this.dvhIsPrimary = dvhIsPrimary;
    }

    public DvhMotorbike getDvhMotorbike() {
        return dvhMotorbike;
    }

    public void setDvhMotorbike(DvhMotorbike dvhMotorbike) {
        this.dvhMotorbike = dvhMotorbike;
    }
}
