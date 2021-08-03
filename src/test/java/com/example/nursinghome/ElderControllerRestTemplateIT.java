package com.example.nursinghome;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.shouldHaveThrown;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ElderControllerRestTemplateIT {
    @Autowired
    TestRestTemplate template;
    @Autowired
    ElderService elderService;

    @BeforeEach
    void deleteAll() {
        elderService.deleteAll();
    }

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

    @Test
    void testUpdateAddressWhenAddressIsNull() {
        ElderDTO elderDTO = template.postForObject("/api/elders",
                new CreateElderCommand("John Doe", LocalDate.of(2000, 10, 1)), ElderDTO.class);


        template.put("/api/elders/" + elderDTO.getId() + "/address",
                new UpdateAddressCommand("5400", "Xuzhou", "Chengdu street", "98"));

        ElderDTO elder = template.getForObject("/api/elders/" + elderDTO.getId(), ElderDTO.class);

        assertEquals("Xuzhou", elder.getAddress().getCity());

        template.put("/api/elders/" + elderDTO.getId() + "/address",
                new UpdateAddressCommand("5400", "Xuzhou", "Chengdu street", "9"));

    }

    @Test
    void testUpdateAddressWhenAddressIsNotNull() {
        ElderDTO elderDTO = template.postForObject("/api/elders",
                new CreateElderCommand("John Doe", LocalDate.of(2000, 10, 1)), ElderDTO.class);


        template.put("/api/elders/" + elderDTO.getId() + "/address",
                new UpdateAddressCommand("5400", "Xuzhou", "Chengdu street", "98"));


        template.put("/api/elders/" + elderDTO.getId() + "/address",
                new UpdateAddressCommand("5400", "Xuzhou", "Chengdu street", "9"));

        ElderDTO elder2 = template.getForObject("/api/elders/" + elderDTO.getId(), ElderDTO.class);

        assertEquals("9", elder2.getAddress().getHouseNumber());
    }


}
