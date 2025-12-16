package K23CNT2_DinhVanHieu_2310900122.repository;

import K23CNT2_DinhVanHieu_2310900122.entity.DvhOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DvhOrderRepository extends JpaRepository<DvhOrder, Long> {
    // Tìm đơn hàng theo User ID và sắp xếp ngày giảm dần (Mới nhất lên đầu)
    List<DvhOrder> findByDvhUserIdOrderByDvhOrderDateDesc(Long dvhUserId);

    // Tìm theo mã đơn hàng (nếu cần)
    DvhOrder findByDvhCode(String dvhCode);
}