package com.rubify.music.controller;

import com.rubify.music.entity.SongEntity;
import com.rubify.music.mapper.SongModelMapper;
import com.rubify.music.repository.ISongRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class SongController {
    private final ISongRepository songRepository;
    private final SongModelMapper mapper;

    SongController(ISongRepository songRepository, SongModelMapper mapper){
        this.songRepository = songRepository;
        this.mapper = mapper;
    }

    @GetMapping("/songs")
    public ResponseEntity<?> getAllSongs(){
        List<EntityModel<SongEntity>> songs = songRepository.findAll().stream()
                .map(mapper::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(CollectionModel.of(songs,
                linkTo(methodOn(SongController.class).getAllSongs()).withSelfRel()));
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<?> getOneSong(@PathVariable Integer id){
        SongEntity song = songRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException(id.toString()));
        if(song == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toModel(song));
    }

    @PostMapping("/songs") //TODO hacer front econder 64
    public ResponseEntity<?> addSong(@RequestBody SongEntity song){
        EntityModel<SongEntity> songMod = mapper.toModel(songRepository.save(song));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(songMod);
    }

    @PutMapping("/songs/{id}")
    public ResponseEntity<?> updateSong(@RequestBody SongEntity song, @PathVariable Integer id){
        SongEntity songToUpd = songRepository.findById(id)
                .map(songEntity -> {
                    songEntity.setSongName(song.getSongName());
                    songEntity.setReleaseDate(song.getReleaseDate());
                    songEntity.setExplicitContent(song.isExplicitContent());
                    return songRepository.save(songEntity);
                }).orElseThrow(()->new IllegalArgumentException(id.toString()));

        EntityModel<SongEntity> entity = mapper.toModel(songToUpd);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(entity);
    }

    @DeleteMapping("/songs/{id}")
    public ResponseEntity<?> deleteSong(@PathVariable Integer id){
        songRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
