package k23cnt2.dvh.lesson06.DvhRepository;

import k23cnt2.dvh.lesson06.DvhEntity.DvhStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DvhStudentRepository extends JpaRepository<DvhStudent, Long> {
}
