package com.rubify.music.controller;

import com.rubify.music.entity.SongEntity;
import com.rubify.music.repository.ISongRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SongController {
    private final ISongRepository songRepository;

    SongController(ISongRepository songRepository){
        this.songRepository = songRepository;
    }

    @GetMapping("/songs")
    public List<SongEntity> getAllSongs(){
        return songRepository.findAll();
    }

}
