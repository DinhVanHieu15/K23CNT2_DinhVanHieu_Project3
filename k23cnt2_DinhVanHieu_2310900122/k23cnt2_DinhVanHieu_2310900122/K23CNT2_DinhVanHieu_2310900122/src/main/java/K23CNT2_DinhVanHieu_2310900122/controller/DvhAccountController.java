package K23CNT2_DinhVanHieu_2310900122.controller;

import K23CNT2_DinhVanHieu_2310900122.entity.DvhUser;
import K23CNT2_DinhVanHieu_2310900122.repository.DvhUserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/dvh-account")
public class DvhAccountController {

    @Autowired
    private DvhUserRepository dvhUserRepository;

    @PostMapping("/update-profile")
    public String dvhUpdateProfile(
            @RequestParam("dvhFullName") String fullName,
            @RequestParam("dvhEmail") String email,
            @RequestParam("dvhPhone") String phone,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        // Lấy user hiện tại từ session
        DvhUser currentUser = (DvhUser) session.getAttribute("dvhCurrentUser");

        if (currentUser != null) {
            // Cập nhật thông tin mới
            currentUser.setDvhFullName(fullName);
            currentUser.setDvhEmail(email);
            currentUser.setDvhPhone(phone);

            // Lưu xuống Database
            dvhUserRepository.save(currentUser);

            // Cập nhật lại Session để giao diện hiển thị đúng ngay lập tức
            session.setAttribute("dvhCurrentUser", currentUser);

            // Thông báo thành công
            redirectAttributes.addFlashAttribute("dvhMessage", "Cập nhật hồ sơ thành công!");
        }

        return "redirect:/dvh-profile";
    }
}