package k23cnt2.dvh.lesson06.DvhService;

import k23cnt2.dvh.lesson06.DvhDto.DvhStudentDTO;
import k23cnt2.dvh.lesson06.DvhEntity.DvhStudent;
import k23cnt2.dvh.lesson06.DvhRepository.DvhStudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DvhStudentService {

    private final DvhStudentRepository dvhStudentRepository;

    // Lấy tất cả sinh viên
    public List<DvhStudent> findAll() {
        return dvhStudentRepository.findAll();
    }

    // Lấy 1 sinh viên theo id (dùng cho edit)
    public Optional<DvhStudentDTO> findById(Long id) {
        return dvhStudentRepository.findById(id)
                .map(student -> DvhStudentDTO.builder()
                        .id(student.getId())
                        .name(student.getName())
                        .email(student.getEmail())
                        .age(student.getAge())
                        .build());
    }

    // Thêm mới
    public boolean save(DvhStudentDTO studentDTO) {
        try {
            DvhStudent student = new DvhStudent();
            student.setName(studentDTO.getName());
            student.setEmail(studentDTO.getEmail());
            student.setAge(studentDTO.getAge());
            dvhStudentRepository.save(student);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Cập nhật theo id
    public Optional<DvhStudent> updateStudentById(Long id, DvhStudentDTO updatedStudent) {
        return dvhStudentRepository.findById(id)
                .map(student -> {
                    student.setName(updatedStudent.getName());
                    student.setEmail(updatedStudent.getEmail());
                    student.setAge(updatedStudent.getAge());
                    return dvhStudentRepository.save(student);
                });
    }

    // Xóa
    public void deleteStudent(Long id) {
        dvhStudentRepository.deleteById(id);
    }
}
