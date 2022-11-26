package com.rubify.music.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "liked_song")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LikedSongEntity implements Serializable {
    private static final long serialVersionUID = -5415421473755698034L;

    private @Id @GeneratedValue @Column(name = "id") Integer id;

    private @ManyToOne @JoinColumn(name = "user_id") UserEntity user;
    private @ManyToOne @JoinColumn(name = "song_id") SongEntity song;

}
