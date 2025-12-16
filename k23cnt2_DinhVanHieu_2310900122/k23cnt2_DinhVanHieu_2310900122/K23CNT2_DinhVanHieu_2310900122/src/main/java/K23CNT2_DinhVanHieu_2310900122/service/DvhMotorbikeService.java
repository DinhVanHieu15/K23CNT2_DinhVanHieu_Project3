package K23CNT2_DinhVanHieu_2310900122.service;

import K23CNT2_DinhVanHieu_2310900122.dto.DvhMotorbikeSearchRequest;
import K23CNT2_DinhVanHieu_2310900122.entity.DvhMotorbike;
import K23CNT2_DinhVanHieu_2310900122.repository.DvhMotorbikeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DvhMotorbikeService {

    private final DvhMotorbikeRepository dvhMotorbikeRepository;

    public DvhMotorbikeService(DvhMotorbikeRepository dvhMotorbikeRepository) {
        this.dvhMotorbikeRepository = dvhMotorbikeRepository;
    }

    // Lấy tất cả sản phẩm
    public List<DvhMotorbike> dvhFindAll() {
        return dvhMotorbikeRepository.findAll();
    }

    // Lấy sản phẩm theo ID
    public DvhMotorbike dvhGetMotorbikeById(Long id) {
        return dvhMotorbikeRepository.findById(id).orElse(null);
    }

    // Lưu sản phẩm
    public void dvhSaveMotorbike(DvhMotorbike motorbike) {
        dvhMotorbikeRepository.save(motorbike);
    }

    // Xóa sản phẩm
    public void dvhDeleteMotorbike(Long id) {
        dvhMotorbikeRepository.deleteById(id);
    }

    // ========================================================================
    // HÀM TÌM KIẾM VÀ LỌC ĐA NĂNG (ĐÃ CẬP NHẬT LỌC HÃNG)
    // ========================================================================
    public List<DvhMotorbike> dvhSearch(DvhMotorbikeSearchRequest request) {
        // 1. Chuẩn hóa tham số (Nếu là chuỗi rỗng thì coi là null để query bỏ qua)
        String keyword = (request.getDvhKeyword() == null || request.getDvhKeyword().isEmpty()) ? null : request.getDvhKeyword();
        String type = (request.getDvhType() == null || request.getDvhType().isEmpty()) ? null : request.getDvhType();

        // --- QUAN TRỌNG: Lấy tham số Hãng xe (String) ---
        String brand = (request.getDvhBrand() == null || request.getDvhBrand().isEmpty()) ? null : request.getDvhBrand();

        BigDecimal minPrice = null;
        BigDecimal maxPrice = null;

        // 2. Xử lý khoảng giá (Chuyển chuỗi "20-50" thành BigDecimal)
        if (request.getDvhPriceRange() != null) {
            switch (request.getDvhPriceRange()) {
                case "under-20": // Dưới 20 triệu
                    maxPrice = new BigDecimal("20000000");
                    break;
                case "20-50":    // 20 - 50 triệu
                    minPrice = new BigDecimal("20000000");
                    maxPrice = new BigDecimal("50000000");
                    break;
                case "50-100":   // 50 - 100 triệu
                    minPrice = new BigDecimal("50000000");
                    maxPrice = new BigDecimal("100000000");
                    break;
                case "over-100": // Trên 100 triệu
                    minPrice = new BigDecimal("100000000");
                    break;
            }
        }

        // 3. Gọi hàm lọc đa năng trong Repository (truyền đủ 5 tham số)
        return dvhMotorbikeRepository.findByFilters(keyword, type, brand, minPrice, maxPrice);
    }
}