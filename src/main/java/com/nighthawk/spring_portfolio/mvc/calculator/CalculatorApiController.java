package com.nighthawk.spring_portfolio.mvc.calculator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorApiController {
    @GetMapping("/{expression}")
    public ResponseEntity<String> getResult(@PathVariable String expression) {

        // Returns jsonified result of expression with tokens and everything
        Calculator newCalc = new Calculator(expression);
        String result = newCalc.toJSON();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    
    // private Calculator calculator;
    // private JsonNode json;
    // @GetMapping("/{expression}")
    // public ResponseEntity<String> calculate(@PathVariable final String expression) throws JsonMappingException, JsonProcessingException {
    //     Calculator calculatedExpression = new Calculator(expression);
    //     ObjectMapper mapper = new ObjectMapper();
    //     json = mapper.readTree(calculatedExpression.toString());

    //     return ResponseEntity.ok(json);
    // }

    // @GetMapping("/isLeapYear/{year}")
    // public ResponseEntity<JsonNode> getIsLeapYear(@PathVariable int year) throws JsonMappingException, JsonProcessingException {
    //   // Backend Year Object
    //   Year year_obj = new Year();
    //   year_obj.setYear(year);  // evaluates Leap Year

    //   // Turn Year Object into JSON
    //   ObjectMapper mapper = new ObjectMapper(); 
    //   JsonNode json = mapper.readTree(year_obj.isLeapYearToString()); // this requires exception handling

    //   return ResponseEntity.ok(json);  // JSON response, see ExceptionHandlerAdvice for throws
    // }
}
