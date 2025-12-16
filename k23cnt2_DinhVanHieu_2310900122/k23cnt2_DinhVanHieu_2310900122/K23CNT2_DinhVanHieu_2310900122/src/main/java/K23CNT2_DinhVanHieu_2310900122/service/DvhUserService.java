package K23CNT2_DinhVanHieu_2310900122.service;

import K23CNT2_DinhVanHieu_2310900122.dto.DvhRegisterRequest;
import K23CNT2_DinhVanHieu_2310900122.entity.DvhUser;
import K23CNT2_DinhVanHieu_2310900122.repository.DvhUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DvhUserService {

    private final DvhUserRepository dvhUserRepository;

    @Autowired
    public DvhUserService(DvhUserRepository dvhUserRepository) {
        this.dvhUserRepository = dvhUserRepository;
    }

    // --- 1. ĐĂNG NHẬP ---
    public Optional<DvhUser> dvhLogin(String username, String password) {
        Optional<DvhUser> userOpt = dvhUserRepository.findByDvhUsername(username);
        // Lưu ý: Đây là so sánh mật khẩu thô (chưa mã hóa) cho đơn giản
        if (userOpt.isPresent() && userOpt.get().getDvhPassword().equals(password)) {
            return userOpt;
        }
        return Optional.empty();
    }

    // --- 2. ĐĂNG KÝ ---
    public DvhUser dvhRegister(DvhRegisterRequest request, boolean isAdmin) {
        DvhUser newUser = new DvhUser();
        newUser.setDvhUsername(request.getDvhUsername());
        newUser.setDvhPassword(request.getDvhPassword());
        newUser.setDvhFullName(request.getDvhFullName());
        newUser.setDvhEmail(request.getDvhEmail());
        newUser.setDvhPhone(request.getDvhPhone());

        // Set quyền
        newUser.setDvhRole(isAdmin ? "ADMIN" : "USER");

        return dvhUserRepository.save(newUser);
    }

    // --- 3. CẬP NHẬT THÔNG TIN USER (HÀM BẠN ĐANG THIẾU) ---
    // Đây là hàm quan trọng để sửa lỗi "cannot find symbol"
    public void dvhUpdateUser(DvhUser user) {
        dvhUserRepository.save(user);
    }
}