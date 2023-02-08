package com.nighthawk.spring_portfolio.mvc;

import com.nighthawk.spring_portfolio.mvc.role.Role;
import com.nighthawk.spring_portfolio.mvc.role.RoleJpaRepository;
import com.nighthawk.spring_portfolio.mvc.songs.Songs;
import com.nighthawk.spring_portfolio.mvc.songs.SongsJpaRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component // Scans Application for ModelInit Bean, this detects CommandLineRunner
public class ModelInit {
    // Inject repositories\
    @Autowired RoleJpaRepository roleJpaRepository;
    @Autowired SongsJpaRepository songsJpaRepository;

    @Bean
    CommandLineRunner run() {  // The run() method will be executed after the application starts
        return args -> {
            // Fail safe data validations

            final String[] SongArray = {
                "Bad Habit",
                "Unholy",
                "As It Was",
                "I Like You (A Happier Song)",
                "You Proof",
                "I Ain't Worried",
                "Sunroof",
                "Super Freaky Girl",
                "The Kind Of Love We Make",
                "Vegas"
            };

            // make sure Joke database is populated with starting jokes
            for (String song : SongArray) {
                List<Songs> test = songsJpaRepository.findBySongIgnoreCase(song);  // JPA lookup
                if (test.size() == 0)
                songsJpaRepository.save(new Songs(null, song, 0, 0)); //JPA save
            }

            // make sure Role database is populated with defaults
            String[] roles = {"ROLE_USER", "ROLE_ADMIN"};
            for (String role : roles) {
                if (roleJpaRepository.findByName(role) == null)
                    roleJpaRepository.save(new Role(null, role));
            }


        };
    }
}