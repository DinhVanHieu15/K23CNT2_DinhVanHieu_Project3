package k23cnt2.dvh.lesson07.DvhController;

import k23cnt2.dvh.lesson07.DvhService.DvhCategoryService;
import k23cnt2.dvh.lesson07.DvhEntity.DvhCategory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category") // ==> tất cả method bên dưới nằm dưới /category
public class DvhCategoryController {

    private final DvhCategoryService service;

    public DvhCategoryController(DvhCategoryService service) {
        this.service = service;
    }
    public class DvhPingController {
        @GetMapping("/ping") public String ping(){ return "ok"; }
    }

    @GetMapping                // GET /category
    public String list(Model model) {
        model.addAttribute("categories", service.getAllDvhCategories());
        return "category/category-list"; // src/main/resources/templates/category/category-list.html
    }

    @GetMapping("/create")     // GET /category/create
    public String showCreateForm(Model model){
        model.addAttribute("category", new DvhCategory());
        return "category/category-form";
    }

    @GetMapping("/edit/{id}")  // GET /category/edit/5
    public String showEditForm(@PathVariable Long id, Model model){
        var c = service.getDvhCategoryById(id).orElse(new DvhCategory());
        model.addAttribute("category", c);
        return "category/category-form";
    }

    @PostMapping("/save")      // POST /category/save
    public String save(@ModelAttribute("category") DvhCategory category){
        service.saveDvhCategory(category);
        return "redirect:/category";
    }

    @GetMapping("/delete/{id}") // GET /category/delete/5
    public String delete(@PathVariable Long id){
        service.deleteDvhCategory(id);
        return "redirect:/category";
    }
}
