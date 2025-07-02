package com.example.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {
     @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "tên không được để trống")
    private String name;

    @Email(message = "email không hợp lệ")
    @NotBlank(message = "email không được để trống")
    private String email;

    @NotBlank(message = "mật khẩu không được để trống")
    private String password;
}
