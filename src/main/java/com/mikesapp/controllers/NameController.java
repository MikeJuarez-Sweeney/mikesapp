package com.mikesapp.controllers;

import com.mikesapp.model.Person;
import com.mikesapp.service.NameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class NameController {

    private NameService nameService = new NameService();

    @GetMapping(value = "/")
    public ResponseEntity<String> getAllPersons() {
        return nameService.getPersonList();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable String id) {
        return nameService.getPersonById(id);
    }

    @PostMapping(value = "/")
    public ResponseEntity<String> addPerson(@RequestBody Person person) {
        return nameService.addPerson(person);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updatePerson(@PathVariable int id,
                                               @RequestBody Person person) {
        return nameService.updatePerson(id, person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable int id) {
        return nameService.deletePerson(id);
    }


}