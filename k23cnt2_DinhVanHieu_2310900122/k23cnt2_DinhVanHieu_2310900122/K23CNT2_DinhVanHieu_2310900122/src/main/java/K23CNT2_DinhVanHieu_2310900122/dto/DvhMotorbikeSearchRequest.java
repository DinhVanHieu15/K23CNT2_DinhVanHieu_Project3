package K23CNT2_DinhVanHieu_2310900122.dto;

import java.math.BigDecimal;

public class DvhMotorbikeSearchRequest {

    private String dvhKeyword;
    private String dvhProvince;

    // --- CÁC TRƯỜNG LỌC CHÍNH (Đã chuẩn hóa) ---
    private String dvhType;        // Loại xe (TAY_GA, XE_SO...)
    private String dvhPriceRange;  // Khoảng giá (under-20, 20-50...)

    // *** QUAN TRỌNG: Dùng String để khớp với cột dvhBrand trong Database ***
    private String dvhBrand;       // Hãng xe (Honda, Yamaha...)

    // --- Các trường phụ (Giữ lại nếu muốn mở rộng sau này) ---
    private BigDecimal dvhPriceFrom;
    private BigDecimal dvhPriceTo;


    // =======================================================
    // GETTER & SETTER
    // =======================================================

    public String getDvhKeyword() {
        return dvhKeyword;
    }

    public void setDvhKeyword(String dvhKeyword) {
        this.dvhKeyword = dvhKeyword;
    }

    public String getDvhProvince() {
        return dvhProvince;
    }

    public void setDvhProvince(String dvhProvince) {
        this.dvhProvince = dvhProvince;
    }

    // --- Getter/Setter cho Brand (String) ---
    public String getDvhBrand() {
        return dvhBrand;
    }

    public void setDvhBrand(String dvhBrand) {
        this.dvhBrand = dvhBrand;
    }

    public String getDvhType() {
        return dvhType;
    }

    public void setDvhType(String dvhType) {
        this.dvhType = dvhType;
    }

    public String getDvhPriceRange() {
        return dvhPriceRange;
    }

    public void setDvhPriceRange(String dvhPriceRange) {
        this.dvhPriceRange = dvhPriceRange;
    }

    public BigDecimal getDvhPriceFrom() {
        return dvhPriceFrom;
    }

    public void setDvhPriceFrom(BigDecimal dvhPriceFrom) {
        this.dvhPriceFrom = dvhPriceFrom;
    }

    public BigDecimal getDvhPriceTo() {
        return dvhPriceTo;
    }

    public void setDvhPriceTo(BigDecimal dvhPriceTo) {
        this.dvhPriceTo = dvhPriceTo;
    }
}