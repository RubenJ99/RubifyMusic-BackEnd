package com.rubify.music.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;

@Table(name = "liked_song")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LikedSongEntity implements Serializable {
    private static final long serialVersionUID = -5415421473755698034L;

    private @Id @GeneratedValue @Column(name = "id") Integer id;

    private @ManyToOne @JoinColumn(name = "user_id") UserDefaultEntity user;
    private @ManyToOne @JoinColumn(name = "song_id") SongEntity song;

}
