package com.rubify.music.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "playlist_join_song")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PlaylistJoinSongEntity implements Serializable {
    private static final long serialVersionUID = -5692520436687783241L;
    private @Id @GeneratedValue @Column(name = "id") Integer id;
    private @ManyToOne @JoinColumn(name = "playlist_id") CustomPlaylistEntity playlist;
    private @ManyToOne @JoinColumn(name = "song_id") SongEntity song;
}
