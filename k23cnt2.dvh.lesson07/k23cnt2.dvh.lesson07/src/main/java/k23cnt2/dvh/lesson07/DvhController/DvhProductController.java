package k23cnt2.dvh.lesson07.DvhController;

import k23cnt2.dvh.lesson07.DvhEntity.DvhProduct;
import k23cnt2.dvh.lesson07.DvhService.DvhCategoryService;
import k23cnt2.dvh.lesson07.DvhService.DvhProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class DvhProductController {

    private final DvhProductService productService;
    private final DvhCategoryService categoryService;

    public DvhProductController(DvhProductService productService, DvhCategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    // Danh sách
    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", productService.getAllDvhProduct());
        return "product/product-list";
    }

    // Form tạo mới
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("product", new DvhProduct());
        model.addAttribute("categories", categoryService.getAllDvhCategories());
        return "product/product-form";
    }

    // Form sửa
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        var product = productService.findById(id).orElse(new DvhProduct());
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllDvhCategories());
        return "product/product-form";
    }

    // Lưu (create/update)
    @PostMapping("/save")
    public String save(@ModelAttribute("product") DvhProduct product) {
        productService.saveDvhProduct(product);
        return "redirect:/products";
    }

    // Xóa
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        productService.deleteDvhProduct(id);
        return "redirect:/products";
    }
}
