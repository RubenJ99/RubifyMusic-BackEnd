package com.rubify.music.repository;

import com.rubify.music.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthRepository extends JpaRepository<Authority,Integer> {
}
