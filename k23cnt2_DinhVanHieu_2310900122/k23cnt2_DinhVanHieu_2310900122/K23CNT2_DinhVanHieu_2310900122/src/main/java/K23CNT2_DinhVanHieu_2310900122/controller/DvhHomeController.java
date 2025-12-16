package K23CNT2_DinhVanHieu_2310900122.controller;

import K23CNT2_DinhVanHieu_2310900122.dto.DvhMotorbikeSearchRequest;
import K23CNT2_DinhVanHieu_2310900122.dto.DvhRegisterRequest;
import K23CNT2_DinhVanHieu_2310900122.entity.*;
import K23CNT2_DinhVanHieu_2310900122.repository.*;
import K23CNT2_DinhVanHieu_2310900122.service.DvhBrandService;
import K23CNT2_DinhVanHieu_2310900122.service.DvhMotorbikeService;
import K23CNT2_DinhVanHieu_2310900122.service.DvhUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Sort; // Nhớ import Sort
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class DvhHomeController {

    // --- KHAI BÁO SERVICE & REPOSITORY ---
    private final DvhMotorbikeService dvhMotorbikeService;
    private final DvhBrandService dvhBrandService;
    private final DvhUserService dvhUserService;

    // Thêm Repository này để gọi hàm sắp xếp (Sort)
    private final DvhMotorbikeRepository dvhMotorbikeRepository;

    private final DvhVoucherRepository dvhVoucherRepository;
    private final DvhBlogPostRepository dvhBlogPostRepository;
    private final DvhBannerRepository dvhBannerRepository;
    private final DvhOrderRepository dvhOrderRepository;

    // --- DANH SÁCH HÃNG ĐỂ LỌC ---
    private final List<String> BRAND_LIST = Arrays.asList(
            "Honda", "Yamaha", "Suzuki", "Piaggio", "VinFast", "Ducati", "Kawasaki", "BMW", "KTM"
    );

    public DvhHomeController(DvhMotorbikeService dvhMotorbikeService,
                             DvhBrandService dvhBrandService,
                             DvhUserService dvhUserService,
                             DvhMotorbikeRepository dvhMotorbikeRepository, // Inject thêm vào đây
                             DvhVoucherRepository dvhVoucherRepository,
                             DvhBlogPostRepository dvhBlogPostRepository,
                             DvhBannerRepository dvhBannerRepository,
                             DvhOrderRepository dvhOrderRepository) {
        this.dvhMotorbikeService = dvhMotorbikeService;
        this.dvhBrandService = dvhBrandService;
        this.dvhUserService = dvhUserService;
        this.dvhMotorbikeRepository = dvhMotorbikeRepository;
        this.dvhVoucherRepository = dvhVoucherRepository;
        this.dvhBlogPostRepository = dvhBlogPostRepository;
        this.dvhBannerRepository = dvhBannerRepository;
        this.dvhOrderRepository = dvhOrderRepository;
    }

    // ========================================================================
    // 1. TRANG CHỦ (CÓ LOGIC SẮP XẾP CHO 3 NÚT)
    // ========================================================================
    @GetMapping("/")
    public String dvhHome(@RequestParam(value = "dvhKeyword", required = false) String dvhKeyword,
                          @RequestParam(value = "sort", defaultValue = "new") String sort, // Nhận tham số sắp xếp
                          Model dvhModel) {

        List<DvhMotorbike> motorbikes;

        if (dvhKeyword != null && !dvhKeyword.isEmpty()) {
            // Nếu có từ khóa tìm kiếm -> Ưu tiên tìm kiếm
            DvhMotorbikeSearchRequest dvhRequest = new DvhMotorbikeSearchRequest();
            dvhRequest.setDvhKeyword(dvhKeyword);
            motorbikes = dvhMotorbikeService.dvhSearch(dvhRequest);
        } else {
            // Logic sắp xếp cho 3 nút
            if ("price".equals(sort)) {
                // Giá tốt: Sắp xếp giá tăng dần (Rẻ -> Đắt)
                motorbikes = dvhMotorbikeRepository.findAll(Sort.by(Sort.Direction.ASC, "dvhPrice"));
            } else if ("best".equals(sort)) {
                // Bán chạy: Tạm thời sắp xếp theo Tên A-Z (hoặc logic tùy chỉnh)
                motorbikes = dvhMotorbikeRepository.findAll(Sort.by(Sort.Direction.ASC, "dvhName"));
            } else {
                // Mặc định (New): Sắp xếp ID giảm dần (Mới nhất lên đầu)
                motorbikes = dvhMotorbikeRepository.findAll(Sort.by(Sort.Direction.DESC, "dvhId"));
            }
        }

        dvhModel.addAttribute("dvhMotorbikes", motorbikes);
        dvhModel.addAttribute("dvhBrands", dvhBrandService.dvhFindAll());
        dvhModel.addAttribute("dvhBanners", dvhBannerRepository.findByDvhActiveTrue());
        dvhModel.addAttribute("sort", sort); // Gửi trạng thái sort về view để active nút
        dvhModel.addAttribute("dvhKeyword", dvhKeyword);

        return "home/dvh-home";
    }

    // ========================================================================
    // 2. SẢN PHẨM
    // ========================================================================
    @GetMapping("/dvh-products")
    public String dvhAllProducts(@RequestParam(value = "type", required = false) String type,
                                 @RequestParam(value = "priceRange", required = false) String priceRange,
                                 @RequestParam(value = "brand", required = false) String brand,
                                 @RequestParam(value = "keyword", required = false) String keyword,
                                 Model model) {
        DvhMotorbikeSearchRequest request = new DvhMotorbikeSearchRequest();
        request.setDvhType(type);
        request.setDvhPriceRange(priceRange);
        request.setDvhBrand(brand);
        request.setDvhKeyword(keyword);

        model.addAttribute("dvhMotorbikes", dvhMotorbikeService.dvhSearch(request));
        model.addAttribute("dvhBrandList", BRAND_LIST);
        model.addAttribute("selectedType", type);
        model.addAttribute("selectedPrice", priceRange);
        model.addAttribute("selectedBrand", brand);

        return "product/dvh-products";
    }

    @GetMapping("/dvh-products/detail/{id}")
    public String dvhProductDetail(@PathVariable Long id, Model model) {
        DvhMotorbike motorbike = dvhMotorbikeService.dvhGetMotorbikeById(id);
        if (motorbike == null) return "redirect:/dvh-products";
        model.addAttribute("dvhProduct", motorbike);
        return "product/dvh-product-detail";
    }

    // ========================================================================
    // 3. TIN TỨC (BLOG) - TRẢ VỀ FOLDER 'OTHER'
    // ========================================================================
    @GetMapping("/dvh-blogs")
    public String dvhBlogs(Model model) {
        model.addAttribute("dvhBlogs", dvhBlogPostRepository.findAll());
        return "other/dvh-blogs"; // Folder 'other'
    }

    @GetMapping("/dvh-blogs/detail/{id}")
    public String dvhBlogDetail(@PathVariable Long id, Model model) {
        DvhBlogPost blog = dvhBlogPostRepository.findById(id).orElse(null);
        if (blog != null) {
            model.addAttribute("dvhBlogPost", blog);
            return "other/dvh-blog-detail"; // Folder 'other'
        }
        return "redirect:/dvh-blogs";
    }

    // ========================================================================
    // 4. CÁC TRANG KHÁC
    // ========================================================================
    @GetMapping("/dvh-promotions")
    public String dvhPromotions(Model model) {
        model.addAttribute("dvhVouchers", dvhVoucherRepository.findAll());
        return "other/dvh-promotions";
    }

    @GetMapping("/dvh-orders")
    public String dvhViewOrders(HttpSession session, Model model) {
        DvhUser currentUser = (DvhUser) session.getAttribute("dvhCurrentUser");
        if (currentUser == null) return "redirect:/dvh-login/dvh-login";
        model.addAttribute("dvhOrders", dvhOrderRepository.findByDvhUserIdOrderByDvhOrderDateDesc(currentUser.getDvhId()));
        return "cart/dvh-orders";
    }

    // ========================================================================
    // 5. LOGIN / REGISTER / LOGOUT
    // ========================================================================
    @GetMapping("/dvh-login/dvh-login") public String dvhLoginForm() { return "login/dvh-login"; }

    @PostMapping("/dvh-login/dvh-login")
    public String dvhDoLogin(@RequestParam("dvhUsername") String u, @RequestParam("dvhPassword") String p, HttpSession s, Model m) {
        Optional<DvhUser> user = dvhUserService.dvhLogin(u, p);
        if (user.isEmpty()) {
            m.addAttribute("dvhError", "Sai tài khoản hoặc mật khẩu!");
            return "login/dvh-login";
        }
        s.setAttribute("dvhCurrentUser", user.get());
        if ("ADMIN".equals(user.get().getDvhRole()) || "STAFF".equals(user.get().getDvhRole())) {
            return "redirect:/dvh-admin/dashboard";
        }
        return "redirect:/";
    }

    @GetMapping("/dvh-login/dvh-logout") public String dvhLogout(HttpSession s) { s.invalidate(); return "redirect:/"; }

    @GetMapping("/dvh-login/dvh-register")
    public String r(Model m) {
        m.addAttribute("dvhRegisterRequest", new DvhRegisterRequest());
        return "login/dvh-register";
    }

    @PostMapping("/dvh-login/dvh-register")
    public String dr(@ModelAttribute DvhRegisterRequest r) {
        dvhUserService.dvhRegister(r, false);
        return "redirect:/dvh-login/dvh-login";
    }

    @GetMapping("/dvh-profile")
    public String p(HttpSession s) {
        return s.getAttribute("dvhCurrentUser") == null ? "redirect:/dvh-login/dvh-login" : "login/dvh-profile";
    }

    // ========================================================================
    // 6. TRANG TĨNH & LIÊN HỆ
    // ========================================================================
    @GetMapping("/dvh-services") public String s() { return "other/dvh-services"; }
    @GetMapping("/dvh-parts") public String pa() { return "other/dvh-parts"; }
    @GetMapping("/dvh-contact") public String c() { return "other/dvh-contact"; }
    @PostMapping("/dvh-contact/send") public String cs(RedirectAttributes r) { r.addFlashAttribute("dvhMessage", "Đã gửi!"); return "redirect:/dvh-contact"; }
}