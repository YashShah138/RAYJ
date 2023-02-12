package com.nighthawk.spring_portfolio.mvc.itinerary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.nighthawk.spring_portfolio.mvc.itinerary.Itinerary;
import com.nighthawk.spring_portfolio.mvc.itinerary.ItineraryJpaRepository;


// import static org.junit.Assert.fail;

import javax.persistence.*;

@Data  // Annotations to simplify writing code (ie constructors, setters)
@NoArgsConstructor
// @AllArgsConstructor
@Entity // Annotation to simplify creating an entity, which is a lightweight persistence domain object. Typically, an entity represents a table in a relational database, and each entity instance corresponds to a row in that table.
public class Itinerary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique=true, nullable=false)
    private Long itineraryId;

    @Column(unique=true, nullable=false)
    private String name;
    
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
    private String notes;

    public String toString() {
        return ("{ \"ID\": " + this.itineraryId + "\"Itinerary Name\": " + this.name + ", " + "\"Description\": " + this.description + ", " + "\"Packing\": " + this.packing
                + ", " + "\"Travel\": " + this.travel + ", " + "\"Food\": " + this.food + ", " + " \"Hotel\": "
                + this.hotel + ", " + "\"Activities\": " + this.activities + " \"Important Notes\": " + this.notes + "}");
    }

    public Itinerary (Long itineraryId, String name, String description, String packing, String travel, String food, String hotel, String activities, String notes) {
        this.itineraryId = itineraryId;
        this.name = name;
        this.description = description;
        this.packing = packing;
        this.travel = travel;
        this.food = food;
        this.hotel = hotel;
        this.activities = activities;
        this.notes = notes;
    }
    
    public static void main(String[] args) {
        Itinerary FirstItinerary = new Itinerary();
        FirstItinerary.setItineraryId(112342412L);
        FirstItinerary.setName("My First Itinerary");
        FirstItinerary.setDescription("Trip to Hawaii");
        FirstItinerary.setPacking("Clothes, toiletries, swimgear");
        FirstItinerary.setTravel("Airplane");
        FirstItinerary.setFood("Local restaurants");
        FirstItinerary.setHotel("Trivago");
        FirstItinerary.setActivities("Snorkeling, hiking, cultural festival");
        FirstItinerary.setNotes("Don't forget allergy medication");
        System.out.println(FirstItinerary.toString());
    }
}
