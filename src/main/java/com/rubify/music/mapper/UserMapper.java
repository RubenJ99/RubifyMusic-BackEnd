package com.rubify.music.mapper;

import com.rubify.music.controller.AuthController;
import com.rubify.music.controller.SongController;
import com.rubify.music.entity.UserEntity;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserMapper implements RepresentationModelAssembler<UserEntity, EntityModel<UserEntity>> {
    @Override
    public EntityModel<UserEntity> toModel(UserEntity user) {
        return EntityModel.of(user);
    }
}
