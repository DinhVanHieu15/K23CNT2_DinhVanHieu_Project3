package k23cnt2.dvh.lesson07.DvhService;

import k23cnt2.dvh.lesson07.DvhEntity.DvhCategory;
import k23cnt2.dvh.lesson07.DvhRepository.DvhCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DvhCategoryService {
    @Autowired
    private DvhCategoryRepository DvhCategoryRepository;

    public DvhCategoryService (DvhCategoryRepository DvhCategoryRepository) {
        this.DvhCategoryRepository = DvhCategoryRepository;
    }

    public List<DvhCategory> getAllDvhCategories() {
        System.out.println("DvhCategoryRepository.findAll");
        return DvhCategoryRepository.findAll();
    }

    public Optional<DvhCategory> getDvhCategoryById(Long id) {
        return DvhCategoryRepository.findById(id);
    }

    public DvhCategory saveDvhCategory(DvhCategory DvhCategory) {
        return DvhCategoryRepository.save(DvhCategory);
    }

    public void deleteDvhCategory(Long id) {
        DvhCategoryRepository.deleteById(id);
    }
}
