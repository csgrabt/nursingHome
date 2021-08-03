package com.example.nursinghome.elder;

import com.example.nursinghome.address.Address;
import com.example.nursinghome.address.AddressRepository;
import com.example.nursinghome.address.UpdateAddressCommand;
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
        Elder elder = repository.findById(id).orElseThrow(() -> new ElderNotFoundException("Cannot find elder by id"));
        return modelMapper.map(elder, ElderDTO.class);
    }


    @Transactional
    public ElderDTO updateAddress(long id, UpdateAddressCommand command) {
        Elder elder = repository.findById(id).orElseThrow(() -> new ElderNotFoundException("Cannot find elder by id"));
        if (elder.getAddress() == null) {
            Address address = new Address(command.getZipCode(),
                    command.getCity(),
                    command.getStreet(),
                    command.getHouseNumber());
            addressRepository.save(address);
            elder.setAddress(address);
        } else {
            elder.getAddress().setCity(command.getCity());
            elder.getAddress().setZipCode(command.getZipCode());
            elder.getAddress().setStreet(command.getStreet());
            elder.getAddress().setHouseNumber(command.getHouseNumber());
        }
        return modelMapper.map(elder, ElderDTO.class);


    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void deleteElderById(Long id) {
        repository.delete(repository.findById(id).orElseThrow(
                () -> new ElderNotFoundException("Cannot delete Elder based on id: " + id))
        );
    }
}
