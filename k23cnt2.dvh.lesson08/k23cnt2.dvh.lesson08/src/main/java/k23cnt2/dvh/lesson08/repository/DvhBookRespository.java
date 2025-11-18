package k23cnt2.dvh.lesson08.repository;

import k23cnt2.dvh.lesson08.entity.DvhBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DvhBookRespository    extends JpaRepository<DvhBook, Long> {
}
