package com.example.nursinghome;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ElderService {
    private ModelMapper modelMapper;
    private ElderRepository repository;
    private AddressRepository addressRepository;

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

    public ElderDTO findElderById(long id) {
        Elder elder = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cannot find elder by id"));
        return modelMapper.map(elder, ElderDTO.class);
    }


    @Transactional
    public ElderDTOWithAddress updateAddress(long id, UpdateAddressCommand command) {
        Elder elder = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cannot find elder by id"));
        Address address = new Address(command.getZipCode(),
                command.getCity(),
                command.getStreet(),
                command.getHouseNumber());
        addressRepository.save(address);
        elder.setAddress(address);
        return modelMapper.map(elder, ElderDTOWithAddress.class);


    }
}
