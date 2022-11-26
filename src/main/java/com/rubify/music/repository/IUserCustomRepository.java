package com.rubify.music.repository;

import com.rubify.music.dto.UserRegisterDTO;
import org.springframework.transaction.annotation.Transactional;

public interface IUserCustomRepository {

    @Transactional(rollbackFor = {Exception.class})
    Integer saveUser(UserRegisterDTO userDTO);
}
