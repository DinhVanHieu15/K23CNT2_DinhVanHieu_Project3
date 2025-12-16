package K23CNT2_DinhVanHieu_2310900122.repository;

import K23CNT2_DinhVanHieu_2310900122.entity.DvhVoucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DvhVoucherRepository extends JpaRepository<DvhVoucher, Long> {

    // --- KHAI BÁO HÀM TÌM KIẾM THEO MÃ CODE ---
    // Spring Boot sẽ tự động tạo câu lệnh SQL: SELECT * FROM dvh_voucher WHERE dvh_code = ?
    DvhVoucher findByDvhCode(String dvhCode);

}