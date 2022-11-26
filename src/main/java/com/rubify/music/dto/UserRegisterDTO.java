package com.rubify.music.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rubify.music.entity.Authority;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonSerialize
public class UserRegisterDTO implements Serializable {

    private static final long serialVersionUID = -792816251916237766L;

    private String email;
    private String password;
    private String name;
    private String surname;
    private String iban;
    private String expirationDate;
    private String nameOnCard;
    private byte[] profilePicture;
    private Authority authority;
}
