package com.rubify.music.controller;

import com.rubify.music.dto.EditSongDTO;
import com.rubify.music.dto.FavSongReqDTO;
import com.rubify.music.dto.NewSongDTO;
import com.rubify.music.dto.SongDTO;
import com.rubify.music.entity.*;
import com.rubify.music.mapper.SongModelMapper;
import com.rubify.music.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1")
public class SongController {
    private final ISongRepository songRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private ISongJoinCatRepository joinCatRepository;
    @Autowired
    private ISongJoinPerformerRepository joinPerformerRepository;
    private final SongModelMapper mapper;

    SongController(ISongRepository songRepository, SongModelMapper mapper){
        this.songRepository = songRepository;
        this.mapper = mapper;
    }

    @GetMapping("/songs")
    public ResponseEntity<?> getAllSongs(){
        List<SongEntity> songs = songRepository.findAll();
        List<EntityModel<SongDTO>> songDTOS = mapper.toModelList(songs);

        return ResponseEntity.status(HttpStatus.OK).body(CollectionModel.of(songDTOS));
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<?> getOneSong(@PathVariable Integer id){
        SongEntity song = songRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException(id.toString()));
        if(song == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toModel(song));
    }

    @PostMapping("/songs")
    public ResponseEntity<?> addSong(@RequestBody NewSongDTO song){
        UserEntity user = userRepository.findById(song.getPerformerId()).orElseThrow();
        SongCategoryEntity cat = categoryRepository.findById(song.getCategoryId()).orElseThrow();
        SongEntity entity = SongEntity.builder()
                .songName(song.getName())
                .audioFile(song.getAudioFile())
                .iconFile(song.getIconFile())
                .releaseDate(song.getReleaseDate())
                .explicitContent(song.isExplicitContent())
                .build();
        songRepository.save(entity);
        SongJoinPerformer joinPerformer = SongJoinPerformer.builder()
                .performer(user)
                .song(entity).build();
        SongJoinCatEntity joinCat = SongJoinCatEntity.builder()
                .song(entity)
                .category(cat).build();

        joinPerformerRepository.save(joinPerformer);
        joinCatRepository.save(joinCat);

        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping("/songs/{id}")
    public ResponseEntity<?> updateSong(@RequestBody EditSongDTO song, @PathVariable Integer id){

        SongEntity songToUpd = songRepository.findById(id).orElseThrow();
        songToUpd.setSongName(song.getName());
        songToUpd.setReleaseDate(song.getReleaseDate());
        songToUpd.setExplicitContent(song.isExplicitContent());
        songToUpd.setIconFile(song.getIconFile());

        songRepository.save(songToUpd);


        EntityModel<SongDTO> entity = mapper.toModel(songToUpd);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(entity);
    }

    @DeleteMapping("/songs/{id}")
    public ResponseEntity<?> deleteSong(@PathVariable Integer id){
        songRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @PostMapping("/songs/addFavSong")
    public ResponseEntity<?> addFavSong(@RequestBody FavSongReqDTO favSongReqDTO){

            songRepository.addSongToFav(favSongReqDTO.getSongId(), favSongReqDTO.getUserId());
            return ResponseEntity.ok().build();

    }

    @GetMapping("/songs/likedSongs/{id}")
    public ResponseEntity<?> getLikedSongsByUser(@PathVariable("id") Integer userId){
        List<SongEntity> queryData = songRepository.getLikedSongsByUserId(userId);
        if(queryData.isEmpty() || queryData == null) return ResponseEntity.noContent().build();
        return ResponseEntity.status(HttpStatus.OK).body(CollectionModel.of(mapper.toModelList(queryData)));
    }

    @GetMapping("/songs/performer/{id}")
    public ResponseEntity<?> getSongsByPerformerId(@PathVariable("id") Integer perfId){
        List<SongEntity> queryData = songRepository.getSongsByPerformerId(perfId);
        if(queryData.isEmpty() || queryData == null) return ResponseEntity.noContent().build();
        return ResponseEntity.status(HttpStatus.OK).body(CollectionModel.of(mapper.toModelList(queryData)));
    }
}
