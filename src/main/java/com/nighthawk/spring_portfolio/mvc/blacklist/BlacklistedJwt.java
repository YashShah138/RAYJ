package com.nighthawk.spring_portfolio.mvc.blacklist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BlacklistedJwt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String blacklistedJwt;

    public BlacklistedJwt(String jwt) {
        this.blacklistedJwt = jwt;
    }
}
