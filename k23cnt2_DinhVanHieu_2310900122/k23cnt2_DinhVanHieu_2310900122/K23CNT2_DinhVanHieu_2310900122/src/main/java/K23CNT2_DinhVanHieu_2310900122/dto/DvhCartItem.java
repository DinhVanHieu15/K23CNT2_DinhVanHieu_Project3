package K23CNT2_DinhVanHieu_2310900122.dto;

import java.math.BigDecimal;

public class DvhCartItem {
    private Long dvhId;
    private String dvhName;
    private String dvhImage;
    private BigDecimal dvhPrice;
    private int dvhQuantity;

    public DvhCartItem() {}

    public DvhCartItem(Long dvhId, String dvhName, String dvhImage, BigDecimal dvhPrice, int dvhQuantity) {
        this.dvhId = dvhId;
        this.dvhName = dvhName;
        this.dvhImage = dvhImage;
        this.dvhPrice = dvhPrice;
        this.dvhQuantity = dvhQuantity;
    }

    // --- HÀM TÍNH TIỀN (BẮT BUỘC PHẢI CÓ) ---
    public BigDecimal getDvhTotalPrice() {
        if (dvhPrice == null) return BigDecimal.ZERO;
        return dvhPrice.multiply(BigDecimal.valueOf(dvhQuantity));
    }

    // Getter & Setter
    public Long getDvhId() { return dvhId; }
    public void setDvhId(Long dvhId) { this.dvhId = dvhId; }
    public String getDvhName() { return dvhName; }
    public void setDvhName(String dvhName) { this.dvhName = dvhName; }
    public String getDvhImage() { return dvhImage; }
    public void setDvhImage(String dvhImage) { this.dvhImage = dvhImage; }
    public BigDecimal getDvhPrice() { return dvhPrice; }
    public void setDvhPrice(BigDecimal dvhPrice) { this.dvhPrice = dvhPrice; }
    public int getDvhQuantity() { return dvhQuantity; }
    public void setDvhQuantity(int dvhQuantity) { this.dvhQuantity = dvhQuantity; }
}