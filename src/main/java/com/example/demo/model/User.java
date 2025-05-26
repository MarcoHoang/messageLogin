package com.example.Demo.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")  // Chỉ định bảng sẽ được ánh xạ
@Data  // Lombok sẽ tự động tạo các getter, setter, toString, equals, và hashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // UUID sẽ tự động sinh ra
    private UUID id;  // Sử dụng UUID thay vì Integer

    @Column(unique = true, nullable = false)
    private String username;  // Tên đăng nhập, duy nhất và không rỗng

    @Column(nullable = false)
    private String passwordHash;  // Mã hóa mật khẩu (password hash)

    private String displayName;  // Tên hiển thị

    private String avatarUrl;  // Đường dẫn ảnh đại diện

    @Column(unique = true)
    private String phoneNumber;  // Số điện thoại, không trùng nhau

    @Column(unique = true)
    private String email;  // Email, không trùng nhau

    private String status = "active";  // Trạng thái tài khoản mặc định là 'active'

    @CreationTimestamp
    private LocalDateTime createdAt;  // Thời gian tạo tài khoản, Hibernate sẽ tự động thiết lập

    @UpdateTimestamp
    private LocalDateTime updatedAt;  // Thời gian cập nhật tài khoản, Hibernate tự động cập nhật khi có thay đổi

    // Constructor, getter, setter sẽ tự động được Lombok sinh ra nhờ @Data
}
