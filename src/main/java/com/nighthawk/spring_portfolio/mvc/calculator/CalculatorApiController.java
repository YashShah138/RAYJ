package com.nighthawk.spring_portfolio.mvc.calculator;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorApiController {
    @PostMapping("/")
    public ResponseEntity<JSONObject> calculate(@RequestBody final String expression) {
        try {
            Calculator calculatedExpression = new Calculator(expression);
            return new ResponseEntity<JSONObject>(calculatedExpression.toJSON(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return null;
            // return new ResponseEntity<JSONObject>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    // @PostMapping("/calc/")
    // public ResponseEntity<String> calc(@RequestBody final String expression) {
    //     try {
    //         Calculator calculatedExpression = new Calculator(expression);
    //         return new ResponseEntity<String>(calculatedExpression.toJSON(), HttpStatus.ACCEPTED);
    //     } catch (Exception e) {
    //         return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    //     }
    // }
}
