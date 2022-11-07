package com.rubify.music.config;

import com.rubify.music.entity.SongEntity;
import com.rubify.music.repository.ISongRepository;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.slf4j.Logger;

@Configuration
public class Seeds {
    private static final Logger log
            = LoggerFactory.getLogger(Seeds.class);

    @Bean
    CommandLineRunner initDb(ISongRepository repo){
        return args -> {
          log.info("Pre-generating song data... ");
        };
    }
}
