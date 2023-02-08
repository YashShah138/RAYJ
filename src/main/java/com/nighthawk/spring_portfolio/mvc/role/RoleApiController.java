package com.nighthawk.spring_portfolio.mvc.role;

import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nighthawk.spring_portfolio.mvc.person.Person;
import com.nighthawk.spring_portfolio.mvc.person.PersonJpaRepository;

import java.util.*;

@RestController // annotation to simplify the creation of RESTful web services
@RequestMapping("/api/role")
public class RoleApiController {

    // Autowired enables Control to connect HTML and POJO Object to database easily for CRUD operations
    @Autowired
    private RoleJpaRepository roleRepository;

    @Autowired
    private PersonJpaRepository personRepository;

    /*
    GET List of Roles
     */
    @GetMapping("/")
    public ResponseEntity<List<Role>> getUsers() {
        return new ResponseEntity<>(roleRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/createRole")
    public ResponseEntity<Object> createRole(@RequestParam("role") String roleN) {
    Role role = roleRepository.findByName(roleN);
    if (role != null) {
        return new ResponseEntity<>(roleN + " already exists", HttpStatus.OK);
    } else {
        roleRepository.save(new Role(null, roleN));
        return new ResponseEntity<>(roleN + " has been created", HttpStatus.BAD_REQUEST);
    }
    }

    @PostMapping("/deleteRole")
    public ResponseEntity<Object> deleteRole(@RequestParam("role") String roleN) {
    Role role = roleRepository.findByName(roleN);
    if (role != null) {
        roleRepository.delete(role);
        return new ResponseEntity<>(roleN + " has been deleted", HttpStatus.OK);
    } else {
        return new ResponseEntity<>("role not found", HttpStatus.BAD_REQUEST);
    }
    }

    /* Update Like
     * @PutMapping annotation is used for mapping HTTP PUT requests onto specific handler methods.
     * @PathVariable annotation extracts the templated part {id}, from the URI
     */
    @PostMapping("/updateRole")
    public ResponseEntity<Object> updateRole(@RequestParam("email") String email, @RequestParam("role") String roleN) {
    Person person = personRepository.findByEmail(email);
    if (person != null) {
        Role role = roleRepository.findByName(roleN);
        if (role != null) {
            for (Role r: person.getRoles()) {
                if (r.getName().equals(roleN)) {
                    return new ResponseEntity<>("user already has role", HttpStatus.BAD_REQUEST);
                }
            }
            person.getRoles().add(role);
            personRepository.save(person);
            return new ResponseEntity<>(email + " now has " + roleN + " role", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("role not found", HttpStatus.BAD_REQUEST);
        }
    }
    return new ResponseEntity<>("user not found", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/removeRole")
    public ResponseEntity<Object> removeRole(@RequestParam("email") String email, @RequestParam("role") String roleN) {
    Person person = personRepository.findByEmail(email);
    if (person != null) {
        Role role = roleRepository.findByName(roleN);
        if (role != null) {
            for (Role r: person.getRoles()) {
                System.out.println(r.getName());
                if (r.getName().equals(roleN)) {
                    person.getRoles().remove(role);
                    personRepository.save(person);
                    return new ResponseEntity<>(email + " no longer has " + roleN + " role", HttpStatus.OK);
                }
            }
            return new ResponseEntity<>("user does not have this role", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("role not found", HttpStatus.BAD_REQUEST);
        }
    }
    return new ResponseEntity<>("user not found", HttpStatus.BAD_REQUEST);
    }
}