package com.rubify.music.repository;

import com.rubify.music.entity.SongJoinCatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISongJoinCatRepository extends JpaRepository<SongJoinCatEntity,Integer> {
}
