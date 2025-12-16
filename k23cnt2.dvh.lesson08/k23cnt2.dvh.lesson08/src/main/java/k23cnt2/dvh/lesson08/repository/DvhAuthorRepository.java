package k23cnt2.dvh.lesson08.repository;

import k23cnt2.dvh.lesson08.entity.DvhAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DvhAuthorRepository extends JpaRepository<DvhAuthor, Long> {}
