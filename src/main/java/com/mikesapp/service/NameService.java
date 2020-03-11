package com.mikesapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mikesapp.model.Person;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NameService {

    private String url = "http://localhost:8081/";

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
        return template.exchange(url, HttpMethod.POST, httpEntity, String.class);
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