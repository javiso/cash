package com.example.project.project.integrationTest;

import com.example.project.project.model.User;
import com.example.project.project.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Integration test suite for User request")
public class UserControllerTest {

    private static String URL = "http://localhost:";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void userSuccessfulResponseTest(){
        User userTest = new User("Pato","Lucas","patoLucas@gmail.com");
        Long idUser = userRepository.save(userTest).getId();
        String url = URL + port + "/cash/v1/user/" + idUser;

        ResponseEntity<User> response = restTemplate.getForEntity(url, User.class);
        User user = response.getBody();

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(user.getId());
        assertEquals("Pato",user.getFirstName());
        assertEquals("Lucas",user.getLastName());
        assertEquals("patoLucas@gmail.com",user.getEmail());
    }

    @Test
    public void userUnsuccessfulResponseTest(){
        String url = URL + port + "/cash/v1/user/" + "99";
        ResponseEntity<User> response = restTemplate.getForEntity(url, User.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody().getFirstName());
        assertNull(response.getBody().getLastName());
        assertNull(response.getBody().getFirstName());
    }

    @Test
    public void createUserTest() {
        String url = URL + port + "/cash/v1/user/";
        User userTest = new User("Pato","Lucas","patoLucas@gmail.com");

        ResponseEntity<User> response = restTemplate.postForEntity(url, userTest, User.class);
        User user = response.getBody();

        assertNotNull(user);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Pato",user.getFirstName());
        assertEquals("Lucas",user.getLastName());
        assertEquals("patoLucas@gmail.com",user.getEmail());
        assertEquals(0, user.getLoans().size());
    }

    @Test
    public void createUnsuccessfulUserTest() {
        String url = URL + port + "/cash/v1/user/";
        User userTest = new User("Pato","Lucas","patoLucasgmailcom");

        ResponseEntity<User> response = restTemplate.postForEntity(url, userTest, User.class);
        User user = response.getBody();

        assertNotNull(user);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void deleteUser() {
        User userTest = new User("Pato","Lucas","patoLucas@gmail.com");
        Long idUser = userRepository.save(userTest).getId();
        String url = URL + port + "/cash/v1/user/" + idUser;
        restTemplate.delete(url);
        ResponseEntity<User> response = restTemplate.getForEntity(url, User.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}