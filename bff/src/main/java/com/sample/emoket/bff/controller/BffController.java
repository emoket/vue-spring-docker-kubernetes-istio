package com.sample.emoket.bff.controller;

import com.sample.emoket.bff.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController()
@RequestMapping("/api")
public class BffController {

    RestTemplate restTemplate = new RestTemplate();

    private static final Logger LOG = LoggerFactory.getLogger(BffController.class);


    // 스프링부트 백엔드 호출 서비스
    @Value("${backend.spring.host}")
    private String backendSpringHost;

    @Value("${backend.spring.port}")
    private String backendSpringPort;

    @GetMapping(path = "/hello/spring")
    public String getGreetingMessageFromSpring() {
        String url = "http://" + backendSpringHost + ":" + backendSpringPort + "/api/hello/spring";
        LOG.info(url);
        String resultMessage = restTemplate.getForObject(url, String.class);
        LOG.info(resultMessage);
        return resultMessage;
    }


    // 노드 백엔드 호출 서비스
    @Value("${backend.node.host}")
    private String backendNodeHost;

    @Value("${backend.node.port}")
    private String backendNodePort;

    @GetMapping(path = "/hello/node")
    public String getGreetingMessageFromNode() {
        String url = "http://" + backendNodeHost + ":" + backendNodePort + "/api/hello/node";
        LOG.info(url);
        String resultMessage = restTemplate.getForObject(url, String.class);
        LOG.info(resultMessage);
        return resultMessage;
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public String passingAddNewUser(@RequestParam String firstName, @RequestParam String lastName) {
        User user = new User(firstName, lastName);
        String url = "http://" + backendSpringHost + ":" + backendSpringPort + "/api/user";
        LOG.info(url);
        ResponseEntity<String> response = restTemplate.postForEntity(url, user, String.class);
        LOG.info(response.getBody());
        return response.getBody();
    }

    @GetMapping(path = "/user/{id}")
    public String passingGetUserById(@PathVariable("id") long id) {
        LOG.info("id : " + id); // id : 2 ok
        String url = "http://" + backendSpringHost + ":" + backendSpringPort + "/api/user/" + id;
        LOG.info(url); // ok
        String response = restTemplate.getForObject(url, String.class);
        LOG.info(response);
        return response;
    }



}
