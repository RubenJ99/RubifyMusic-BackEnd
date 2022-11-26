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


@Table(name = "user", schema = "public")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
        return this.email;
    }

    @Override
    public String getPassword(){
        return this.password;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public List<SongJoinPerformer> getPerformers() {
        return performers;
    }

    public void setPerformers(List<SongJoinPerformer> performers) {
        this.performers = performers;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }
}
