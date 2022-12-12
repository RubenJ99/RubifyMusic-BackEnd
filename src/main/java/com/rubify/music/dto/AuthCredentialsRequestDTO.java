package com.rubify.music.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthCredentialsRequestDTO {
    private String email;
    private String password;
}
