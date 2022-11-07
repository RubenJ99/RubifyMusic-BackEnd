package com.rubify.music.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "song_join_performer")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SongJoinPerformer implements Serializable {

    private static final long serialVersionUID = 7485937702699604387L;
    private @Id @GeneratedValue @Column(name = "id") Integer id;
    private @ManyToOne @JoinColumn(name = "song_id") SongEntity song;
    private @ManyToOne @JoinColumn(name = "performer_id") UserPerformerEntity performer;
}
