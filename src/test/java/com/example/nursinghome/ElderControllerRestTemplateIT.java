package com.example.nursinghome;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ElderControllerRestTemplateIT {
    @Autowired
    TestRestTemplate template;
    @Autowired
    ElderService ElderService;


    @Test
    void testListEmployees() {

        template.postForObject("/api/elders", new CreateElderCommand("John Doe", LocalDate.of(2000, 10, 1)), ElderDTO.class);

        template.postForObject("/api/elders", new CreateElderCommand("Jack Doe", LocalDate.of(1990, 10, 1)), ElderDTO.class);



        List<ElderDTO> list = template.exchange("/api/elders", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<ElderDTO>>() {
                }).getBody();

        assertThat(list)
                .extracting(ElderDTO::getName)
                .containsExactly("John Doe", "Jack Doe");
    }


}
