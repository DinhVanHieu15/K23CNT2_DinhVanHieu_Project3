package k23cnt2.dvh.lesson07.DvhRepository;

import k23cnt2.dvh.lesson07.DvhEntity.DvhCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DvhCategoryRepository extends
        JpaRepository<DvhCategory, Long> {
    List<DvhCategory> id(Long id);
}
