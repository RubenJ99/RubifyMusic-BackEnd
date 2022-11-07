package com.rubify.music.entity;

import jdk.dynalink.linker.LinkerServices;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Table(name = "user_performer")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserPerformerEntity implements Serializable {
    private static final long serialVersionUID = 2881016933523907242L;
    private @Id @GeneratedValue Integer id;
    private @Column(name = "email") String email;
    private @Column(name = "name") String name;
    private @Column(name = "surname") String surname;
    private @Column(name = "password") String password;
    private @Column(name = "iban") String iban;
    private @Column(name = "expiration_date") String expirationDate;
    private @Column(name = "name_on_card") String nameOnCard;
    private @Lob @Type(type = "org.hibernate.type.BinaryType") @Column(name = "profile_picture") byte[] profilePicture;

    private @OneToMany(mappedBy = "performer") List<SongJoinPerformer> performers;
}
