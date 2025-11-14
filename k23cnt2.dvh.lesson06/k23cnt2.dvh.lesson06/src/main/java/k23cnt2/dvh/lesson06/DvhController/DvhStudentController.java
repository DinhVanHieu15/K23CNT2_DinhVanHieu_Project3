package k23cnt2.dvh.lesson06.DvhController;

import k23cnt2.dvh.lesson06.DvhDto.DvhStudentDTO;
import k23cnt2.dvh.lesson06.DvhEntity.DvhStudent;
import k23cnt2.dvh.lesson06.DvhService.DvhStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class DvhStudentController {

    private final DvhStudentService dvhStudentService;


    @GetMapping
    public String getStudents(Model model) {
        List<DvhStudent> students = dvhStudentService.findAll();
        model.addAttribute("students", students);
        return "student-list";
    }

    // Mở form thêm mới
    @GetMapping("/add-new")
    public String addNewStudent(Model model) {
        model.addAttribute("student", new DvhStudentDTO());
        return "student-add";
    }

    // Lưu sinh viên mới
    @PostMapping
    public String saveStudent(@ModelAttribute("student") DvhStudentDTO studentDTO) {
        dvhStudentService.save(studentDTO);
        return "redirect:/students";
    }

    // Mở form sửa
    @GetMapping("/edit/{id}")
    public String showFormForUpdate(@PathVariable("id") Long id, Model model) {
        DvhStudentDTO dto = dvhStudentService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student id: " + id));
        model.addAttribute("student", dto);
        return "student-edit";
    }

    // Cập nhật
    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") Long id,
                                @ModelAttribute("student") DvhStudentDTO studentDTO) {
        dvhStudentService.updateStudentById(id, studentDTO)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student id: " + id));
        return "redirect:/students";
    }

    // Xóa
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        dvhStudentService.deleteStudent(id);
        return "redirect:/students";
    }
}
