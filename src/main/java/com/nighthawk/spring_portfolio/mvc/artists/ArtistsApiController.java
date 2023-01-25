package com.nighthawk.spring_portfolio.mvc.artists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController // annotation to simplify the creation of RESTful web services
@RequestMapping("/api/artists")
public class ArtistsApiController {

    // Autowired enables Control to connect HTML and POJO Object to database easily for CRUD operations
    @Autowired
    private ArtistsJpaRepository repository;

    /*
    GET List of Artists
     */
    @GetMapping("/")
    public ResponseEntity<List<Artists>> getUsers() {
        return new ResponseEntity<>( repository.findAll(), HttpStatus.OK);
    }

    /* Update Like
     * @PutMapping annotation is used for mapping HTTP PUT requests onto specific handler methods.
     * @PathVariable annotation extracts the templated part {id}, from the URI
     */
    @PutMapping("/like/{id}")
    public ResponseEntity<Artists> setLikes(@PathVariable long id) {
        /* 
        * Optional (below) is a container object which helps determine if a result is present. 
        * If a value is present, isPresent() will return true
        * get() will return the value.
        */
        Optional<Artists> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Artists artist = optional.get();  // value from findByID
            artist.setLikes(artist.getLikes()+1); // increment value
            repository.save(artist);  // save entity
            return new ResponseEntity<>(artist, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Failed HTTP response: status code, headers, and body
    }
    /* Update Dislikes
     */
    @PutMapping("/dislike/{id}")
    public ResponseEntity<Artists> setDislikes(@PathVariable long id) {
        Optional<Artists> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Artists artist = optional.get();
            artist.setDislikes(artist.getDislikes()+1);
            repository.save(artist);
            return new ResponseEntity<>(artist, HttpStatus.OK);
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
