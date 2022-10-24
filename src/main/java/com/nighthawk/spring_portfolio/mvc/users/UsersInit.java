// package com.nighthawk.spring_portfolio.mvc.users;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.stereotype.Component;

// @Component // Scans Application for ModelInit Bean, this detects CommandLineRunner
// public class UsersInit {
    
//     // Inject repositories
//     @Autowired UsersJpaRepository repository;
    
//     @Bean
//     CommandLineRunner run() {  // The run() method will be executed after the application starts
//         return args -> {
//             // Fail safe data validations

//             // starting jokes
//             final String[] usernameArray = {
//                 "aidan","jun","rithwik","yash"
//             };

//             // make sure Joke database is populated with starting jokes
//             for (String username : usernameArray) {
//                 List<Users> test = repository.findByUsernameIgnoreCase(username);  // JPA lookup
//                 if (test.size() == 0)
//                     repository.save(new Users(null, username, "password")); //JPA save
//             }
            
//         };
//     }
// }

