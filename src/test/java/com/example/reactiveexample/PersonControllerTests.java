package com.example.reactiveexample;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = PersonController.class)
public class PersonControllerTests {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private PersonRepository personRepository;

    private Person fred;
    private Person alice;

    @BeforeEach
    public void setUp() {
        fred = new Person("1", "Fred",  "fred@baylor.edu");
        alice = new Person("2", "Alice", "alice@baylor.edu");


        //TODO 1.a: Add a mock for the personRepository to return a Flux of fred and alice on the findAll() method
        //Hint: use the when() method to set up the mock

        //TODO 2.a: Add a mock for the personRepository to return a Mono of fred on the save() method
        //Hint: use the when() method to set up the mock

        //TODO 3.a: Add a mock for the personRepository to return a Mono of fred on the findById() method
        //Hint: use the when() method to set up the mock

    }


    // TODO 1.b: Add a test method to test the getAllPersons() method of the PersonController
    @Test
    public void testGetAllPersons() {
        // Hint: Use the WebTestClient.get() method to get a list of Person objects from the /api/persons URI
    }

    // TODO 2.b: Add a test method to test the savePerson() method of the PersonController
    @Test
    public void testSavePerson() {
        // Hint: Use the WebTestClient.post() method to post a Person object to the /api/persons URI
    }

    // TODO 3.a: Complete this test method so that it tests the updatePerson() method of the PersonController
    @Test
    public void testUpdatePerson() {
        // Hint: Use the WebTestClient.put() method to put the updatedPerson object to the /api/persons URI
        Person updatedPerson = new Person("1", "Fred Update", "updated@baylor.edu");

    }
}
