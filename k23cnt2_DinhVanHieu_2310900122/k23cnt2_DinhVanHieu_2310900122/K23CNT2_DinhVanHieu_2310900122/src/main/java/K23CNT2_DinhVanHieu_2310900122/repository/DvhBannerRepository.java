package K23CNT2_DinhVanHieu_2310900122.repository;

import K23CNT2_DinhVanHieu_2310900122.entity.DvhBanner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DvhBannerRepository extends JpaRepository<DvhBanner, Long> {
    // Lấy danh sách banner đang bật (Active = true)
    List<DvhBanner> findByDvhActiveTrue();
}