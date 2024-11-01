package com.desafioserasaexp.api.core.usecase.person;

import com.desafioserasaexp.api.core.port.PersonRepository;
import com.desafioserasaexp.api.entity.Person;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


@Component
public class GetPersonUseCase {

    private final PersonRepository personRepository;

    public GetPersonUseCase(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Busca pessoas com paginação e filtragem.
     *
     * @param pageable Parâmetros de paginação.
     * @param name Nome da pessoa (opcional).
     * @param age Idade da pessoa (opcional).
     * @param cep CEP da pessoa (opcional).
     * @return Página de pessoas que correspondem aos critérios de filtro.
     */
    public Page<Person> execute(@NotNull @Valid Pageable pageable, String name, Integer age, String cep) {
        return personRepository.findPaginated(pageable, name, age, cep);
    }
}