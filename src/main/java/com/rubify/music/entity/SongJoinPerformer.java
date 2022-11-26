package com.rubify.music.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "song_join_performer")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SongJoinPerformer implements Serializable {

    private static final long serialVersionUID = 7485937702699604387L;
    private @Id @GeneratedValue @Column(name = "id") Integer id;
    private @ManyToOne @JoinColumn(name = "song_id") SongEntity song;
    private @ManyToOne @JoinColumn(name = "performer_id") UserEntity performer;
}
