package service;

import org.springframework.stereotype.Service;

@Service  // Đánh dấu class này là một Spring Service
public class UserService {

    public String getUserDetails() {
        return "<h1>User details</h1>";
    }
}