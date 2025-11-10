package com.dvh.lesson03.DvhController;


import com.dvh.lesson03.DvhEntity.DvhStudent;
import com.dvh.lesson03.DvhService.DvhServiceStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dvh") // prefix chung cho các API của bạn
public class DvhStudentController {

    private final DvhServiceStudent DvhServiceStudent;

    @Autowired
    public DvhStudentController(DvhServiceStudent DvhServiceStudent) {
        this.DvhServiceStudent = DvhServiceStudent;
    }

    // GET /dvh/student-list  -> lấy toàn bộ
    @GetMapping("/student-list")
    public List<DvhStudent> getAllDvhStudents() {
        return DvhServiceStudent.getDvhStudents();
    }

    // GET /dvh/student/{id} -> lấy 1 sinh viên
    @GetMapping("/student/{id}")
    public DvhStudent getDvhStudent(@PathVariable Long id) {
        return DvhServiceStudent.getDvhStudent(id);
    }

    // POST /dvh/student-add  -> thêm mới
    @PostMapping("/student-add")
    public DvhStudent addDvhStudent(@RequestBody DvhStudent dvhStudent) {
        return DvhServiceStudent.addDvhStudent(dvhStudent);
    }

    // PUT /dvh/student/{id}  -> cập nhật
    @PutMapping("/student/{id}")
    public DvhStudent updateDvhStudent(@PathVariable Long id,
                                       @RequestBody DvhStudent dvhStudent) {
        return DvhServiceStudent.updateDvhStudent(id, dvhStudent);
    }

    // DELETE /dvh/student/{id} -> xóa
    @DeleteMapping("/student/{id}")
    public boolean deleteDvhStudent(@PathVariable Long id) {
        return DvhServiceStudent.deleteDvhStudent(id);
    }
}