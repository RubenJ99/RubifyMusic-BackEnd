package com.rubify.music.mapper;

import com.rubify.music.controller.SongController;
import com.rubify.music.entity.SongEntity;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class SongModelMapper implements RepresentationModelAssembler<SongEntity, EntityModel<SongEntity>> {
    @Override
    public EntityModel<SongEntity> toModel(SongEntity song){
        return EntityModel.of(song,
                linkTo(methodOn(SongController.class).getOneSong(song.getId())).withSelfRel(),
                linkTo(methodOn(SongController.class).getAllSongs()).withRel("songs"));
    }
}
