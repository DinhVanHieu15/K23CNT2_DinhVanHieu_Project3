package K23CNT2_DinhVanHieu_2310900122.repository;

import K23CNT2_DinhVanHieu_2310900122.entity.DvhUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DvhUserRepository extends JpaRepository<DvhUser, Long> {
    // Tìm theo tên đăng nhập (cho chức năng Login)
    java.util.Optional<DvhUser> findByDvhUsername(String username);

    // --- THÊM: Tìm danh sách theo Role (để lọc Nhân viên) ---
    // Tìm những user có role là ADMIN hoặc STAFF
    List<DvhUser> findByDvhRoleIn(List<String> roles);
}