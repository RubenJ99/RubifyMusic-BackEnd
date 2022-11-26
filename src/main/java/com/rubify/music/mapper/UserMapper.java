package com.rubify.music.mapper;

import com.rubify.music.controller.AuthController;
import com.rubify.music.controller.SongController;
import com.rubify.music.dto.UserDTO;
import com.rubify.music.entity.UserEntity;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserMapper implements RepresentationModelAssembler<UserEntity, EntityModel<UserDTO>> {
    @Override
    public EntityModel<UserDTO> toModel(UserEntity user) {
        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .surname(user.getSurname())
                .iban(user.getIban())
                .profilePicture(user.getProfilePicture())
                .authority(user.getAuthority()).build();
        return EntityModel.of(userDTO);
    }
}
