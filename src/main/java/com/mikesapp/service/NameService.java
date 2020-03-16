package com.mikesapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mikesapp.exception.NoConnectionException;
import com.mikesapp.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;

@Service
public class NameService {

    private String url = "http://localhost:8081/";

    NoConnectionException noConnectionException;

    private static final Logger logger = LoggerFactory.getLogger(HttpClient.class);

    private final ObjectMapper objectMapper = new ObjectMapper();
    private RestTemplate template = new RestTemplate();

    public ResponseEntity<String> getPersonList() {
        return template.exchange(url, HttpMethod.GET, null, String.class);

    }

    public ResponseEntity<Person> getPersonById(String id) {
        //UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance().host("localhost").port(8081).path(id);
        return template.exchange(url + id, HttpMethod.GET, null, Person.class);
    }

    public ResponseEntity<String> addPerson(Person person) {

        HttpEntity<String> httpEntity = null;

        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json");
        try {
            httpEntity = new HttpEntity<>(objectMapper.writeValueAsString(person), headers);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ResponseEntity<String> response = template.exchange(url, HttpMethod.POST, httpEntity, String.class);

        return response;
    }

    public ResponseEntity<String> updatePerson(int id, Person person) {

        HttpEntity<String> httpEntity = null;

        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json");
        try {
            httpEntity = new HttpEntity<>(objectMapper.writeValueAsString(person), headers);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return template.exchange(url + id, HttpMethod.PUT, httpEntity, String.class);
    }

    public ResponseEntity<String> deletePerson(int id) {

        HttpEntity<String> httpEntity = null;

        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json");
        try {
            httpEntity = new HttpEntity<>(objectMapper.writeValueAsString(id), headers);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return template.exchange(url + id, HttpMethod.DELETE, httpEntity, String.class);
    }
}