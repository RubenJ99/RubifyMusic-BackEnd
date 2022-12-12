package com.rubify.music.repository;

import com.rubify.music.entity.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface ISongRepository extends JpaRepository<SongEntity,Integer> {

    @Query(value = "INSERT INTO public.liked_song (song_id,user_id) VALUES (:songId,:userId);", nativeQuery = true)
    void addSongToFav(@Param("songId") Integer songId,@Param("userId") Integer userId);

    @Query(value = "SELECT * FROM public.song s INNER JOIN public.liked_song lk ON s.id = lk.song_id WHERE lk.user_id = (:userId);", nativeQuery = true)
    List<SongEntity> getLikedSongsByUserId(@Param("userId") Integer userId);

    @Query(value = "SELECT * FROM public.song s INNER JOIN public.song_join_performer sjp ON s.id = sjp.song_id WHERE sjp.performer_id = (:perfId);",nativeQuery = true)
    List<SongEntity> getSongsByPerformerId(@Param("perfId") Integer perfId);
    
}
