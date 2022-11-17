package com.rubify.music.entity;

import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Table(name = "user")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEntity implements Serializable, UserDetails {
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

    private @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auth_id") Authority authority;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new Authority("ROLE_DEFAULT"));
        return roles;
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public String getPassword(){
        return this.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
