package com.nighthawk.spring_portfolio.mvc.light;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/api/light")
public class LightApiController {

    private LightBoard lightBoard;
    private JsonNode json;

    @GetMapping("/{rows}/{cols}")
    public ResponseEntity<JsonNode> generateLightBoard(@PathVariable int rows, @PathVariable int cols)
            throws JsonMappingException, JsonProcessingException {
        lightBoard = new LightBoard(rows, cols);

        // Create objectmapper to convert String to JSON
        ObjectMapper mapper = new ObjectMapper();
        json = mapper.readTree(lightBoard.toString());

        return ResponseEntity.ok(json);
    }
}