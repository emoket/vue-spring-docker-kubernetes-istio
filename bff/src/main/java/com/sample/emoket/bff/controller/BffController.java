package com.sample.emoket.bff.controller;

import com.sample.emoket.bff.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController()
@RequestMapping("/api")
public class BffController {

    RestTemplate restTemplate = new RestTemplate();

    private static final Logger LOG = LoggerFactory.getLogger(BffController.class);

    // 스프링부트 백엔드 호출 서비스
    @Value("${target.backend.spring.name}") private String backendSpringHost;

    // 노드 백엔드 호출 서비스
    @Value("${target.backend.node.name}") private String backendNodeHost;

    // Greeting Service for Spring
    @GetMapping(path = "/hello/spring")
    public ResponseEntity<String> getGreetingMessageFromSpring() {

        // Spring Backend Service 명칭이 없는 경우, 추가 진행 없이 종료
        if ( !Optional.ofNullable(backendSpringHost).filter(f -> !f.isEmpty()).isPresent() ) {
            LOG.info("No Spring Backend Service");
            return new ResponseEntity<>("No Spring Backend Service", HttpStatus.OK);
        }

        LOG.info("Call Spring Backend Service");

        // 백엔드 호출
        ResponseEntity<String> response = restTemplate.exchange(
            "http://" + backendSpringHost + "/api/hello/spring",
            HttpMethod.GET,
            null,
            String.class
        );

        if ( !Optional.ofNullable(response.getBody()).isPresent() ) {
            LOG.info("No data found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        LOG.info(response.getBody());

        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<String> getUserById(@PathVariable("id") long id) {

        // Spring Backend Service 명칭이 없는 경우, 추가 진행 없이 종료
        if ( !Optional.ofNullable(backendSpringHost).filter(f -> !f.isEmpty()).isPresent() ) {
            LOG.info("No Spring Backend Service");
            return new ResponseEntity<>("No Was Service", HttpStatus.OK);
        }

        LOG.info("Get User : " + id);

        // 백엔드 호출
        ResponseEntity<String> response = restTemplate.exchange(
            "http://" + backendSpringHost + "/api/user/" + id,
            HttpMethod.GET,
            null,
            String.class
        );

        if ( !Optional.ofNullable(response.getBody()).isPresent() ) {
            LOG.info("No data found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }


    // Greeting Service for Node
    @GetMapping(path = "/hello/node")
    public String getGreetingMessageFromNode() {

        // Node Backend Service 명칭이 없는 경우, 추가 진행 없이 종료
        if ( !Optional.ofNullable(backendNodeHost).filter(f -> !f.isEmpty()).isPresent() ) {
            LOG.info("No Node Backend Service");
            return "No Node Backend Service";
        }

        LOG.info("Call Node Backend Service");

        // Node Backend Service 호출
        ResponseEntity<String> response = restTemplate.exchange(
            "http://" + backendNodeHost + "/api/hello/node",
            HttpMethod.GET,
            null,
            String.class
        );

        if ( !Optional.ofNullable(response.getBody()).isPresent() ) {
            LOG.info("No data found");
            return "No data found";
        }

        LOG.info(response.getBody());

        return response.getBody();
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public String passingAddNewUser(@RequestParam String firstName, @RequestParam String lastName) {
        User user = new User(firstName, lastName);
        String url = "http://" + backendSpringHost + "/api/user";
        LOG.info(url);
        ResponseEntity<String> response = restTemplate.postForEntity(url, user, String.class);
        LOG.info(response.getBody());
        return response.getBody();
    }

}
