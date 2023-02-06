package com.nighthawk.spring_portfolio.mvc.itinerary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// import static org.junit.Assert.fail;

import javax.persistence.*;

@Data  // Annotations to simplify writing code (ie constructors, setters)
@NoArgsConstructor
@AllArgsConstructor
@Entity // Annotation to simplify creating an entity, which is a lightweight persistence domain object. Typically, an entity represents a table in a relational database, and each entity instance corresponds to a row in that table.
public class Itinerary {
    @Id
    @Column(unique=true, nullable=false)
    private String itineraryName;

    @Column(nullable=false)
    private String description;

    @Column(nullable=false)
    private String packing;
    
    @Column(nullable=false)
    private String travel;

    @Column(nullable=false)
    private String food;

    @Column(nullable=false)
    private String hotel;

    @Column(nullable=false)
    private String activities;

    @Column(nullable=false)
    private String importantNotes;
}
