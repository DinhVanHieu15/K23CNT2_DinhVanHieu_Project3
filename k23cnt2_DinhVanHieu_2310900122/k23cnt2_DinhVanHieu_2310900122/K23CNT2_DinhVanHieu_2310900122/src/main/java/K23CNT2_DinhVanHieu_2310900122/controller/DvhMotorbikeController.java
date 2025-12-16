package K23CNT2_DinhVanHieu_2310900122.controller;

import K23CNT2_DinhVanHieu_2310900122.entity.DvhMotorbike;
import K23CNT2_DinhVanHieu_2310900122.service.DvhMotorbikeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dvh-motorbikes")
public class DvhMotorbikeController {

    private final DvhMotorbikeService dvhMotorbikeService;

    public DvhMotorbikeController(DvhMotorbikeService dvhMotorbikeService) {
        this.dvhMotorbikeService = dvhMotorbikeService;
    }

    // --- Trang danh sách sản phẩm (nếu cần) ---
    @GetMapping
    public String dvhList(Model model) {
        model.addAttribute("dvhMotorbikes", dvhMotorbikeService.dvhFindAll());
        return "motorbike/dvh-motorbike-list"; // Đảm bảo bạn có file html này hoặc đổi đường dẫn
    }

    // --- Trang chi tiết sản phẩm ---
    @GetMapping("/detail/{id}")
    public String dvhDetail(@PathVariable("id") Long dvhId, Model dvhModel) {
        // SỬA LỖI TẠI ĐÂY:
        // Đổi từ dvhFindById -> dvhGetMotorbikeById (cho khớp với Service)
        DvhMotorbike dvhMotorbike = dvhMotorbikeService.dvhGetMotorbikeById(dvhId);

        dvhModel.addAttribute("dvhMotorbike", dvhMotorbike);
        return "motorbike/dvh-motorbike-detail"; // Trỏ về file html chi tiết
    }
}