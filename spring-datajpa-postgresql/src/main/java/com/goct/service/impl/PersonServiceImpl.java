package com.goct.service.impl;

import com.goct.dto.PersonDto;
import com.goct.entity.Address;
import com.goct.entity.Person;
import com.goct.repository.AddressRepository;
import com.goct.repository.PersonRepository;
import com.goct.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository; // Construction Injection - if its final, then u need to assign.
    private final AddressRepository addressRepository;

    @Override
    @Transactional
    public PersonDto save(PersonDto personDto) {
        Assert.notNull(personDto.getName(), "Name is required.");

        Person person = new Person();
        person.setName(personDto.getName());
        person.setSurname(personDto.getSurname());
        final Person personDb = personRepository.save(person);

        List<Address> addressList = new ArrayList<>();

        personDto.getAddress().forEach(item -> {
            Address address = new Address();
            address.setAddress(item);
            address.setAddressType(Address.AddressType.OTHERS);
            address.setActive(true);
            address.setPerson(personDb);
            addressList.add(address);
        });
        addressRepository.saveAll(addressList);
        personDto.setId(personDb.getId());
        return personDto;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<PersonDto> getAll() {
        final List<PersonDto> personDtos = new ArrayList<>();
        List<Person> personList = personRepository.findAll();
        personList.forEach(item -> {
            PersonDto personDto = new PersonDto();
            personDto.setId(item.getId());
            personDto.setName(item.getName());
            personDto.setSurname(item.getSurname());
            personDto.setAddress(item.getAddresses().stream().map(Address::getAddress).collect(Collectors.toList()));
            personDtos.add(personDto);
        });
        return personDtos;
    }

    @Override
    public Page<Person> getAll(Pageable pageable) {
        return null;
    }
}
