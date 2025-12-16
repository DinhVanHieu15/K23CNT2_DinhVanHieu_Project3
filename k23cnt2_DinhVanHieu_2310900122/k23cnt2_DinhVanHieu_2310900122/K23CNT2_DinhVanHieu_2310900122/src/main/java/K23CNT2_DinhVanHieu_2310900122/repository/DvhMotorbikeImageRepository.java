package K23CNT2_DinhVanHieu_2310900122.repository;

import K23CNT2_DinhVanHieu_2310900122.entity.DvhMotorbikeImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DvhMotorbikeImageRepository extends JpaRepository<DvhMotorbikeImage, Long> {

    // SỬA LẠI TÊN PHƯƠNG THỨC Ở ĐÂY
    // Thay vì findByDvhMotorbike_DvhMotorbikeId -> findByDvhMotorbike_DvhId
    // Spring Data JPA sẽ hiểu: Tìm trong DvhMotorbikeImage -> trường dvhMotorbike -> trường dvhId
    List<DvhMotorbikeImage> findByDvhMotorbike_DvhId(Long dvhId);
}