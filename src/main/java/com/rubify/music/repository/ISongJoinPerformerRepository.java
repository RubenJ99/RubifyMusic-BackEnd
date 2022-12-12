package com.rubify.music.repository;

import com.rubify.music.entity.SongJoinPerformer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISongJoinPerformerRepository extends JpaRepository<SongJoinPerformer,Integer> {
}
