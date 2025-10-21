package pkg_annotation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Trả dữ liệu dạng text/JSON, không phải view HTML
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "<h2>Hello, Spring Boot!</h2>";
    }
}
