package com.example.nursinghome;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    }
}