package com.example.nursinghome;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<ElderDTO> listElders() {
        return repository
                .findAll()
                .stream()
                .map(n -> modelMapper
                        .map(n, ElderDTO.class))
                .collect(Collectors.toList());
    }
}
