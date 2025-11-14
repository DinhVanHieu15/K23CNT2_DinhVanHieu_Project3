package k23cnt2.dvh.lesson06.DvhDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DvhStudentDTO {

    private Long id;
    private String name;
    private String email;
    private int age;
}
