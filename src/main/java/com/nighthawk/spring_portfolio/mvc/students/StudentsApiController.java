package com.nighthawk.spring_portfolio.mvc.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // annotation to simplify the creation of RESTful web services
@RequestMapping("/api/students") // all requests in file begin with this URI
public class StudentsApiController {

    // Autowired enables Control to connect URI request and POJO Object to easily
    // for Database CRUD operations
    @Autowired
    private StudentsJpaRepository repository;

    /*
     * GET List of Jokes
     * 
     * @GetMapping annotation is used for mapping HTTP GET requests onto specific
     * handler methods.
     */
    @GetMapping("/")
    public ResponseEntity<List<Students>> getStudents() {
        // ResponseEntity returns List of Jokes provide by JPA findAll()
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<Students>> searchStudents(@PathVariable String name) {
        List<Students> listings = repository.findByNameIgnoreCase(name);
        return new ResponseEntity<>(listings,HttpStatus.OK);
    }

    //create
    @PostMapping("/create/{name}/{college}/{team}")
    public ResponseEntity<Students> createStudent(@PathVariable String name,
            @PathVariable String college, @PathVariable Boolean team) {
        repository.saveAndFlush(new Students(null, name, college, team));
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    //update
    @PutMapping("/update/{id}")
    public ResponseEntity<Students> updateListing(@PathVariable long id, @RequestBody Students changes) {
        Optional<Students> optional = repository.findById(id);
        if (optional.isPresent()) { // Good ID
            Students a = optional.get(); // value from findByID
            a.setName(changes.getName()); // value from findByID
            a.setCollege(changes.getCollege()); // value from findByID
            a.setTeam(changes.getTeam()); // value from findByID
            repository.save(a);
            return new ResponseEntity<>(a, HttpStatus.OK); // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Students> deleteStudent(@PathVariable long id) {
        Optional<Students> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Students activity = optional.get();  // value from findByID
            repository.deleteById(id);  // value from findByID
            return new ResponseEntity<>(activity, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}