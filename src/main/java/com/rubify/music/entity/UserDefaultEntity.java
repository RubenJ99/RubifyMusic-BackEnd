package com.rubify.music.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "user_default")

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDefaultEntity implements Serializable {

    private static final long serialVersionUID = 5933216080924065923L;
    private @Id @GeneratedValue @Column(name = "id") Integer id;
    private @Column(name = "email") String email;
    private @Column(name = "name") String name;
    private @Column(name = "surname") String surname;
    private @Column(name = "password") String password;
    private @Lob @Type(type = "org.hibernate.type.BinaryType") @Column(name = "profile_picture") byte[] profile_picture;


    private @OneToOne(mappedBy = "userDefault",cascade = CascadeType.ALL,fetch = FetchType.LAZY) CustomPlaylistEntity customPlaylist;
    private @OneToMany(mappedBy = "user") List<LikedSongEntity> likedSongs;
}
