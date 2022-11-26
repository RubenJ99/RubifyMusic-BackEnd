package com.rubify.music.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Table(name = "custom_playlist")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomPlaylistEntity implements Serializable {
    private static final long serialVersionUID = -3228416509414743702L;
    private @Id @GeneratedValue Integer id;
    private @Column(name = "name") String name;

    private @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") UserEntity userDefault;

    private @OneToMany(mappedBy = "playlist") List<PlaylistJoinSongEntity> playlists;
}
