package K23CNT2_DinhVanHieu_2310900122.repository;

import K23CNT2_DinhVanHieu_2310900122.entity.DvhBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DvhBrandRepository extends JpaRepository<DvhBrand, Long> {
}