package com.rubify.music.controller;

import com.rubify.music.dto.UserDTO;
import com.rubify.music.entity.UserEntity;
import com.rubify.music.mapper.UserMapper;
import com.rubify.music.repository.IUserRepository;
import io.swagger.models.Response;
import io.swagger.models.auth.In;
import org.apache.catalina.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final IUserRepository userRepository;
    private final UserMapper mapper;

    UserController(IUserRepository userRepository, UserMapper mapper){
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<EntityModel<UserDTO>> getUserById(@PathVariable Integer id){
        UserEntity user = userRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(mapper.toModel(user));
    }

    @GetMapping("/users")
    public ResponseEntity<List<EntityModel<UserDTO>>> getAllUsers(){
        List<UserEntity> users = userRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toModelList(users));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Integer id){
        userRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
