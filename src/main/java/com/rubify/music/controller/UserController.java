package com.rubify.music.controller;

import com.rubify.music.dto.UserDTO;
import com.rubify.music.entity.UserEntity;
import com.rubify.music.mapper.UserMapper;
import com.rubify.music.repository.IUserRepository;
import io.swagger.models.Response;
import io.swagger.models.auth.In;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
