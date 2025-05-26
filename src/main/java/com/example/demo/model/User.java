package com.example.demo.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")  // Chỉ định bảng sẽ được ánh xạ

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User(UUID id, String username, String passwordHash, String displayName, String avatarUrl, String phoneNumber, String email, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.displayName = displayName;
        this.avatarUrl = avatarUrl;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
