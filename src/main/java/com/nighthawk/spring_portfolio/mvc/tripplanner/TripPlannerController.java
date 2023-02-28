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

    @PostMapping("/create/{name}/{packing}/{travel}/{food}/{hotel}/{activities}/{notes}")
    public ResponseEntity<TripPlanner> createTripPlanner(@PathVariable String name,
            @PathVariable String packing, @PathVariable String travel, @PathVariable String food, @PathVariable String hotel, @PathVariable String activities, @PathVariable String notes) {
        repository.saveAndFlush(new TripPlanner(null, name, packing, travel, food, hotel, activities, notes));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<TripPlanner>> searchTripPlanner(@PathVariable String name) {
        List<TripPlanner> listings = repository.findByNameIgnoreCase(name);
        return new ResponseEntity<>(listings,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TripPlanner> deleteListing(@PathVariable long id) {
        System.out.println("TripPlanner delete checkpoint 1.  id is "+id);
        Optional<TripPlanner> optional = repository.findById(id);
        System.out.println("TripPlanner delete checkpoint 2");
        if (optional.isPresent()) { // Good ID
            System.out.println("TripPlanner delete checkpoint 3");
            TripPlanner listing = optional.get(); // value from findByID
            System.out.println("TripPlanner delete checkpoint 4");
            repository.deleteById(id); // value from findByID
            System.out.println("TripPlanner delete checkpoint 5");
            return new ResponseEntity<>(listing, HttpStatus.OK); // OK HTTP response: status code, headers, and body
        }
        System.out.println("TripPlanner delete checkpoint 6.  Bad request.");
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TripPlanner> updateListing(@PathVariable long id, @RequestBody TripPlanner newListing) {
        Optional<TripPlanner> optional = repository.findById(id);
        if (optional.isPresent()) { // Good ID
            TripPlanner listing = optional.get(); // value from findByID
            listing.setName(newListing.getName()); // value from findByID
            listing.setPacking(newListing.getPacking()); // value from findByID
            listing.setTravel(newListing.getTravel()); // value from findByID
            listing.setFood(newListing.getFood()); // value from findByID
            listing.setHotel(newListing.getHotel()); // value from findByID
            listing.setActivities(newListing.getActivities()); // value from findByID
            listing.setNotes(newListing.getNotes());
            repository.save(listing);
            return new ResponseEntity<>(listing, HttpStatus.OK); // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}