package com.acme.observability.demo.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class RestEndpoint {

    private static final Logger log = LoggerFactory.getLogger(RestEndpoint.class);

    @GetMapping("/server/{userId}")
    String userName(@PathVariable("userId") String userId) {
        log.info("Got a request! User Id is: {}", userId);
        return "The user ID is: " + userId; 
    }
}
