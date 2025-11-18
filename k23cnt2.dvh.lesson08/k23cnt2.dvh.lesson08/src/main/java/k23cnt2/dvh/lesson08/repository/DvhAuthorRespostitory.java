package k23cnt2.dvh.lesson08.repository;

import k23cnt2.dvh.lesson08.entity.DvhAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DvhAuthorRespostitory extends JpaRepository<DvhAuthor, Long> {
}
