package com.nighthawk.spring_portfolio.mvc.tripplanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // annotation to simplify the creation of RESTful web services
@RequestMapping("/api/tripplanner") // all requests in file begin with this URI
public class TripPlannerController {

    // Autowired enables Control to connect URI request and POJO Object to easily
    // for Database CRUD operations
    @Autowired
    private TripPlannerJpaRepository repository;

    /*
     * GET List of Jokes
     * 
     * @GetMapping annotation is used for mapping HTTP GET requests onto specific
     * handler methods.
     */
    @GetMapping("/")
    public ResponseEntity<List<TripPlanner>> getTripPlanner() {
        // ResponseEntity returns List of Jokes provide by JPA findAll()
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<TripPlanner>> searchTripPlanner(@PathVariable String name) {
        List<TripPlanner> listings = repository.findByNameIgnoreCase(name);
        return new ResponseEntity<>(listings,HttpStatus.OK);
    }

    //create
    @PostMapping("/create/{name}/{packing}/{travel}/{food}/{hotel}/{activities}/{notes}")
    public ResponseEntity<TripPlanner> createTripPlanner(@PathVariable String name,
            @PathVariable String packing, @PathVariable String travel, @PathVariable String food, @PathVariable String hotel, @PathVariable String activities, @PathVariable String notes) {
        repository.saveAndFlush(new TripPlanner(null, name, packing, travel, food, hotel, activities, notes));
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    //update
    @PutMapping("/update/{id}")
    public ResponseEntity<TripPlanner> updateListing(@PathVariable long id, @RequestBody TripPlanner changes) {
        Optional<TripPlanner> optional = repository.findById(id);
        if (optional.isPresent()) { // Good ID
            TripPlanner listing = optional.get(); // value from findByID
            listing.setName(changes.getName()); // value from findByID
            listing.setPacking(changes.getPacking()); // value from findByID
            listing.setTravel(changes.getTravel()); // value from findByID
            listing.setFood(changes.getFood()); // value from findByID
            listing.setHotel(changes.getHotel()); // value from findByID
            listing.setActivities(changes.getActivities()); // value from findByID
            listing.setNotes(changes.getNotes());
            repository.save(listing);
            return new ResponseEntity<>(listing, HttpStatus.OK); // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TripPlanner> deleteTripPlanner(@PathVariable long id) {
        Optional<TripPlanner> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            TripPlanner activity = optional.get();  // value from findByID
            repository.deleteById(id);  // value from findByID
            return new ResponseEntity<>(activity, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}