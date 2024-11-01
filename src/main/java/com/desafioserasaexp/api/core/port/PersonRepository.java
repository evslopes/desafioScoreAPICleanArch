package com.desafioserasaexp.api.core.port;


import com.desafioserasaexp.api.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PersonRepository {

    Person save(Person person);

    Optional<Person> findById(Long id);

    Page<Person> findAll(Pageable pageable, String name, Integer age, String cep);

    void deleteById(Long id);
}