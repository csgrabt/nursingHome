package com.example.nursinghome;


import com.example.nursinghome.address.UpdateAddressCommand;
import com.example.nursinghome.elder.CreateElderCommand;
import com.example.nursinghome.elder.ElderDTO;
import com.example.nursinghome.elder.ElderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ElderControllerRestTemplateIT {
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

    @Test
    void testDelete() {

        ElderDTO elderDTO = template.postForObject("/api/elders",
                new CreateElderCommand("John Doe", LocalDate.of(2000, 10, 1)), ElderDTO.class);

        template.postForObject("/api/elders",
                new CreateElderCommand("Jack Doe", LocalDate.of(2000, 10, 1)), ElderDTO.class);

        template.postForObject("/api/elders",
                new CreateElderCommand("Jane Doe", LocalDate.of(2000, 10, 1)), ElderDTO.class);

        template.delete("/api/elders/" + elderDTO.getId() + "/delete");


        List<ElderDTO> elderDTOs =
                template.exchange(
                        "/api/elders",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<ElderDTO>>() {
                        }).getBody();

        assertThat(elderDTOs)
                .extracting(ElderDTO::getName)
                .containsExactly("Jack Doe", "Jane Doe");

    }

    @Test
    void notFoundElderTest() {
        Problem result = template.getForObject("/api/elders/100", Problem.class);

        assertEquals(URI.create("Elder/not-found"), result.getType());
        assertEquals(Status.NOT_FOUND, result.getStatus());
    }

}
