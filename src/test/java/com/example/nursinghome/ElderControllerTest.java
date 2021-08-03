package com.example.nursinghome;

import com.example.nursinghome.elder.CreateElderCommand;
import com.example.nursinghome.elder.ElderController;
import com.example.nursinghome.elder.ElderDTO;
import com.example.nursinghome.elder.ElderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ElderControllerTest {

    @Mock
    ElderService elderService;

    @InjectMocks
    ElderController elderController;

    @Test
    void createEmployee() {

        when(elderService.createElder(any())).thenReturn(
                new ElderDTO(1l, "John Doe")
        );

        ElderDTO elderDTO = elderController.createEmployee(new CreateElderCommand());

        assertEquals(elderDTO, new ElderDTO(1l, "John Doe"));
        verify(elderService).createElder(any());

    }

    @Test
    void listElders() {
        when(elderService.listElders()).thenReturn(
                List.of(
                        new ElderDTO(1l, "John Doe"),
                        new ElderDTO(1l, "Jack Doe")
                )
        );

        List<ElderDTO> elders = elderController.listElders();

        assertThat(elders).extracting(ElderDTO::getName).containsExactly("John Doe", "Jack Doe");
        verify(elderService).listElders();

    }
}