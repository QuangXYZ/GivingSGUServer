package com.sgu.givingsgu.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
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


    @ManyToOne
    @JoinColumn(name = "faculty_id", insertable = false, updatable = false)
    private Faculty faculty;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String studentId;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private Integer points;

    private String imageUrl;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comment> comments;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(); // Không dùng phân quyền cụ thể trong ví dụ này
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

