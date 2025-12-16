package K23CNT2_DinhVanHieu_2310900122.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "dvh_order")
public class DvhOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dvhId;

    private String dvhCode;        // Mã đơn hàng
    private Long dvhUserId;        // ID người đặt
    private LocalDate dvhOrderDate; // Ngày đặt
    private BigDecimal dvhTotalAmount; // Tổng tiền

    // --- CÁC TRƯỜNG MỚI (SỬA LỖI CANNOT FIND SYMBOL) ---
    private String dvhReceiverName;    // Tên người nhận
    private String dvhReceiverPhone;   // SĐT người nhận
    private String dvhReceiverAddress; // Địa chỉ nhận

    // --- SỬA LỖI INCOMPATIBLE TYPES (int -> String) ---
    private String dvhStatus;      // Trạng thái đơn (VD: "CHO_XAC_NHAN")

    // =======================================================
    // GETTER & SETTER (Bắt buộc phải có đầy đủ)
    // =======================================================

    public Long getDvhId() { return dvhId; }
    public void setDvhId(Long dvhId) { this.dvhId = dvhId; }

    public String getDvhCode() { return dvhCode; }
    public void setDvhCode(String dvhCode) { this.dvhCode = dvhCode; }

    public Long getDvhUserId() { return dvhUserId; }
    public void setDvhUserId(Long dvhUserId) { this.dvhUserId = dvhUserId; }

    public LocalDate getDvhOrderDate() { return dvhOrderDate; }
    public void setDvhOrderDate(LocalDate dvhOrderDate) { this.dvhOrderDate = dvhOrderDate; }

    public BigDecimal getDvhTotalAmount() { return dvhTotalAmount; }
    public void setDvhTotalAmount(BigDecimal dvhTotalAmount) { this.dvhTotalAmount = dvhTotalAmount; }

    // --- Getter/Setter cho các trường Người nhận ---
    public String getDvhReceiverName() { return dvhReceiverName; }
    public void setDvhReceiverName(String dvhReceiverName) { this.dvhReceiverName = dvhReceiverName; }

    public String getDvhReceiverPhone() { return dvhReceiverPhone; }
    public void setDvhReceiverPhone(String dvhReceiverPhone) { this.dvhReceiverPhone = dvhReceiverPhone; }

    public String getDvhReceiverAddress() { return dvhReceiverAddress; }
    public void setDvhReceiverAddress(String dvhReceiverAddress) { this.dvhReceiverAddress = dvhReceiverAddress; }

    public String getDvhStatus() { return dvhStatus; }
    public void setDvhStatus(String dvhStatus) { this.dvhStatus = dvhStatus; }
}