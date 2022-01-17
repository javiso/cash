package com.example.project.project.integrationTest;

import com.example.project.project.dto.LoanApi;
import com.example.project.project.dto.PageApi;
import com.example.project.project.model.Loan;
import com.example.project.project.model.User;
import com.example.project.project.repository.LoanRepository;
import com.example.project.project.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Integration test suite for Loan request")
public class LoanControllerTest {

    private static String URL = "http://localhost:";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void userSuccessfulResponseTest(){
        User userTest = new User("Pato","Lucas","patoLucas@gmail.com");
        Loan loanTest = new Loan(new BigDecimal("3.3"));
        loanTest.setUser(userTest);
        userTest.setLoans(new HashSet<>(Arrays.asList(loanTest)));
        User user= userRepository.save(userTest);
        String url = URL + port + "/cash/v1/loan/";

        ParameterizedTypeReference<PageApi<LoanApi>> parameterizedTypeReference = new ParameterizedTypeReference<>() {};
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(url)
                .queryParam("page","0")
                .queryParam("size","15")
                .queryParam("id_user",user.getId());

         ResponseEntity<PageApi<LoanApi>> response = restTemplate.exchange(uriComponentsBuilder.buildAndExpand().toUri(), HttpMethod.GET, null, parameterizedTypeReference);
         PageApi<LoanApi> pageLoan = response.getBody();

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(pageLoan.getItems());
        assertEquals(1, pageLoan.getItems().size());

        LoanApi loanResponse = (LoanApi) pageLoan.getItems().toArray()[0];
        assertEquals(user.getId(), loanResponse.getIdUser());
        assertEquals(0, pageLoan.getPaging().getPage());
        assertEquals(15, pageLoan.getPaging().getSize());
    }
}
