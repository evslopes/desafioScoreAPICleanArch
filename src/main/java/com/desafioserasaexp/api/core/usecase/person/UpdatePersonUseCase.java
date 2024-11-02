package com.desafioserasaexp.api.core.usecase.person;

import com.desafioserasaexp.api.core.exception.PersonNotFoundException;
import com.desafioserasaexp.api.core.port.PersonRepository;
import com.desafioserasaexp.api.core.port.PersonService;
import com.desafioserasaexp.api.core.port.AddressService;
import com.desafioserasaexp.api.entity.Person;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UpdatePersonUseCase {

    private final PersonRepository personRepository;
    private final PersonService personService;
    private final Validator validator;
    private final AddressService addressService;

    public UpdatePersonUseCase(PersonRepository personRepository, PersonService personService, Validator validator, AddressService addressService) {
        this.personRepository = personRepository;
        this.personService = personService;
        this.validator = validator;
        this.addressService = addressService;
    }

    public Person execute(Long id, Person updatedPerson) {
        validatePerson(updatedPerson);
        Person person = findPersonById(id);
        updatePersonData(person, updatedPerson);
        return personRepository.save(person);
    }

    private void validatePerson(Person person) {
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private Person findPersonById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Pessoa não encontrada com o ID: " + id));
    }

    private void updatePersonData(Person person, Person updatedPerson) {
        person.setName(updatedPerson.getName());
        person.setAge(updatedPerson.getAge());
        person.setPhone(updatedPerson.getPhone());
        person.setScore(updatedPerson.getScore());
        person.setAddress(addressService.getAddressByCep(updatedPerson.getAddress().getCep()));
        String scoreDescription = personService.calculateScoreDescription(person);
        person.setScoreDescription(scoreDescription);
    }
}