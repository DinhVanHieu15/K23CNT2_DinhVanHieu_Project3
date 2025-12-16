package k23cnt2.dvh.lesson08.service;

import k23cnt2.dvh.lesson08.entity.DvhBook;
import k23cnt2.dvh.lesson08.repository.DvhBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DvhBookService {

    private final DvhBookRepository dvhBookRepository;

    public List<DvhBook> getAllBooks() {
        return dvhBookRepository.findAll();
    }

    public DvhBook saveBook(DvhBook book) {
        return dvhBookRepository.save(book);
    }

    public DvhBook getBookById(Long id) {
        return dvhBookRepository.findById(id).orElse(null);
    }

    public void deleteBookById(Long id) {
        dvhBookRepository.deleteById(id);
    }
}
