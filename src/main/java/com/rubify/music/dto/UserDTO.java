package com.rubify.music.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonSerialize
public class UserDTO implements Serializable {
    private static final long serialVersionUID = -8314435638806060154L;
    private Integer id;
    private String email;
    private String name;
    private String surname;
    private String iban;
    private byte[] profilePicture;
    private String authority;
}
