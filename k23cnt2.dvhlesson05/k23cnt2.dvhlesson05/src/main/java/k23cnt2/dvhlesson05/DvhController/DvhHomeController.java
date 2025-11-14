package k23cnt2.dvhlesson05.DvhController;

import k23cnt2.dvhlesson05.DvhEntity.DvhInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class DvhHomeController {

    @GetMapping("/home")
    public String dvhHome(Model model) {
        model.addAttribute("title", "Devmaster:Trang chủ");
        return "home";
    }

    @GetMapping("/about")
    public String dvhAbout(Model model) {
        model.addAttribute("title", "Devmaster:Giới thiệu");
        return "about";
    }

    @GetMapping("/contact")
    public String dvhContact(Model model) {
        model.addAttribute("title", "Devmaster:Liên hệ");
        return "contact";
    }

    @GetMapping
    public String dvhIndex() {
        return "index";
    }

    @GetMapping("/profile")
    public String Profile(Model model) {
        List<DvhInfo> profile = new ArrayList<>();

        profile.add(new DvhInfo(
                "Dinh Van Hieu Academy",
                "Van Hieu",
                "DinhVanHieu@devmaster.edu.vn",
                "https://devmaster.edu.vn"
        ));

        model.addAttribute("DinhVanHieuProfile", profile);
        return "profile";
    }
}
