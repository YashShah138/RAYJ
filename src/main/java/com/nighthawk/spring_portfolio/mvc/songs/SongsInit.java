package com.nighthawk.spring_portfolio.mvc.songs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component // Scans Application for ModelInit Bean, this detects CommandLineRunner
public class SongsInit {
    
    // Inject repositories
    @Autowired SongsJpaRepository repository;
    
    @Bean
    CommandLineRunner run() {  // The run() method will be executed after the application starts
        return args -> {
            // Fail safe data validations

            // starting jokes
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
                List<Songs> test = repository.findBySongIgnoreCase(song);  // JPA lookup
                if (test.size() == 0)
                    repository.save(new Songs(null, song, 0, 0)); //JPA save
            }
            
        };
    }
}

