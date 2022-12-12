package com.rubify.music.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.source.doctree.SerialDataTree;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonSerialize
public class FavSongReqDTO implements Serializable {
    private static final long serialVersionUID = 1041957812310119983L;

    private Integer userId;
    private Integer songId;
}
