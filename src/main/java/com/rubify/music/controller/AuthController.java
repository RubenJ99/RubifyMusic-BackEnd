package com.rubify.music.controller;

import com.rubify.music.dto.AuthCredentialsRequestDTO;
import com.rubify.music.entity.UserEntity;
import com.rubify.music.mapper.UserMapper;
import com.rubify.music.repository.IUserRepository;
import com.rubify.music.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    private final IUserRepository userRepository;
    private final UserMapper mapper;

    public AuthController(IUserRepository userRepository, UserMapper mapper){
        this.mapper = mapper;
        this.userRepository = userRepository;
    }



@PostMapping("/login")
    public ResponseEntity<?> login(AuthCredentialsRequestDTO requestDTO){
        try{
            Authentication auth = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    requestDTO.getEmail(),requestDTO.getPassword())
                    );

            UserEntity user = (UserEntity) auth.getPrincipal();

            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,jwtUtil.generateToken(user))
                    .body(mapper.toModel(user));
        }catch (BadCredentialsException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
