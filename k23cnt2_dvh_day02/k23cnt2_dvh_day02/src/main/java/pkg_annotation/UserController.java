package pkg_annotation;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    // Lấy danh sách tất cả người dùng
    @GetMapping("/users")
    public String getUsers() {
        return "<h1>Get all users</h1>";
    }

    // Thêm mới người dùng
    @PostMapping("/users")
    public String createUser() {
        return "<h1>User created</h1>";
    }

    // Cập nhật thông tin người dùng theo ID
    @PutMapping("/users/{id}")
    public String updateUser(@PathVariable int id) {
        return "<h1>User with ID " + id + " updated</h1>";
    }

    // Xóa người dùng theo ID
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id) {
        return "<h1>User with ID " + id + " deleted</h1>";
    }
}
