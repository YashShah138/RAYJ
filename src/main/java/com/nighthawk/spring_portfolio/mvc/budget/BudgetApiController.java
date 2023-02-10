package com.nighthawk.spring_portfolio.mvc.budget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nighthawk.spring_portfolio.mvc.budget.Budget;
import com.nighthawk.spring_portfolio.mvc.budget.BudgetJpaRepository;


import java.util.*;

@RestController // annotation to simplify the creation of RESTful web services
@RequestMapping("/api/budget")
public class BudgetApiController {

    // Autowired enables Control to connect HTML and POJO Object to database easily for CRUD operations
    @Autowired
    private BudgetJpaRepository repository;

    // @Autowired
    // private ItineraryJpaRepository

    /*
    GET List of Jokes
     */
    @GetMapping("/")
    public ResponseEntity<List<Budget>> getBudget() {
        return new ResponseEntity<>( repository.findAll(), HttpStatus.OK);
    }

    // /*
    // GET List of itineraries
    //  */
    @GetMapping("/{id}")
    public ResponseEntity<Budget> getBudget(@PathVariable int id) {
        Optional<Budget> optional = repository.findAllByOrderByBudgetIdAsc();
        if (optional.isPresent()) {  // Good ID
            Budget budget = optional.get();  // value from findByID
            return new ResponseEntity<>(budget, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);       
    }
    
    //create
    @GetMapping("/newbudget")
    public ResponseEntity<List<Budget>> createBudgetInfo(Integer trip, String name, Integer airport, Integer rental, Integer transport, Integer hotel1, Integer hotel2) {
    Budget newBudget = new Budget(trip, name, airport, rental, transport, hotel1, hotel2);
    repository.save(newBudget);
    return new ResponseEntity<>(repository.findAllByOrderByBudgetIdAsc(), HttpStatus.OK);
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Budget> deleteBudget(@PathVariable int id) {
        Optional<Budget> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Budget budget = optional.get();  // value from findByID
            repository.deleteById(id);  // value from findByID
            return new ResponseEntity<>(budget, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
    }

}
