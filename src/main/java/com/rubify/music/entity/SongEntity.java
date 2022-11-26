package com.rubify.music.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "song", schema = "public")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SongEntity implements Serializable {

    private static final long serialVersionUID = -8579064179353812899L;
    private @Id @GeneratedValue @Column(name = "id") Integer id;
    private @Column(name = "song_name") String songName;
    private @Column(name= "release_date") String releaseDate;
    private @Column(name = "explicit_content") boolean  explicitContent;
    private @Lob @Type(type = "org.hibernate.type.BinaryType") @Column(name = "icon") byte[] icon;
    private @Lob @Type(type = "org.hibernate.type.BinaryType") @Column(name = "audio") byte[] audio;

    private @OneToMany(mappedBy = "song") List<SongJoinCatEntity> categories;
    private @OneToMany(mappedBy = "song") List<PlaylistJoinSongEntity> playlists;
    private @OneToMany(mappedBy = "song") List<SongJoinPerformer> songsByPerformer;
    private @OneToMany(mappedBy = "song") List<LikedSongEntity> likedSongs;
}
