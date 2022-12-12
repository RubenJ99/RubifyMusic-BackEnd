package com.rubify.music.entity;

import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.annotations.Fetch;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/*
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private @Column(name = "role") String authority;

    public Authority(String authority){
        this.authority = authority;
    }
}
*/