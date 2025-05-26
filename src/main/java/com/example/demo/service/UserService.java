package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Hàm mã hóa mật khẩu bằng SHA-256 (hoặc có thể thay bằng một phương thức khác)
    private String hashPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = messageDigest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error while hashing password", e);
        }
    }

    // Kiểm tra thông tin đăng nhập
    public boolean validateUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            // So sánh mật khẩu hash trong DB với mật khẩu người dùng đã mã hóa
            return user.get().getPasswordHash().equals(hashPassword(password));
        }
        return false;
    }


    // Kiểm tra xem người dùng đã tồn tại hay chưa
    public boolean userExists(String username, String email, String phoneNumber) {
        return userRepository.findByUsername(username).isPresent() ||
                userRepository.findByEmail(email).isPresent() ||
                userRepository.findByPhoneNumber(phoneNumber).isPresent();
    }

    // Đăng ký người dùng mới
    public void registerUser(User user) {
        // Lấy mật khẩu gốc từ trường password (tạm, không lưu DB)
        String rawPassword = user.getPassword(); // mật khẩu gốc người dùng nhập

        // Mã hóa mật khẩu trước khi lưu
        String encodedPassword = hashPassword(rawPassword);
        user.setPasswordHash(encodedPassword);

        // Có thể xóa hoặc đặt null trường password (để tránh lưu nhầm)
        user.setPassword(null);

        userRepository.save(user);
    }

}
