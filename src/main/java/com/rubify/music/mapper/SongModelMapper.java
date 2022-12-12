package com.rubify.music.mapper;

import com.rubify.music.controller.SongController;
import com.rubify.music.dto.SongDTO;
import com.rubify.music.entity.SongEntity;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class SongModelMapper implements RepresentationModelAssembler<SongEntity, EntityModel<SongDTO>> {
    @Override
    public EntityModel<SongDTO> toModel(SongEntity song){
        SongDTO songDTO = SongDTO.builder()
                .id(song.getId())
                .name(song.getSongName())
                .iconFile(song.getIconFile())
                .audioFile(song.getAudioFile())
                .releaseDate(song.getReleaseDate())
                .explicitContent(song.isExplicitContent()).build();

        List<String> catList = new ArrayList<>();
        song.getCategories().parallelStream().forEach((s)-> {
            catList.add(s.getCategory().getCategoryName());
        });

        List<String> performersList = new ArrayList<>();
        song.getSongsByPerformer().parallelStream().forEach((s) -> {
            performersList.add(s.getPerformer().getName() + " " + s.getPerformer().getSurname());
        });

        songDTO.setCategories(catList);
        songDTO.setPerformers(performersList);
        return EntityModel.of(songDTO);
    }

    public List<EntityModel<SongDTO>> toModelList(List<SongEntity> songs) {
        List<EntityModel<SongDTO>> list = new LinkedList<>();

        songs.stream().forEach((song -> {
           list.add(toModel(song));
        }));

        return list;
    }
}
