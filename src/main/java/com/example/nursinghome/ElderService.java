package com.example.nursinghome;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ElderService {
    private ModelMapper modelMapper;
    private ElderRepository repository;

    public ElderDTO createElder(CreateElderCommand command) {
        Elder elder = new Elder(command.getName(), command.getDateOfBirth());
        repository.save(elder);
        return modelMapper.map(elder, ElderDTO.class);
    }
}
