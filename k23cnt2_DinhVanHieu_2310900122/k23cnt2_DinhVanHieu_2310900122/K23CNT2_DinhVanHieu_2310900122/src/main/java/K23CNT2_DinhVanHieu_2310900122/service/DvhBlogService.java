package K23CNT2_DinhVanHieu_2310900122.service;

import K23CNT2_DinhVanHieu_2310900122.entity.DvhBlogPost;
import K23CNT2_DinhVanHieu_2310900122.repository.DvhBlogPostRepository;
import org.springframework.stereotype.Service; // <--- BẮT BUỘC PHẢI CÓ
import java.util.List;
import java.util.Optional;

@Service // <--- QUAN TRỌNG: Đánh dấu đây là Service để Spring quản lý
public class DvhBlogService {

    private final DvhBlogPostRepository dvhBlogPostRepository;

    public DvhBlogService(DvhBlogPostRepository dvhBlogPostRepository) {
        this.dvhBlogPostRepository = dvhBlogPostRepository;
    }

    // 1. Lấy tất cả bài viết
    public List<DvhBlogPost> dvhFindAll() {
        return dvhBlogPostRepository.findAll();
    }

    // 2. Tìm bài viết theo ID
    public DvhBlogPost dvhFindById(Long id) {
        Optional<DvhBlogPost> opt = dvhBlogPostRepository.findById(id);
        return opt.orElse(null);
    }

    // 3. Lưu bài viết
    public DvhBlogPost dvhSave(DvhBlogPost post) {
        return dvhBlogPostRepository.save(post);
    }

    // 4. Xóa bài viết
    public void dvhDelete(Long id) {
        dvhBlogPostRepository.deleteById(id);
    }
}