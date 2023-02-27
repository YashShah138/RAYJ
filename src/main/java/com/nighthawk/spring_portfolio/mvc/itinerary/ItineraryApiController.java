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
        return new ResponseEntity<>( repository.findAll(), HttpStatus.OK); //search for all
    }

    //search
    @GetMapping("/search/{name}")
    public ResponseEntity<List<Itinerary>> searchItinerary(@PathVariable String name) {
        List<Itinerary> found = repository.findByNameIgnoreCase(name); //search by itinerary name
        return new ResponseEntity<>(found,HttpStatus.OK);
    }
    // /*
    // GET List of itineraries
    //  */
    // @GetMapping("/itinerary") 
    // public ResponseEntity<List<Itinerary>> getItineraries() {
    //     return new ResponseEntity<>(itineraryRepository.findAllByOrderByItineraryNameAsc(), HttpStatus.OK);
    // }
    
    //create
    @PostMapping("/new")
    public ResponseEntity<Itinerary> createItinerary(@PathVariable String name, @PathVariable String description, @PathVariable String packing, @PathVariable String travel, @PathVariable String food, @PathVariable String hotel, @PathVariable String activities, @PathVariable String notes) {
    // Itinerary newItinerary = new Itinerary(name, description, packing, travel, food, hotel, activities, notes);
    // repository.save(newItinerary);
    repository.saveAndFlush(new Itinerary(0L, name, description, packing, travel, food, hotel, activities, notes)); //save new itinerary object to db
    return new ResponseEntity<>(HttpStatus.OK);
    }

    //update
    @PutMapping("/update/{id}")
    public ResponseEntity<Itinerary> updateItinerary(@PathVariable long id, @RequestBody Itinerary updating) {
    Optional<Itinerary> a = repository.findById(id);
    if (a.isPresent()) { //check if there is currently input
        Itinerary existing = a.get();
        existing.setName(updating.getName());
        existing.setDescription(updating.getDescription());
        existing.setPacking(updating.getPacking());
        existing.setTravel(updating.getTravel());
        existing.setFood(updating.getFood());
        existing.setHotel(updating.getHotel());
        existing.setActivities(updating.getActivities());
        existing.setNotes(updating.getNotes());
        return new ResponseEntity<>(existing, HttpStatus.OK);
    }
    // Itinerary existingItinerary = repository.findById(id).get();
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // @GetMapping("/update/{id}")
    // public ResponseEntity<List<Itinerary>> updateItinerary(@PathVariable int id) {
    // Itinerary selectedItinerary = repository.findById(id).get();
    // repository.update(selectedItinerary);
    // return new ResponseEntity<>(repository.findByid(id), HttpStatus.OK);
    // }


    //delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Itinerary> deleteItinerary(@PathVariable long id) {
    System.out.println("id is " + id);
    Optional<Itinerary> a = repository.findById(id);
    System.out.println("finding id");
    if (a.isPresent()) { 
        System.out.println("checking id");
        Itinerary existing = a.get(); 
        System.out.println("getting values from id");
        repository.deleteById(id); 
        System.out.println("deleting by id");
        return new ResponseEntity<>(existing, HttpStatus.OK); 
    }
    System.out.println("id checking done"); //finish id checking
        
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //filter out bad request
    // Itinerary selectedItinerary = repository.findById(id).get();
    // repository.delete(selectedItinerary);
    // return new ResponseEntity<>(repository.findByid(id), HttpStatus.OK);
    }

}
