package K23CNT2_DinhVanHieu_2310900122.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat; // Import định dạng ngày
import java.math.BigDecimal;
import java.time.LocalDate; // Import LocalDate

@Entity
@Table(name = "dvh_voucher")
public class DvhVoucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dvhId;

    @Column(unique = true, nullable = false)
    private String dvhCode;

    private BigDecimal dvhDiscount; // Số tiền giảm
    private Integer dvhQuantity;    // Số lượng

    // --- THÊM 2 TRƯỜNG NGÀY THÁNG ---
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dvhStartDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dvhEndDate;

    // --- GETTER & SETTER ---

    public Long getDvhId() { return dvhId; }
    public void setDvhId(Long dvhId) { this.dvhId = dvhId; }

    public String getDvhCode() { return dvhCode; }
    public void setDvhCode(String dvhCode) { this.dvhCode = dvhCode; }

    public BigDecimal getDvhDiscount() { return dvhDiscount; }
    public void setDvhDiscount(BigDecimal dvhDiscount) { this.dvhDiscount = dvhDiscount; }

    public Integer getDvhQuantity() { return dvhQuantity; }
    public void setDvhQuantity(Integer dvhQuantity) { this.dvhQuantity = dvhQuantity; }

    // --- QUAN TRỌNG: CÁC HÀM SỬA LỖI ---
    public LocalDate getDvhStartDate() { return dvhStartDate; }
    public void setDvhStartDate(LocalDate dvhStartDate) { this.dvhStartDate = dvhStartDate; }

    public LocalDate getDvhEndDate() { return dvhEndDate; }
    public void setDvhEndDate(LocalDate dvhEndDate) { this.dvhEndDate = dvhEndDate; }
}