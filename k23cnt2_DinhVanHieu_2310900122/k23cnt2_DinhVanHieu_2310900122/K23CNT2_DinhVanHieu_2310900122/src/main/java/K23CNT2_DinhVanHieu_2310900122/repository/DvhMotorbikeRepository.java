package K23CNT2_DinhVanHieu_2310900122.repository;

import K23CNT2_DinhVanHieu_2310900122.entity.DvhMotorbike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface DvhMotorbikeRepository extends JpaRepository<DvhMotorbike, Long> {

    List<DvhMotorbike> findByDvhNameContainingIgnoreCase(String name);

    // --- CẬP NHẬT QUERY LỌC HÃNG (String) ---
    @Query("SELECT m FROM DvhMotorbike m WHERE " +
            "(:keyword IS NULL OR m.dvhName LIKE %:keyword% OR m.dvhCode LIKE %:keyword%) " +
            "AND (:type IS NULL OR m.dvhType = :type) " +
            "AND (:brand IS NULL OR m.dvhBrand = :brand) " + // Lọc theo tên hãng
            "AND (:minPrice IS NULL OR m.dvhPrice >= :minPrice) " +
            "AND (:maxPrice IS NULL OR m.dvhPrice <= :maxPrice)")
    List<DvhMotorbike> findByFilters(@Param("keyword") String keyword,
                                     @Param("type") String type,
                                     @Param("brand") String brand, // Nhận chuỗi
                                     @Param("minPrice") BigDecimal minPrice,
                                     @Param("maxPrice") BigDecimal maxPrice);
}