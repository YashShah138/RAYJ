package com.nighthawk.spring_portfolio.mvc.front;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.HashMap;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // annotation to create a RESTful web services
@RequestMapping("/api/front")  //prefix of API
public class FrontApiController {
    private JSONObject body; //last run result
    private HttpStatus status; //last run status
    String last_run = null; //last run day of month

    // GET Covid 19 Stats
    @GetMapping("/10")   //added to end of prefix as endpoint
    public ResponseEntity<JSONObject> getFront() {

        //calls API once a day, sets body and status properties
        String today = new Date().toString().substring(0,10); 
        if (last_run == null || !today.equals(last_run))
        {
            try {  //APIs can fail (ie Internet or Service down)
                
                //RapidAPI header
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://billboard3.p.rapidapi.com/hot-100?date=2022-07-07&range=1-10"))
                    .header("X-RapidAPI-Key", "1d9c0e5dd4msh00cea2fa8d7699fp1dfecdjsn1cf8da6644a9")
                    .header("X-RapidAPI-Host", "billboard3.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

                //RapidAPI request and response
                HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println(response.body());
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("data", response.body());
                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(map);
                //JSONParser extracts text body and parses to JSONObject
                // this.body = (JSONObject) response.body();
                this.body = (JSONObject) new JSONParser().parse(json);
                this.status = HttpStatus.OK;  //200 success
                this.last_run = "today";
            }
            catch (Exception e) {  //capture failure info
                HashMap<String, String> status = new HashMap<>();
                status.put("status", "RapidApi failure: " + e);

                //Setup object for error
                this.body = (JSONObject) status;
                this.status = HttpStatus.INTERNAL_SERVER_ERROR; //500 error
                this.last_run = null;
            }
        }

        //return JSONObject in RESTful style
        return new ResponseEntity<>(body, status);
    }
}