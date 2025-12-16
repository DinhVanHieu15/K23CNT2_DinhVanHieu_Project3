package K23CNT2_DinhVanHieu_2310900122.controller;

import K23CNT2_DinhVanHieu_2310900122.dto.DvhCartItem;
import K23CNT2_DinhVanHieu_2310900122.entity.DvhMotorbike;
import K23CNT2_DinhVanHieu_2310900122.entity.DvhVoucher;
import K23CNT2_DinhVanHieu_2310900122.repository.DvhVoucherRepository;
import K23CNT2_DinhVanHieu_2310900122.service.DvhMotorbikeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dvh-cart")
public class DvhCartController {

    private final DvhMotorbikeService dvhMotorbikeService;
    private final DvhVoucherRepository dvhVoucherRepository;

    public DvhCartController(DvhMotorbikeService dvhMotorbikeService, DvhVoucherRepository dvhVoucherRepository) {
        this.dvhMotorbikeService = dvhMotorbikeService;
        this.dvhVoucherRepository = dvhVoucherRepository;
    }

    // ============================================================
    // 1. HIỂN THỊ GIỎ HÀNG
    // ============================================================
    @GetMapping
    public String dvhShowCart(HttpSession session, Model model) {
        List<DvhCartItem> cart = (List<DvhCartItem>) session.getAttribute("dvhCart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        // A. Tính tổng tiền hàng (Tạm tính)
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (DvhCartItem item : cart) {
            if (item.getDvhTotalPrice() != null) {
                totalAmount = totalAmount.add(item.getDvhTotalPrice());
            }
        }

        // B. Xử lý Voucher (Nếu đã áp dụng)
        BigDecimal discountAmount = BigDecimal.ZERO;
        DvhVoucher voucher = (DvhVoucher) session.getAttribute("dvhAppliedVoucher");

        if (voucher != null) {
            // Giả sử logic giảm giá là 10% (hoặc lấy từ DB nếu có trường discount)
            BigDecimal discountPercent = new BigDecimal("0.1"); // 10%
            discountAmount = totalAmount.multiply(discountPercent);
        }

        // C. Tính tổng thanh toán cuối cùng
        BigDecimal finalTotal = totalAmount.subtract(discountAmount);
        if (finalTotal.compareTo(BigDecimal.ZERO) < 0) {
            finalTotal = BigDecimal.ZERO;
        }

        // D. Gửi dữ liệu sang View
        model.addAttribute("dvhCart", cart);
        model.addAttribute("dvhTotalAmount", totalAmount); // Tạm tính
        model.addAttribute("dvhDiscount", discountAmount); // Tiền giảm
        model.addAttribute("dvhFinalTotal", finalTotal);   // Tổng thanh toán
        model.addAttribute("dvhVoucher", voucher);         // Voucher hiện tại

        return "cart/dvh-cart";
    }

    // ============================================================
    // 2. XỬ LÝ ÁP DỤNG VOUCHER (ĐÃ FIX LỖI OPTIONAL)
    // ============================================================
    @PostMapping("/apply-voucher")
    public String dvhApplyVoucher(@RequestParam("voucherCode") String code, HttpSession session) {
        // --- SỬA LỖI: Hứng trực tiếp DvhVoucher (vì Repo trả về DvhVoucher) ---
        DvhVoucher voucher = dvhVoucherRepository.findByDvhCode(code);

        if (voucher != null) {
            session.setAttribute("dvhAppliedVoucher", voucher);
        } else {
            // Nếu không tìm thấy trong DB, tạo voucher ảo để test hiển thị
            // (Bạn có thể xóa phần else này nếu muốn logic chặt chẽ hơn)
            DvhVoucher mockVoucher = new DvhVoucher();
            mockVoucher.setDvhCode(code);
            session.setAttribute("dvhAppliedVoucher", mockVoucher);
        }

        return "redirect:/dvh-cart";
    }

    // ============================================================
    // 3. GỠ VOUCHER
    // ============================================================
    @GetMapping("/remove-voucher")
    public String dvhRemoveVoucher(HttpSession session) {
        session.removeAttribute("dvhAppliedVoucher");
        return "redirect:/dvh-cart";
    }

    // ============================================================
    // 4. THÊM VÀO GIỎ
    // ============================================================
    @GetMapping("/add/{id}")
    public String dvhAddToCart(@PathVariable Long id, HttpSession session) {
        List<DvhCartItem> cart = (List<DvhCartItem>) session.getAttribute("dvhCart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        boolean found = false;
        for (DvhCartItem item : cart) {
            if (item.getDvhId().equals(id)) {
                item.setDvhQuantity(item.getDvhQuantity() + 1);
                found = true;
                break;
            }
        }

        if (!found) {
            DvhMotorbike motorbike = dvhMotorbikeService.dvhGetMotorbikeById(id);
            if (motorbike != null) {
                cart.add(new DvhCartItem(
                        motorbike.getDvhId(),
                        motorbike.getDvhName(),
                        motorbike.getDvhImage(),
                        motorbike.getDvhPrice(),
                        1
                ));
            }
        }
        session.setAttribute("dvhCart", cart);
        return "redirect:/dvh-cart";
    }

    // ============================================================
    // 5. XÓA KHỎI GIỎ
    // ============================================================
    @GetMapping("/remove/{id}")
    public String dvhRemoveFromCart(@PathVariable Long id, HttpSession session) {
        List<DvhCartItem> cart = (List<DvhCartItem>) session.getAttribute("dvhCart");
        if (cart != null) {
            cart.removeIf(item -> item.getDvhId().equals(id));
            session.setAttribute("dvhCart", cart);
        }
        return "redirect:/dvh-cart";
    }

    // ============================================================
    // 6. XÓA TẤT CẢ
    // ============================================================
    @GetMapping("/clear")
    public String dvhClearCart(HttpSession session) {
        session.removeAttribute("dvhCart");
        session.removeAttribute("dvhAppliedVoucher"); // Xóa luôn voucher khi làm trống giỏ
        return "redirect:/dvh-cart";
    }
}