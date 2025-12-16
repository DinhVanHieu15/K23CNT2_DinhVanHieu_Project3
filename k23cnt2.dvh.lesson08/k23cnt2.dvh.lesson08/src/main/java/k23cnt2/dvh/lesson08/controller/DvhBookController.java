package k23cnt2.dvh.lesson08.controller;

import k23cnt2.dvh.lesson08.entity.DvhBook;
import k23cnt2.dvh.lesson08.service.DvhAuthorService;
import k23cnt2.dvh.lesson08.service.DvhBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/templates/books")
@RequiredArgsConstructor
public class DvhBookController {

    private final DvhBookService bookService;
    private final DvhAuthorService authorService;

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("templates/books", bookService.getAllBooks());
        return "templates/books/book-list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new DvhBook());
        model.addAttribute("authors", authorService.getAllAuthors());
        return "templates/books/book-form";
    }

    @PostMapping("/new")
    public String saveBook(@ModelAttribute("book") DvhBook book,
                           @RequestParam(value = "authorIds", required = false) List<Long> authorIds) {
        if (authorIds != null) {
            book.setDvhAuthors(authorService.findAllById(authorIds));
        }
        bookService.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        DvhBook b = bookService.getBookById(id);
        if (b == null) return "redirect:/books";
        model.addAttribute("book", b);
        model.addAttribute("authors", authorService.getAllAuthors());
        return "templates/books/book-form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return "redirect:/books";
    }
}
