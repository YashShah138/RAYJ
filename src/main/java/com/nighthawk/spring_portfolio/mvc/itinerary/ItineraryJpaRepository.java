package com.nighthawk.spring_portfolio.mvc.itinerary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

// JPA is an object-relational mapping (ORM) to persistent data, originally relational databases (SQL). Today JPA implementations has been extended for NoSQL.
public interface ItineraryJpaRepository extends JpaRepository<Itinerary, Long> {
    // JPA has many built in methods, these few have been prototyped for this application
    void save(String Joke);
    // List<Itinerary> findAllByOrderByItineraryIdAsc();
    List<Itinerary> findByNameIgnoreCase(String name);
    // List<Itinerary> findByItineraryIgnoreCase(String name);
    // List<Itinerary> findByEmail(String email);
}
