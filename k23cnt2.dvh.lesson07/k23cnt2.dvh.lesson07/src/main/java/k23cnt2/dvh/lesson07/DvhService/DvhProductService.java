package k23cnt2.dvh.lesson07.DvhService;

import k23cnt2.dvh.lesson07.DvhEntity.DvhProduct;
import k23cnt2.dvh.lesson07.DvhRepository.DvhProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DvhProductService {
    @Autowired
    private DvhProductRepository DvhProductRepository;

    public List<DvhProduct> getAllDvhProduct() {
        return DvhProductRepository.findAll();
    }

    public Optional<DvhProduct> findById(Long id) {
        return DvhProductRepository.findById(id);
    }

    public DvhProduct saveDvhProduct(DvhProduct DvhProduct) {
        System.out.println(DvhProduct);
        return DvhProductRepository.save(DvhProduct);
    }

    public void deleteDvhProduct(Long id) {
        DvhProductRepository.deleteById(id);
    }

}
