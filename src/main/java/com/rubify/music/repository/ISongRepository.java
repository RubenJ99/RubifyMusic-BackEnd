package com.rubify.music.repository;

import com.rubify.music.entity.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISongRepository extends JpaRepository<SongEntity,Integer> {
}
