package K23CNT2_DinhVanHieu_2310900122.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "dvh_order_detail")
public class DvhOrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dvhId;

    // Các trường liên kết ID (Quan trọng để sửa lỗi của bạn)
    private Long dvhOrderId;
    private Long dvhProductId; // <--- ĐÂY LÀ TRƯỜNG BẠN ĐANG THIẾU

    private Integer dvhQuantity;
    private BigDecimal dvhPrice;

    // --- GETTER & SETTER (BẮT BUỘC PHẢI CÓ ĐỦ) ---

    public Long getDvhId() {
        return dvhId;
    }

    public void setDvhId(Long dvhId) {
        this.dvhId = dvhId;
    }

    public Long getDvhOrderId() {
        return dvhOrderId;
    }

    public void setDvhOrderId(Long dvhOrderId) {
        this.dvhOrderId = dvhOrderId;
    }

    // Getter & Setter cho dvhProductId (Sửa lỗi EL1008E)
    public Long getDvhProductId() {
        return dvhProductId;
    }

    public void setDvhProductId(Long dvhProductId) {
        this.dvhProductId = dvhProductId;
    }

    public Integer getDvhQuantity() {
        return dvhQuantity;
    }

    public void setDvhQuantity(Integer dvhQuantity) {
        this.dvhQuantity = dvhQuantity;
    }

    public BigDecimal getDvhPrice() {
        return dvhPrice;
    }

    public void setDvhPrice(BigDecimal dvhPrice) {
        this.dvhPrice = dvhPrice;
    }
}