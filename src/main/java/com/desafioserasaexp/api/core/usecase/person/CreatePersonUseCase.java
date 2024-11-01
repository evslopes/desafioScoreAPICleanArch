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
public class
CreatePersonUseCase {

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
        // Validação dos dados da pessoa
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);

        }

        // Verifica se já existe uma pessoa com o mesmo nome e telefone
        if (personRepository.existsByNameAndPhone(person.getName(), person.getPhone())) {
            throw new PersonAlreadyExistsException("Já existe uma pessoa cadastrada com esse nome e telefone.");
        }

        // Consulta da API externa de endereço
        var address = addressService.getAddressByCep(person.getAddress().getCep());
        person.setAddress(address);

        // Cálculo do scoreDescription
        String scoreDescription = personService.calculateScoreDescription(person);
        person.setScoreDescription(scoreDescription);

        // Persistência da pessoa
        return personRepository.save(person);
    }
}