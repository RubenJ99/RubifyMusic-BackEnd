package com.rubify.music.repository;

import com.rubify.music.dto.UserRegisterDTO;
import com.rubify.music.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IUserCustomRepository {

    @Transactional(rollbackFor = {Exception.class})
    Integer saveUser(UserRegisterDTO userDTO);

}
