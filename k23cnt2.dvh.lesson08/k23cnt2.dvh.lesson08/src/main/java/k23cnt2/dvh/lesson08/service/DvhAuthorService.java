package k23cnt2.dvh.lesson08.service;

import k23cnt2.dvh.lesson08.entity.DvhAuthor;
import k23cnt2.dvh.lesson08.repository.DvhAuthorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class DvhAuthorService {

    @Autowired
    private  DvhAuthorRepository dvhAuthorRepository;

    public List<DvhAuthor> getAllAuthors() {
        return dvhAuthorRepository.findAll();
    }

    public DvhAuthor saveAuthor(DvhAuthor dvhAuthor) {
        return dvhAuthorRepository.save(dvhAuthor);
    }
    public DvhAuthor getAuthorById(Long id) {
        return dvhAuthorRepository.findById(id).orElse(null);
    }
    public  void deleteAuthorById(Long id) {
        dvhAuthorRepository.deleteById(id);
    }
    public List<DvhAuthor> findAllById(List<Long> ids) {
        return dvhAuthorRepository.findAllById(ids);
    }
}
