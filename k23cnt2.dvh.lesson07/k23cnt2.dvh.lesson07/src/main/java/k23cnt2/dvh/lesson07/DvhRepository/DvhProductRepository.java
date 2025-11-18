package k23cnt2.dvh.lesson07.DvhRepository;


import k23cnt2.dvh.lesson07.DvhEntity.DvhProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DvhProductRepository extends
        JpaRepository<DvhProduct, Long> {
}
