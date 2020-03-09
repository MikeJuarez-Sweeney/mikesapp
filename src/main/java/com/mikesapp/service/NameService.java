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

    private String url = "Http://localhost:8081/";
    //private String url1 = "Http://localhost:8081/";

    private final ObjectMapper objectMapper = new ObjectMapper();

    public ResponseEntity<String> getPersonList() {

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, null, String.class);
        return response;
    }

    public ResponseEntity<Person> getPersonById(String id) {

        RestTemplate template = new RestTemplate();
        System.out.printf("url is:"+url+id);
        //UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance().host("localhost").port(8081).path(id);
        ResponseEntity<Person> response = template.exchange(url+id, HttpMethod.GET, null, Person.class);
        return response;
    }

    public ResponseEntity<String> addPerson(Person person) {

        RestTemplate template = new RestTemplate();
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

        RestTemplate template = new RestTemplate();
        HttpEntity<String> httpEntity = null;

        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json");
        try {
            httpEntity = new HttpEntity<>(objectMapper.writeValueAsString(person), headers);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ResponseEntity<String> response = template.exchange(url+id, HttpMethod.PUT, httpEntity, String.class);
        return response;
    }

    public ResponseEntity<String> deletePerson(int id) {

        RestTemplate template = new RestTemplate();
        HttpEntity<String> httpEntity = null;

        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json");
        try {
            httpEntity = new HttpEntity<>(objectMapper.writeValueAsString(id), headers);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ResponseEntity<String> response = template.exchange(url+id, HttpMethod.DELETE, httpEntity, String.class);
        return response;
    }
}
