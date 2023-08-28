package com.acme.observability.demo.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
class RestEndpoint {

    private static final Logger log = LoggerFactory.getLogger(RestEndpoint.class);
    
    @GetMapping("/client/{userId}")
    String userName(@PathVariable("userId") String userId) {
        log.info("Got a request! User Id is: {}", userId);
        return userId;
    }
}
