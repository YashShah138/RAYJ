package com.nighthawk.spring_portfolio.mvc.calculator;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    @PostMapping("/calculate")
    public ResponseEntity<JSONObject> calculate(@RequestBody final String expression) {
        try {
            Calculator calculatedExpression = new Calculator(expression);
            return new ResponseEntity<JSONObject>(calculatedExpression.toJSONObj(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return null;
            // return new ResponseEntity<JSONObject>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{expression}")
    public ResponseEntity<JsonNode> getIsLeapYear(@PathVariable String expression) throws JsonMappingException, JsonProcessingException {
      // Backend Year Object
      Calculator calculator_obj = new Calculator(expression);

      
      // Turn Year Object into JSON
      ObjectMapper mapper = new ObjectMapper(); 
      JsonNode json = mapper.readTree(calculator_obj.toJSON()); // this requires exception handling

      return ResponseEntity.ok(json);  // JSON response, see ExceptionHandlerAdvice for throws
      
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
