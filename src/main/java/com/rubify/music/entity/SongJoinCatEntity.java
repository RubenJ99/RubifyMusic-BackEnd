package com.rubify.music.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "song_join_cat")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SongJoinCatEntity implements Serializable {

    private static final long serialVersionUID = -7815306873125200840L;
    private @Id @GeneratedValue @Column(name = "id") Integer id;
    private @ManyToOne @JoinColumn(name = "song_id") SongEntity song;

    private @ManyToOne @JoinColumn(name = "category_id") SongCategoryEntity category;

}
