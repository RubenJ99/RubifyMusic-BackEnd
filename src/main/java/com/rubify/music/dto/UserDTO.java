package com.rubify.music.dto;

import com.rubify.music.entity.Authority;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Integer id;
    private String email;
    private String name;
    private String surname;
    private String iban;
    private byte[] profilePicture;
    private Authority authority;
}
