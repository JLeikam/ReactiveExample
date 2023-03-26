package com.example.reactiveexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // TODO: Modify this GET endpoint so that it returns all persons
    @GetMapping
    public Flux<Person> getAllPersons() {
        // Hint: It should return Flux<Person>
        return null;
    }


    // TODO: Modify this POST endpoint so that it creates a new person
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Person> createNewPerson(@RequestBody Person person) {
        // Hint: It should return Mono<Person>
        return null;
    }

    // TODO: Modify this PUT endpoint so that it updates an existing person by ID
    @PutMapping("/{id}")
    public Mono<Person> updatePersonById(@PathVariable String id, @RequestBody Person person) {
        // Hint: It should update the person's name and email
        // Hint: It should return Mono<Person>
        return null;
    }

    private Mono<Person> updateAndSave(Person existingPerson, Person newPerson) {
        updatePersonDetails(existingPerson, newPerson);
        return personRepository.save(existingPerson);
    }

    private void updatePersonDetails(Person existingPerson, Person newPerson) {
        existingPerson.setName(newPerson.getName());
        existingPerson.setEmail(newPerson.getEmail());
    }

}
