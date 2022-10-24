package com.nighthawk.spring_portfolio.mvc.songs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController // annotation to simplify the creation of RESTful web services
@RequestMapping("/api/songs")
public class SongsApiController {

    // Autowired enables Control to connect HTML and POJO Object to database easily for CRUD operations
    @Autowired
    private SongsJpaRepository repository;

    /*
    GET List of Jokes
     */
    @GetMapping("/")
    public ResponseEntity<List<Songs>> getUsers() {
        return new ResponseEntity<>( repository.findAll(), HttpStatus.OK);
    }

    /* Update Like
     * @PutMapping annotation is used for mapping HTTP PUT requests onto specific handler methods.
     * @PathVariable annotation extracts the templated part {id}, from the URI
     */
    @PutMapping("/like/{id}")
    public ResponseEntity<Songs> setLikes(@PathVariable long id) {
        /* 
        * Optional (below) is a container object which helps determine if a result is present. 
        * If a value is present, isPresent() will return true
        * get() will return the value.
        */
        Optional<Songs> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Songs song = optional.get();  // value from findByID
            song.setLikes(song.getLikes()+1); // increment value
            repository.save(song);  // save entity
            return new ResponseEntity<>(song, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Failed HTTP response: status code, headers, and body
    }
    /* Update Dislikes
     */
    @PutMapping("/dislike/{id}")
    public ResponseEntity<Songs> setDislikes(@PathVariable long id) {
        Optional<Songs> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Songs song = optional.get();
            song.setDislikes(song.getDislikes()+1);
            repository.save(song);
            return new ResponseEntity<>(song, HttpStatus.OK);
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
