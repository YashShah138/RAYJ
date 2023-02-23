package com.nighthawk.spring_portfolio.mvc.blacklist;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nighthawk.spring_portfolio.mvc.jwt.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;

@RestController
@RequestMapping("/blacklist")
public class BlacklistedJwtApiController {

    @Autowired
    private BlacklistedJwtJpaRepository repository;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

    @GetMapping("/")
    public ResponseEntity<List<BlacklistedJwt>> getAllBlacklistedJwt() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @PostMapping( "/logout")
    public ResponseEntity<Object> postPerson(@CookieValue("jwt") String str) {
        cleanBlacklist();
        BlacklistedJwt jwt = new BlacklistedJwt(str);
        repository.save(jwt);
        return new ResponseEntity<>("You have logged out", HttpStatus.CREATED);
    }

    private void cleanBlacklist() {
        for (BlacklistedJwt jwt : repository.findAll()) {
            String token = jwt.getBlacklistedJwt();
            System.out.println(token);
            try {
                jwtTokenUtil.isTokenExpired(token);
            } catch(ExpiredJwtException e) {
                repository.deleteById(jwt.getId());
            }
        }
    }
}