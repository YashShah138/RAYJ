package com.nighthawk.spring_portfolio.mvc.blacklist;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nighthawk.spring_portfolio.mvc.jwt.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;

@RestController
public class BlacklistedJwtApiController {

    @Autowired
    private BlacklistedJwtJpaRepository repository;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

    @GetMapping("/blacklist")
    public ResponseEntity<List<BlacklistedJwt>> getAllBlacklistedJwt() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @PostMapping( "/logout")
    public ResponseEntity<?> postPerson(@CookieValue("jwt") String str) {
        cleanBlacklist();
        BlacklistedJwt jwt = new BlacklistedJwt(str);
        repository.save(jwt);
		final ResponseCookie tokenCookie = ResponseCookie.from("jwt", "")
			.httpOnly(true)
			.secure(true)
			.path("/")
			.maxAge(0)
			.sameSite("None")
			// .domain("music.nighthawkcoders.tk")
			.build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, tokenCookie.toString()).build();
    }

    private void cleanBlacklist() {
        for (BlacklistedJwt jwt : repository.findAll()) {
            String token = jwt.getBlacklistedJwt();
            try {
                jwtTokenUtil.isTokenExpired(token);
            } catch(ExpiredJwtException e) {
                repository.deleteById(jwt.getId());
            }
        }
    }
}