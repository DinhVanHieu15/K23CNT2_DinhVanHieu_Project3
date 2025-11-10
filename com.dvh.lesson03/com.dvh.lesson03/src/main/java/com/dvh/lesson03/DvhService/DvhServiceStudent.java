package com.dvh.lesson03.DvhService;


import com.dvh.lesson03.DvhEntity.DvhStudent;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Service class: DvhStudentService
 * Lớp dịch vụ thực hiện các chức năng thao tác với List Object DvhStudent
 * @author Đinh Văn Hiếu
 */
@Service
public class DvhServiceStudent {

    private List<DvhStudent> dvhStudents = new ArrayList<>();

    public DvhServiceStudent() {
        dvhStudents.addAll(Arrays.asList(
                new DvhStudent(1L, "Đinh Văn Hiếu", 20, "Hà Nội", "hieu@gmail.com", "0978611889", "Nam"),
                new DvhStudent(2L, "Nguyễn Bảo An", 25, "Hà Nội", "baoan@gmail.com", "0978611888", "Nam"),
                new DvhStudent(3L, "Nguyễn Hoàng Nam", 22, "Số 25 VNP", "nam@gmail.com", "0978611887", "Nam")
        ));
    }


    public List<DvhStudent> getDvhStudents() {
        return dvhStudents;
    }


    public DvhStudent getDvhStudent(Long dvhId) {
        return dvhStudents.stream()
                .filter(s -> s.getDvhid().equals(dvhId))
                .findFirst()
                .orElse(null);
    }


    public DvhStudent addDvhStudent(DvhStudent dvhStudent) {
        dvhStudents.add(dvhStudent);
        return dvhStudent;
    }


    public DvhStudent updateDvhStudent(Long dvhId, DvhStudent dvhStudent) {
        DvhStudent check = getDvhStudent(dvhId);
        if (check == null) return null;

        dvhStudents.forEach(item -> {
            if (item.getDvhid().equals(dvhId)) {
                item.setDvhname(dvhStudent.getDvhname());
                item.setDvhaddress(dvhStudent.getDvhaddress());
                item.setDvhemail(dvhStudent.getDvhemail());
                item.setDvhphone(dvhStudent.getDvhphone());
                item.setDvhage(dvhStudent.getDvhage());
                item.setDvhgender(dvhStudent.getDvhgender());
            }
        });
        return dvhStudent;
    }


    public boolean deleteDvhStudent(Long dvhId) {
        DvhStudent check = getDvhStudent(dvhId);
        return dvhStudents.remove(check);
    }
}
