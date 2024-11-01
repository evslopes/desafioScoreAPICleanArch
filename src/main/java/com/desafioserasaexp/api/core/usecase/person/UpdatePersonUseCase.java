package com.desafioserasaexp.api.core.usecase.person;

import com.desafioserasaexp.api.core.exception.PersonNotFoundException;
import com.desafioserasaexp.api.core.port.PersonRepository;
import com.desafioserasaexp.api.core.port.PersonService;
import com.desafioserasaexp.api.entity.Person;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.stereotype.Component;


import java.util.Set;

@Component
public class
UpdatePersonUseCase {

    private final PersonRepository personRepository;
    private final PersonService personService;
    private final Validator validator;
    private final AddressService addressService;

    public UpdatePersonUseCase(PersonRepository personRepository, PersonService personService, Validator validator) {
        this.personRepository = personRepository;
        this.personService = personService;
        this.validator = validator;
    }

    public Person execute(Long id, Person updatedPerson) {
        // Validação dos dados da pessoa
        Set<ConstraintViolation<Person>> violations = validator.validate(updatedPerson);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        // Busca a pessoa no repositório
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Pessoa não encontrada com o ID: " + id));

        // Atualiza os dados da pessoa
        person.setName(updatedPerson.getName());
        person.setAge(updatedPerson.getAge());
        person.setPhone(updatedPerson.getPhone());
        person.setScore(updatedPerson.getScore());

        person.setAddress(addressService.getAddressByCep(updatedPerson.getAddress().getCep()));

        // Recalcula o scoreDescription
        String scoreDescription = personService.calculateScoreDescription(person);
        person.setScoreDescription(scoreDescription);

        // Salva a pessoa atualizada no repositório
        return personRepository.save(person);
    }
}
