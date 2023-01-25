package com.nighthawk.spring_portfolio.mvc.artists;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component // Scans Application for ModelInit Bean, this detects CommandLineRunner
public class ArtistsInit {
    
    // Inject repositories
    @Autowired ArtistsJpaRepository repository;
    
    @Bean
    CommandLineRunner runArtists() {  // The run() method will be executed after the application starts
        return args -> {
            // Fail safe data validations

            // starting artists
            final String[] ArtistArray = {
                "Artist 1",
                "Artist 2",
                "Artist 3",
                "Artist 4",
                "Artist 5",
                "Artist 6",
                "Artist 7",
                "Artist 8",
                "Artist 9",
                "Artist 10"
            };

            // make sure Artist database is populated with starting artists
            for (String artist : ArtistArray) {
                List<Artists> test = repository.findByArtistIgnoreCase(artist);  // JPA lookup
                if (test.size() == 0)
                    repository.save(new Artists(null, artist, 0, 0)); //JPA save
            }
            
        };
    }
}

