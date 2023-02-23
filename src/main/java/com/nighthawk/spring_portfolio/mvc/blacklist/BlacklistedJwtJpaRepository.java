package com.nighthawk.spring_portfolio.mvc.blacklist;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlacklistedJwtJpaRepository extends JpaRepository<BlacklistedJwt, Integer> {
    List<BlacklistedJwt> findAll();
    Optional<BlacklistedJwt> findByBlacklistedJwt(String string);
}
