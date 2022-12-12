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
public class CategoryDTO implements Serializable {
    private static final long serialVersionUID = -6055510004581813909L;

    private Integer id;
    private String name;
}
