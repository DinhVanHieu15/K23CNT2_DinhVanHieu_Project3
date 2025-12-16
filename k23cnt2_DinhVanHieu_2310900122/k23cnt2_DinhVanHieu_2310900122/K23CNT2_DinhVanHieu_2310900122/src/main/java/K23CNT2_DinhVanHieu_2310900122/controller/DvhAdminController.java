package K23CNT2_DinhVanHieu_2310900122.controller;

import K23CNT2_DinhVanHieu_2310900122.entity.*;
import K23CNT2_DinhVanHieu_2310900122.repository.*;
import K23CNT2_DinhVanHieu_2310900122.service.DvhMotorbikeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/dvh-admin")
public class DvhAdminController {

    private final DvhMotorbikeService dvhMotorbikeService;
    private final DvhUserRepository dvhUserRepository;
    private final DvhOrderRepository dvhOrderRepository;
    private final DvhOrderDetailRepository dvhOrderDetailRepository;
    private final DvhBlogPostRepository dvhBlogPostRepository;
    private final DvhVoucherRepository dvhVoucherRepository;
    private final DvhBannerRepository dvhBannerRepository;

    // --- DANH SÁCH THƯƠNG HIỆU (CỐ ĐỊNH) ---
    private final List<String> BRAND_LIST = Arrays.asList(
            "Honda", "Yamaha", "Suzuki", "Piaggio", "VinFast", "Ducati", "Kawasaki", "BMW", "KTM"
    );

    public DvhAdminController(DvhMotorbikeService dvhMotorbikeService,
                              DvhUserRepository dvhUserRepository,
                              DvhOrderRepository dvhOrderRepository,
                              DvhOrderDetailRepository dvhOrderDetailRepository,
                              DvhBlogPostRepository dvhBlogPostRepository,
                              DvhVoucherRepository dvhVoucherRepository,
                              DvhBannerRepository dvhBannerRepository) {
        this.dvhMotorbikeService = dvhMotorbikeService;
        this.dvhUserRepository = dvhUserRepository;
        this.dvhOrderRepository = dvhOrderRepository;
        this.dvhOrderDetailRepository = dvhOrderDetailRepository;
        this.dvhBlogPostRepository = dvhBlogPostRepository;
        this.dvhVoucherRepository = dvhVoucherRepository;
        this.dvhBannerRepository = dvhBannerRepository;
    }

    // --- 1. DASHBOARD ---
    @GetMapping("/dashboard")
    public String dvhDashboard() {
        return "admin/dvh-admin-dashboard";
    }

    // --- 2. QUẢN LÝ SẢN PHẨM ---
    @GetMapping("/motorbikes")
    public String dvhListMotorbikes(Model model) {
        model.addAttribute("dvhMotorbikes", dvhMotorbikeService.dvhFindAll());
        return "admin/dvh-admin-motorbikes";
    }

    @GetMapping("/motorbikes/create")
    public String dvhShowCreateForm(Model model) {
        model.addAttribute("dvhMotorbike", new DvhMotorbike());
        // Gửi danh sách hãng sang view
        model.addAttribute("dvhBrandList", BRAND_LIST);
        return "admin/dvh-admin-motorbike-form";
    }

    @GetMapping("/motorbikes/edit/{id}")
    public String dvhShowEditForm(@PathVariable("id") Long id, Model model) {
        DvhMotorbike motorbike = dvhMotorbikeService.dvhGetMotorbikeById(id);
        if (motorbike != null) {
            model.addAttribute("dvhMotorbike", motorbike);
            model.addAttribute("dvhBrandList", BRAND_LIST);
            return "admin/dvh-admin-motorbike-form";
        }
        return "redirect:/dvh-admin/motorbikes";
    }

    // --- HÀM LƯU SẢN PHẨM (PHIÊN BẢN DÙNG LINK URL) ---
    // Không còn MultipartFile, chỉ nhận object thuần túy
    @PostMapping("/motorbikes/save")
    public String dvhSaveMotorbike(@ModelAttribute("dvhMotorbike") DvhMotorbike motorbike) {
        try {
            // Lưu trực tiếp vào DB, không cần xử lý file
            dvhMotorbikeService.dvhSaveMotorbike(motorbike);
            System.out.println(">>> Đã lưu sản phẩm thành công: " + motorbike.getDvhName());
        } catch (Exception e) {
            System.err.println(">>> LỖI LƯU SẢN PHẨM: " + e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/dvh-admin/motorbikes";
    }

    @GetMapping("/motorbikes/delete/{id}")
    public String dvhDeleteMotorbike(@PathVariable("id") Long id) {
        dvhMotorbikeService.dvhDeleteMotorbike(id);
        return "redirect:/dvh-admin/motorbikes";
    }

    // --- 3. QUẢN LÝ KHÁCH HÀNG ---
    @GetMapping("/customers")
    public String dvhListCustomers(Model model) {
        model.addAttribute("dvhCustomers", dvhUserRepository.findAll());
        return "admin/dvh-admin-customers";
    }

    // --- 4. QUẢN LÝ ĐƠN HÀNG ---
    @GetMapping("/orders")
    public String dvhListOrders(Model model) {
        model.addAttribute("dvhOrders", dvhOrderRepository.findAll());
        return "admin/dvh-admin-orders";
    }

    @GetMapping("/orders/view/{id}")
    public String dvhOrderDetail(@PathVariable("id") Long id, Model model) {
        DvhOrder order = dvhOrderRepository.findById(id).orElse(null);
        if (order != null) {
            List<DvhOrderDetail> details = dvhOrderDetailRepository.findByDvhOrderId(id);
            model.addAttribute("dvhOrder", order);
            model.addAttribute("dvhDetails", details);
            model.addAttribute("dvhProducts", dvhMotorbikeService.dvhFindAll());
        }
        return "admin/dvh-admin-order-detail";
    }

    @PostMapping("/orders/update-status")
    public String dvhUpdateOrderStatus(@RequestParam("id") Long id, @RequestParam("status") String status) {
        DvhOrder order = dvhOrderRepository.findById(id).orElse(null);
        if (order != null) {
            order.setDvhStatus(status);
            dvhOrderRepository.save(order);
        }
        return "redirect:/dvh-admin/orders";
    }

    @GetMapping("/orders/status/{id}/{status}")
    public String dvhUpdateStatusQuick(@PathVariable("id") Long id, @PathVariable("status") String status) {
        DvhOrder order = dvhOrderRepository.findById(id).orElse(null);
        if (order != null) {
            order.setDvhStatus(status);
            dvhOrderRepository.save(order);
        }
        return "redirect:/dvh-admin/orders";
    }

    // --- 5. TIN TỨC & BLOG ---
    @GetMapping("/blogs")
    public String dvhListBlogs(Model model) {
        model.addAttribute("dvhBlogs", dvhBlogPostRepository.findAll());
        return "admin/dvh-admin-blogs";
    }

    @GetMapping("/blogs/create")
    public String dvhCreateBlogForm(Model model) {
        model.addAttribute("dvhBlogPost", new DvhBlogPost());
        return "admin/dvh-admin-blog-form";
    }

    @GetMapping("/blogs/edit/{id}")
    public String dvhEditBlogForm(@PathVariable("id") Long id, Model model) {
        dvhBlogPostRepository.findById(id).ifPresent(blog -> model.addAttribute("dvhBlogPost", blog));
        return "admin/dvh-admin-blog-form";
    }

    @PostMapping("/blogs/save")
    public String dvhSaveBlog(@ModelAttribute("dvhBlogPost") DvhBlogPost blogPost) {
        dvhBlogPostRepository.save(blogPost);
        return "redirect:/dvh-admin/blogs";
    }

    @GetMapping("/blogs/delete/{id}")
    public String dvhDeleteBlog(@PathVariable("id") Long id) {
        dvhBlogPostRepository.deleteById(id);
        return "redirect:/dvh-admin/blogs";
    }

    // --- 6. QUẢN LÝ VOUCHER ---
    @GetMapping("/vouchers")
    public String dvhListVouchers(Model model) {
        model.addAttribute("dvhVouchers", dvhVoucherRepository.findAll());
        return "admin/dvh-admin-vouchers";
    }

    @GetMapping("/vouchers/create")
    public String dvhCreateVoucherForm(Model model) {
        model.addAttribute("dvhVoucher", new DvhVoucher());
        return "admin/dvh-admin-voucher-form";
    }

    @PostMapping("/vouchers/save")
    public String dvhSaveVoucher(@ModelAttribute("dvhVoucher") DvhVoucher voucher) {
        dvhVoucherRepository.save(voucher);
        return "redirect:/dvh-admin/vouchers";
    }

    @GetMapping("/vouchers/delete/{id}")
    public String dvhDeleteVoucher(@PathVariable("id") Long id) {
        dvhVoucherRepository.deleteById(id);
        return "redirect:/dvh-admin/vouchers";
    }

    // --- 7. QUẢN LÝ KHO HÀNG ---
    @GetMapping("/inventory")
    public String dvhInventory(Model model) {
        model.addAttribute("dvhMotorbikes", dvhMotorbikeService.dvhFindAll());
        return "admin/dvh-admin-inventory";
    }

    @PostMapping("/inventory/import")
    public String dvhImportStock(@RequestParam("id") Long id, @RequestParam("quantity") int quantity) {
        DvhMotorbike motorbike = dvhMotorbikeService.dvhGetMotorbikeById(id);
        if (motorbike != null) {
            int currentQty = motorbike.getDvhQuantity() == null ? 0 : motorbike.getDvhQuantity();
            motorbike.setDvhQuantity(currentQty + quantity);
            dvhMotorbikeService.dvhSaveMotorbike(motorbike);
        }
        return "redirect:/dvh-admin/inventory";
    }

    // --- 8. QUẢN LÝ BANNER ---
    @GetMapping("/banners")
    public String dvhListBanners(Model model) {
        model.addAttribute("dvhBanners", dvhBannerRepository.findAll());
        return "admin/dvh-admin-banners";
    }

    @GetMapping("/banners/create")
    public String dvhCreateBanner(Model model) {
        model.addAttribute("dvhBanner", new DvhBanner());
        return "admin/dvh-admin-banner-form";
    }

    @PostMapping("/banners/save")
    public String dvhSaveBanner(@ModelAttribute("dvhBanner") DvhBanner banner) {
        dvhBannerRepository.save(banner);
        return "redirect:/dvh-admin/banners";
    }

    @GetMapping("/banners/delete/{id}")
    public String dvhDeleteBanner(@PathVariable("id") Long id) {
        dvhBannerRepository.deleteById(id);
        return "redirect:/dvh-admin/banners";
    }

    @GetMapping("/banners/toggle/{id}")
    public String dvhToggleBanner(@PathVariable("id") Long id) {
        DvhBanner banner = dvhBannerRepository.findById(id).orElse(null);
        if (banner != null) {
            banner.setDvhActive(!Boolean.TRUE.equals(banner.getDvhActive()));
            dvhBannerRepository.save(banner);
        }
        return "redirect:/dvh-admin/banners";
    }

    // --- 9. QUẢN LÝ NHÂN VIÊN ---
    @GetMapping("/employees")
    public String dvhListEmployees(Model model) {
        model.addAttribute("dvhEmployees", dvhUserRepository.findAll());
        return "admin/dvh-admin-employees";
    }

    @GetMapping("/employees/create")
    public String dvhCreateEmployeeForm(Model model) {
        model.addAttribute("dvhUser", new DvhUser());
        return "admin/dvh-admin-employee-form";
    }

    @GetMapping("/employees/edit/{id}")
    public String dvhEditEmployeeForm(@PathVariable("id") Long id, Model model) {
        DvhUser user = dvhUserRepository.findById(id).orElse(null);
        if (user != null) {
            model.addAttribute("dvhUser", user);
            return "admin/dvh-admin-employee-form";
        }
        return "redirect:/dvh-admin/employees";
    }

    @PostMapping("/employees/save")
    public String dvhSaveEmployee(@ModelAttribute("dvhUser") DvhUser user) {
        dvhUserRepository.save(user);
        return "redirect:/dvh-admin/employees";
    }

    @GetMapping("/employees/delete/{id}")
    public String dvhDeleteEmployee(@PathVariable("id") Long id) {
        dvhUserRepository.deleteById(id);
        return "redirect:/dvh-admin/employees";
    }
}