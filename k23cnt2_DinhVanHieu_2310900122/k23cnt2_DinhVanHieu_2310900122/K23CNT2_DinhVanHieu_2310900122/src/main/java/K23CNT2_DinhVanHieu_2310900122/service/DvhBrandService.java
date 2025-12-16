package K23CNT2_DinhVanHieu_2310900122.service;

import K23CNT2_DinhVanHieu_2310900122.entity.DvhBrand;
import K23CNT2_DinhVanHieu_2310900122.repository.DvhBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; // <--- BẮT BUỘC

import java.util.List;

@Service // <--- QUAN TRỌNG
public class DvhBrandService {

    private final DvhBrandRepository dvhBrandRepository;

    @Autowired
    public DvhBrandService(DvhBrandRepository dvhBrandRepository) {
        this.dvhBrandRepository = dvhBrandRepository;
    }

    // Hàm lấy danh sách hãng xe (Controller đang gọi hàm này)
    public List<DvhBrand> dvhFindAll() {
        return dvhBrandRepository.findAll();
    }
}