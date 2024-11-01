package com.desafioserasaexp.api.dataprovider.repository;

import com.desafioserasaexp.api.core.port.PersonRepository;
import com.desafioserasaexp.api.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Optional;
import java.util.stream.Collectors;

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
        return null;
    }

    @Override
    public Page<Person> findPaginated(Pageable pageable, String name, Integer age, String cep) {
        List<Person> filteredPersons = persons.values().stream()
                .filter(person -> name == null || person.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(person -> age == null || person.getAge().equals(age))
                .filter(person -> cep == null || person.getAddress().getCep().equals(cep))
                .collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), filteredPersons.size());
        return new PageImpl<>(filteredPersons.subList(start, end), pageable, filteredPersons.size());
    }

    @Override
    public void deleteById(Long id) {
        persons.remove(id);
    }

    @Override
    public boolean existsByNameAndPhone(String name, String phone) {
        return false;
    }
}