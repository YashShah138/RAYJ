package com.nighthawk.spring_portfolio.mvc.budget;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.nighthawk.spring_portfolio.mvc.budget.Budget;
import com.nighthawk.spring_portfolio.mvc.budget.BudgetJpaRepository;


// import static org.junit.Assert.fail;

import javax.persistence.*;

@Data  // Annotations to simplify writing code (ie constructors, setters)
@NoArgsConstructor
// @AllArgsConstructor
@Entity // Annotation to simplify creating an entity, which is a lightweight persistence domain object. Typically, an entity represents a table in a relational database, and each entity instance corresponds to a row in that table.
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique=true, nullable=false)
    private Integer trip;

    @Column(unique=true, nullable=false)
    private String name;
    
    @Column(nullable=false)
    private Integer airport;

    @Column(nullable=false)
    private Integer rental;
    
    @Column(nullable=false)
    private Integer transport;

    @Column(nullable=false)
    private Integer hotel1;

    @Column(nullable=false)
    private Integer hotel2;

    public Budget (Integer trip, String name, Integer airport, Integer rental, Integer transport, Integer hotel1, Integer hotel2) {
        this.trip = trip;
        this.name = name;
        this.airport = airport;
        this.rental = rental;
        this.transport = transport;
        this.hotel1 = hotel1;
        this.hotel2 = hotel2;
    }

    public Integer getId() {
        return this.trip;
    }

    public String toString() {
        return ("{ \"Trip\": " + this.trip + "\"Trip Name\": " + this.name + ", " + "\"Airport\": " + this.airport + ", " + "\"Rental Car\": " + this.rental
                + ", " + "\"Other Transport\": " + this.transport + ", " + "\"1st Hotel\": " + this.hotel1 + ", " + " \"2nd Hotel\": "
                + this.hotel2 + "\"3rd Hotel\": " + "}");
    }
    
    public static void main(String[] args) {
        Budget Budget1 = new Budget();
        Budget1.setTrip(01);
        Budget1.setName("First Trip Budget");
        Budget1.setAirport(2500);
        Budget1.setRental(300);
        Budget1.setTransport(100);
        Budget1.setHotel1(400);
        Budget1.setHotel2(200);
        System.out.println(Budget1.toString());
    }
}