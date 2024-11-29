package com.sgu.givingsgu.controller;


import com.sgu.givingsgu.dto.LoginResponse;
import com.sgu.givingsgu.dto.RegisterRequest;
import com.sgu.givingsgu.dto.ResponseWrapper;
import com.sgu.givingsgu.model.Faculty;
import com.sgu.givingsgu.repository.FacultyRepository;
import com.sgu.givingsgu.service.JwtService;
import com.sgu.givingsgu.service.UserService;
import com.sgu.givingsgu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private FacultyRepository facultyRepository;


    // Endpoint để đăng ký người dùng mới
    @PostMapping("/register")
    public ResponseEntity<ResponseWrapper<User>> registerUser(@RequestBody RegisterRequest request) {
        if (userService.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body(
                    new ResponseWrapper<>(400, "Username đã tồn tại!", null)
            );
        }
        if (userService.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body(
                    new ResponseWrapper<>(400, "Email đã được sử dụng!", null)
            );
        }

        // Lấy Faculty từ cơ sở dữ liệu
        Faculty faculty = facultyRepository.findById(request.getFacultyId())
                .orElseThrow(() -> new RuntimeException("Faculty không tồn tại!"));

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setFullName(request.getFullName());
        user.setStudentId(request.getStudentId());
        user.setRole("USER"); // Mặc định role là USER
        user.setPoints(0); // Điểm ban đầu
        user.setImageUrl(request.getImageUrl());
        user.setFaculty(faculty); // Ánh xạ Faculty từ facultyId

        // Lưu User vào cơ sở dữ liệu
        User savedUser = userService.saveUser(user);

        return ResponseEntity.ok(
                new ResponseWrapper<>(200, "User registered successfully", savedUser)
        );


    }

    // Endpoint để đăng nhập
    @PostMapping("/login")
    public ResponseEntity<ResponseWrapper<LoginResponse>> loginUser(@RequestParam String username, @RequestParam String password) {
        try {
            // Xác thực người dùng
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            // Lấy thông tin người dùng và tạo JWT
            final UserDetails userDetails = userService.loadUserByUsername(username);
            User user = userService.findByUsername(username);
            String token = jwtService.generateToken(userDetails.getUsername());

            LoginResponse loginResponse = new LoginResponse(user, token);

            return ResponseEntity.ok(new ResponseWrapper<>(200, "Login successful", loginResponse));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseWrapper<>(401, "Invalid username or password!", null));
        }
    }
}


