package com.sgu.givingsgu.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;


    @Column(name = "faculty_id", nullable = false)
    private String facultyId;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String studentId;

    @Column(nullable = false)
    private String role;
}

