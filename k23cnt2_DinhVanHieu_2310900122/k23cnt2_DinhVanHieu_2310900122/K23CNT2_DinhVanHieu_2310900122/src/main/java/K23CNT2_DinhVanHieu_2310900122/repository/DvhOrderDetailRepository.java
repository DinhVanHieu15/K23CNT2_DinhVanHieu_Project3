package K23CNT2_DinhVanHieu_2310900122.repository;

import K23CNT2_DinhVanHieu_2310900122.entity.DvhOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DvhOrderDetailRepository extends JpaRepository<DvhOrderDetail, Long> {
    // Tìm các sản phẩm thuộc về một đơn hàng cụ thể
    List<DvhOrderDetail> findByDvhOrderId(Long dvhOrderId);
}