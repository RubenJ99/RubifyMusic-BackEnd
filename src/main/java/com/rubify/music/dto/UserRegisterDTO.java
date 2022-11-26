package com.rubify.music.dto;

import com.rubify.music.entity.Authority;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterDTO {
    private Integer id;
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
