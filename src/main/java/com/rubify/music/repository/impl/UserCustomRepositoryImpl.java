package com.rubify.music.repository.impl;

import com.rubify.music.dto.UserRegisterDTO;
import com.rubify.music.entity.UserEntity;
import com.rubify.music.repository.IUserCustomRepository;
import com.rubify.music.repository.IUserRepository;
import com.rubify.music.utils.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserCustomRepositoryImpl implements IUserCustomRepository {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private CustomPasswordEncoder customPasswordEncoder;


    @Override
    public Integer saveUser(UserRegisterDTO userDTO){
        UserEntity newUser = UserEntity.builder()
                .email(userDTO.getEmail())
                .password(customPasswordEncoder.getPasswordEncoder().encode(userDTO.getPassword()))
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .iban(userDTO.getIban())
                .expirationDate(userDTO.getExpirationDate())
                .nameOnCard(userDTO.getNameOnCard())
                .profilePicture(userDTO.getProfilePicture())
                .authority(userDTO.getAuthority()).build();
        return userRepository.save(newUser).getId();
    }

}
