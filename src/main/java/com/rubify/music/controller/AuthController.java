package com.rubify.music.controller;

import com.rubify.music.dto.AuthCredentialsRequestDTO;
import com.rubify.music.dto.UserDTO;
import com.rubify.music.dto.UserRegisterDTO;
import com.rubify.music.entity.UserEntity;
import com.rubify.music.mapper.UserMapper;
import com.rubify.music.repository.IUserCustomRepository;
import com.rubify.music.repository.IUserRepository;
import com.rubify.music.utils.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    private final IUserRepository userRepository;
    private final IUserCustomRepository userCustomRepository;
    private final UserMapper mapper;

    public AuthController(IUserRepository userRepository, UserMapper mapper, IUserCustomRepository userCustomRepository){
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.userCustomRepository = userCustomRepository;
    }



@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody final AuthCredentialsRequestDTO requestDTO){
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
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody final UserRegisterDTO userDTO){
        final UserEntity newUser = userRepository.findById(userCustomRepository.saveUser(userDTO)).orElseThrow();
        return ResponseEntity.ok().body(mapper.toModel(newUser));
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validate(@RequestParam String token, @AuthenticationPrincipal UserEntity user){
        try{
            Boolean isValidToken = jwtUtil.validateToken(token,user);
            return ResponseEntity.ok(isValidToken);
        }catch (ExpiredJwtException ex){
            return ResponseEntity.ok(false);
        }
    }
}
