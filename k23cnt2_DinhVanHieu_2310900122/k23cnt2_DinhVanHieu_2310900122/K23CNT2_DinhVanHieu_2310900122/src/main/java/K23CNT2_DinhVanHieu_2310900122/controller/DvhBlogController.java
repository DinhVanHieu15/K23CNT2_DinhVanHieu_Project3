package K23CNT2_DinhVanHieu_2310900122.controller;

import K23CNT2_DinhVanHieu_2310900122.service.DvhBlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dvh-blog")
public class DvhBlogController {

    private final DvhBlogService dvhBlogService;

    // Constructor Injection (Spring sẽ tự tìm Bean Service để tiêm vào đây)
    public DvhBlogController(DvhBlogService dvhBlogService) {
        this.dvhBlogService = dvhBlogService;
    }

    // Trang danh sách tin tức
    @GetMapping
    public String dvhListBlogs(Model model) {
        model.addAttribute("dvhBlogs", dvhBlogService.dvhFindAll());
        return "blog/dvh-blog-list"; // Trỏ đến file HTML hiển thị tin tức
    }
}