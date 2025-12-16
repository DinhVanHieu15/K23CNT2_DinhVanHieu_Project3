package K23CNT2_DinhVanHieu_2310900122.controller;

import K23CNT2_DinhVanHieu_2310900122.dto.DvhCartItem;
import K23CNT2_DinhVanHieu_2310900122.entity.*;
import K23CNT2_DinhVanHieu_2310900122.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class DvhCheckoutController {

    private final DvhOrderRepository dvhOrderRepository;
    private final DvhOrderDetailRepository dvhOrderDetailRepository;
    private final DvhMotorbikeRepository dvhMotorbikeRepository;

    public DvhCheckoutController(DvhOrderRepository dvhOrderRepository,
                                 DvhOrderDetailRepository dvhOrderDetailRepository,
                                 DvhMotorbikeRepository dvhMotorbikeRepository) {
        this.dvhOrderRepository = dvhOrderRepository;
        this.dvhOrderDetailRepository = dvhOrderDetailRepository;
        this.dvhMotorbikeRepository = dvhMotorbikeRepository;
    }

    // ============================================================
    // 1. HIỂN THỊ TRANG THANH TOÁN (CÓ TÍNH GIẢM GIÁ VOUCHER)
    // ============================================================
    @GetMapping("/dvh-checkout")
    public String dvhShowCheckout(HttpSession session, Model model) {
        // Kiểm tra đăng nhập
        DvhUser user = (DvhUser) session.getAttribute("dvhCurrentUser");
        if (user == null) return "redirect:/dvh-login/dvh-login";

        // Lấy giỏ hàng
        List<DvhCartItem> cart = (List<DvhCartItem>) session.getAttribute("dvhCart");
        if (cart == null || cart.isEmpty()) return "redirect:/dvh-cart";

        // --- BƯỚC 1: TÍNH TỔNG TIỀN HÀNG (TẠM TÍNH) ---
        BigDecimal subTotal = BigDecimal.ZERO;
        for (DvhCartItem item : cart) {
            if (item.getDvhTotalPrice() != null) {
                subTotal = subTotal.add(item.getDvhTotalPrice());
            }
        }

        // --- BƯỚC 2: TÍNH GIẢM GIÁ (NẾU CÓ VOUCHER) ---
        BigDecimal discountAmount = BigDecimal.ZERO;
        DvhVoucher voucher = (DvhVoucher) session.getAttribute("dvhAppliedVoucher");
        if (voucher != null) {
            // Ví dụ: Giảm 10% (Logic này phải khớp với bên CartController)
            discountAmount = subTotal.multiply(new BigDecimal("0.1"));
        }

        // --- BƯỚC 3: TÍNH TỔNG THANH TOÁN CUỐI CÙNG ---
        BigDecimal finalTotal = subTotal.subtract(discountAmount);
        // Đảm bảo không âm tiền
        if (finalTotal.compareTo(BigDecimal.ZERO) < 0) finalTotal = BigDecimal.ZERO;

        // Gửi dữ liệu sang View
        model.addAttribute("dvhUser", user);
        model.addAttribute("dvhCart", cart);

        // Gửi đủ 3 biến để hiển thị chi tiết hóa đơn
        model.addAttribute("dvhSubTotal", subTotal);     // Tạm tính
        model.addAttribute("dvhDiscount", discountAmount); // Tiền giảm
        model.addAttribute("dvhFinalTotal", finalTotal);   // Tổng phải trả (đã trừ giảm giá)

        // Gửi thêm biến dvhTotal để tương thích với HTML cũ (nếu có dùng)
        model.addAttribute("dvhTotal", finalTotal);

        return "cart/dvh-checkout";
    }

    // ============================================================
    // 2. XỬ LÝ ĐẶT HÀNG (LƯU GIÁ ĐÃ GIẢM VÀO DATABASE)
    // ============================================================
    @PostMapping("/dvh-checkout/place-order")
    public String dvhPlaceOrder(
            @RequestParam("name") String name,
            @RequestParam("phone") String phone,
            @RequestParam("address") String address,
            @RequestParam(value = "paymentMethod", defaultValue = "CASH") String paymentMethod,
            HttpSession session) {

        DvhUser user = (DvhUser) session.getAttribute("dvhCurrentUser");
        List<DvhCartItem> cart = (List<DvhCartItem>) session.getAttribute("dvhCart");

        if (user != null && cart != null && !cart.isEmpty()) {
            DvhOrder order = new DvhOrder();
            order.setDvhUserId(user.getDvhId());
            order.setDvhReceiverName(name);
            order.setDvhReceiverPhone(phone);
            order.setDvhReceiverAddress(address);
            order.setDvhOrderDate(LocalDate.now());
            order.setDvhStatus("CHO_XAC_NHAN");
            order.setDvhCode("DH" + System.currentTimeMillis());

            // --- TÍNH TOÁN LẠI TIỀN ĐỂ LƯU CHÍNH XÁC ---
            BigDecimal subTotal = BigDecimal.ZERO;
            for (DvhCartItem item : cart) {
                subTotal = subTotal.add(item.getDvhTotalPrice());
            }

            BigDecimal discountAmount = BigDecimal.ZERO;
            DvhVoucher voucher = (DvhVoucher) session.getAttribute("dvhAppliedVoucher");
            if (voucher != null) {
                discountAmount = subTotal.multiply(new BigDecimal("0.1")); // Giảm 10%
            }

            BigDecimal finalTotal = subTotal.subtract(discountAmount);
            if (finalTotal.compareTo(BigDecimal.ZERO) < 0) finalTotal = BigDecimal.ZERO;
            // -------------------------------------------

            // Lưu số tiền thực tế khách phải trả
            order.setDvhTotalAmount(finalTotal);

            DvhOrder savedOrder = dvhOrderRepository.save(order);

            // Lưu chi tiết đơn hàng & Trừ kho
            for (DvhCartItem item : cart) {
                DvhOrderDetail detail = new DvhOrderDetail();
                detail.setDvhOrderId(savedOrder.getDvhId());
                detail.setDvhProductId(item.getDvhId());
                detail.setDvhQuantity(item.getDvhQuantity());
                detail.setDvhPrice(item.getDvhPrice());
                dvhOrderDetailRepository.save(detail);

                // Trừ tồn kho
                DvhMotorbike motorbike = dvhMotorbikeRepository.findById(item.getDvhId()).orElse(null);
                if (motorbike != null) {
                    int currentStock = motorbike.getDvhQuantity() == null ? 0 : motorbike.getDvhQuantity();
                    int newStock = Math.max(currentStock - item.getDvhQuantity(), 0);
                    motorbike.setDvhQuantity(newStock);
                    dvhMotorbikeRepository.save(motorbike);
                }
            }

            // Xóa Giỏ hàng và Voucher sau khi đặt xong
            session.removeAttribute("dvhCart");
            session.removeAttribute("dvhAppliedVoucher");

            return "redirect:/dvh-order-success";
        }

        return "redirect:/dvh-cart";
    }

    // ============================================================
    // 3. TRANG THÔNG BÁO ĐẶT HÀNG THÀNH CÔNG
    // ============================================================
    @GetMapping("/dvh-order-success")
    public String dvhOrderSuccess() {
        return "cart/dvh-order-success";
    }
}