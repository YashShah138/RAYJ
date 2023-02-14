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
    @GetMapping("/new/{itineraryId}/{name}/{description}/{packing}/{travel}/{food}/{hotel}/{activities}/{notes}")
    public ResponseEntity<List<Itinerary>> createItinerary(@PathVariable Long itineraryId, @PathVariable String name, @PathVariable String description, @PathVariable String packing, @PathVariable String travel, @PathVariable String food, @PathVariable String hotel, @PathVariable String activities, @PathVariable String notes) {
    Itinerary newItinerary = new Itinerary(itineraryId, name, description, packing, travel, food, hotel, activities, notes);
    repository.save(newItinerary);
    return new ResponseEntity<>(repository.findByItineraryId(itineraryId), HttpStatus.OK);
    }

    //update
    @GetMapping("/get/{itineraryId}")
    public ResponseEntity<Itinerary> getItinerary(@PathVariable Long itineraryId) {
    Itinerary existingItinerary = repository.findById(itineraryId).get();
    return new ResponseEntity<>(existingItinerary, HttpStatus.OK);

    }

    // @GetMapping("/update/{itineraryId}")
    // public ResponseEntity<List<Itinerary>> updateItinerary(@PathVariable Long itineraryId) {
    // Itinerary selectedItinerary = repository.findById(itineraryId).get();
    // repository.update(selectedItinerary);
    // return new ResponseEntity<>(repository.findByItineraryId(itineraryId), HttpStatus.OK);
    // }


    //delete
    @GetMapping("/delete/{itineraryId}")
    public ResponseEntity<List<Itinerary>> deleteItinerary(@PathVariable Long itineraryId) {
    Itinerary selectedItinerary = repository.findById(itineraryId).get();
    repository.delete(selectedItinerary);
    return new ResponseEntity<>(repository.findByItineraryId(itineraryId), HttpStatus.OK);
    }

}
