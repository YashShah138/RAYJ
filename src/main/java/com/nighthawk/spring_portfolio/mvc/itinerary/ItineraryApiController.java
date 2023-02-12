package com.nighthawk.spring_portfolio.mvc.itinerary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.nighthawk.spring_portfolio.mvc.itinerary.Itinerary;
import com.nighthawk.spring_portfolio.mvc.itinerary.ItineraryJpaRepository;


import java.util.*;

@RestController // annotation to simplify the creation of RESTful web services
@RequestMapping("/itinerary")
public class ItineraryApiController {

    // Autowired enables Control to connect HTML and POJO Object to database easily for CRUD operations
    @Autowired
    private ItineraryJpaRepository repository;

    // @Autowired
    // private ItineraryJpaRepository

    /*
    GET List of Jokes
     */
    @GetMapping("/")
    public ResponseEntity<List<Itinerary>> getItinerary() {
        return new ResponseEntity<>( repository.findAll(), HttpStatus.OK);
    }

    // /*
    // GET List of itineraries
    //  */
    // @GetMapping("/itinerary") 
    // public ResponseEntity<List<Itinerary>> getItineraries() {
    //     return new ResponseEntity<>(itineraryRepository.findAllByOrderByItineraryNameAsc(), HttpStatus.OK);
    // }
    
    //create
   
    @GetMapping("/new/{id}")
    public ResponseEntity<List<Itinerary>> createItinerary(Long id, String name, String description, String packing, String travel, String food, String hotel, String activities, String notes) {
    Itinerary newItinerary = new Itinerary(id, name, description, packing, travel, food, hotel, activities, notes);
    repository.save(newItinerary);
    return new ResponseEntity<>(repository.findAllByOrderByItineraryIdAsc(), HttpStatus.OK);
    }


    // @PostMapping(value = "/newItinerary", produces = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<Object> newItinerary(@RequestBody final Map<String, Object> map) {

    //     // Create 
    //     String name = (String) map.get("name");
    //     String description = (String) map.get("description");
    //     String packing = (String) map.get("packing");
    //     String travel = (String) map.get("travel");
    //     String food = (String) map.get("food");
    //     String hotel = (String) map.get("hotel");
    //     String activities = (String) map.get("activities");
    //     String notes = (String) map.get("notes");
    //     }

    //TODO implement update

    //delete
    @GetMapping("/delete/{id}")
    public ResponseEntity<List<Itinerary>> deleteItinerary(Long id, String name, String description, String packing, String travel, String food, String hotel, String activities, String notes) {
    Itinerary selectedItinerary = repository.findById(id).get();
    repository.delete(selectedItinerary);
    return new ResponseEntity<>(repository.findAllByOrderByItineraryIdAsc(), HttpStatus.OK);
    }

}
