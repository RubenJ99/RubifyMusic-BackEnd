package com.rubify.music.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonSerialize
public class NewSongDTO implements Serializable {
    private static final long serialVersionUID = -4914083233889420946L;

    private String name;
    private String iconFile;
    private String audioFile;
    private String releaseDate;
    private boolean explicitContent;
    private Integer performerId;
    private Integer categoryId;
}