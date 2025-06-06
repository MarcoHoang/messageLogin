package com.example.demo.controller;


import com.example.demo.dto.request.LoginRequest;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // Đăng nhập
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest ) {
        if (userService.validateUser(loginRequest.getUsername(), loginRequest.getPassword())) {
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }

    // Đăng ký người dùng mới
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            // Kiểm tra xem người dùng có tồn tại chưa
            if (userService.userExists(user.getUsername(), user.getEmail(), user.getPhoneNumber())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
            }

            // Gọi service để lưu người dùng vào cơ sở dữ liệu
            userService.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Registration successful");
        } catch (Exception e) {
            // Trả về lỗi nếu có vấn đề khi đăng ký
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed");
        }
    }
}
