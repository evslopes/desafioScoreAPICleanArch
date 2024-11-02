package com.desafioserasaexp.api.core.usecase.person;

import com.desafioserasaexp.api.core.exception.PersonAlreadyExistsException;
import com.desafioserasaexp.api.core.port.PersonRepository;
import com.desafioserasaexp.api.core.port.PersonService;
import com.desafioserasaexp.api.dataprovider.service.AddressServiceImpl;
import com.desafioserasaexp.api.entity.Person;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.stereotype.Component;

import javax.xml.validation.Validator;
import java.util.Set;

@Component
public class CreatePersonUseCase {

    private final PersonRepository personRepository;
    private final AddressServiceImpl addressService;
    private final PersonService personService;
    private final Validator validator;

    public CreatePersonUseCase(PersonRepository personRepository, AddressServiceImpl addressService, PersonService personService, Validator validator) {
        this.personRepository = personRepository;
        this.addressService = addressService;
        this.personService = personService;
        this.validator = validator;
    }

    public Person execute(Person person) throws PersonAlreadyExistsException {
        validatePerson(person);
        checkIfPersonExists(person);
        setAddress(person);
        calculateScoreDescription(person);
        return personRepository.save(person);
    }

    private void validatePerson(Person person) {
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private void checkIfPersonExists(Person person) throws PersonAlreadyExistsException {
        if (personRepository.existsByNameAndPhone(person.getName(), person.getPhone())) {
            throw new PersonAlreadyExistsException("Já existe uma pessoa cadastrada com esse nome e telefone.");
        }
    }

    private void setAddress(Person person) {
        var address = addressService.getAddressByCep(person.getAddress().getCep());
        person.setAddress(address);
    }

    private void calculateScoreDescription(Person person) {
        String scoreDescription = personService.calculateScoreDescription(person);
        person.setScoreDescription(scoreDescription);
    }
}