package com.sgu.givingsgu.controller;


import com.sgu.givingsgu.dto.ResponseWrapper;
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

    // Endpoint để đăng ký người dùng mới
    @PostMapping("/register")
    public ResponseEntity<ResponseWrapper<User>> registerUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok(new ResponseWrapper<>(200, "User registered successfully", user));
    }

    // Endpoint để đăng nhập
    @PostMapping("/login")
    public ResponseEntity<ResponseWrapper<String>> loginUser(@RequestParam String username, @RequestParam String password) {
        try {
            // Xác thực người dùng
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            // Lấy thông tin người dùng và tạo JWT
            final UserDetails userDetails = userService.loadUserByUsername(username);

            String token = jwtService.generateToken(userDetails.getUsername());
            return ResponseEntity.ok(new ResponseWrapper<>(200, "Login successful", token));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseWrapper<>(401, "Invalid username or password!", null));
        }
    }
}


