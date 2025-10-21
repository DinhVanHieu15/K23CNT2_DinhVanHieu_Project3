package pkg_annotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lesson02SpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(Lesson02SpringBootApplication.class, args);
        System.out.println("✅ Lesson02 Spring Boot is running...");
    }
}
