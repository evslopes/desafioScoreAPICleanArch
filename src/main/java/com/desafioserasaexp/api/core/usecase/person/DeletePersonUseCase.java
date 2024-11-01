package com.desafioserasaexp.api.core.usecase.person;

import com.desafioserasaexp.api.core.exception.PersonNotFoundException;
import com.desafioserasaexp.api.core.port.PersonRepository;
import com.desafioserasaexp.api.entity.Person;
import org.springframework.stereotype.Component;

@Component
public class DeletePersonUseCase {

    private final PersonRepository personRepository;

    public DeletePersonUseCase(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void execute(Long id) {
        // Busca a pessoa no repositório
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Pessoa não encontrada com o ID: " + id));

        // Marca a pessoa como excluída logicamente
        person.setDeleted(true);

        // Salva a pessoa atualizada no repositório
        personRepository.save(person);
    }
}