package com.desafioserasaexp.api.dataprovider.repository;

import com.desafioserasaexp.api.core.port.PersonRepository;
import com.desafioserasaexp.api.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

import java.util.Optional;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private final Map<Long, Person> persons = new HashMap<>();
    private Long nextId = 1L;

    @Override
    public Person save(Person person) {
        if (person.getId() == null) {
            person.setId(nextId++);
        }
        persons.put(person.getId(), person);
        return person;
    }

    @Override
    public Optional<Person> findById(Long id) {
        return Optional.ofNullable(persons.get(id));
    }

    @Override
    public Page<Person> findAll(Pageable pageable, String name, Integer age, String cep) {
        // TODO Implementação da paginação e filtro utilizando o HashMap
        // ...
        return null;
    }

    @Override
    public void deleteById(Long id) {
        persons.remove(id);
    }
}