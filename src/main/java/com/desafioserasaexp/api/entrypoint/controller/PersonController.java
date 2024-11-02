package com.desafioserasaexp.api.entrypoint.controller;

import com.desafioserasaexp.api.core.exception.PersonAlreadyExistsException;
import com.desafioserasaexp.api.core.usecase.person.CreatePersonUseCase;
import com.desafioserasaexp.api.core.usecase.person.UpdatePersonUseCase;
import com.desafioserasaexp.api.core.usecase.person.DeletePersonUseCase;
import com.desafioserasaexp.api.entity.Person;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persons")
@Tag(name = "Person", description = "Operations about persons")
public class PersonController {

    private final CreatePersonUseCase createPersonUseCase;
    private final UpdatePersonUseCase updatePersonUseCase;
    private final DeletePersonUseCase deletePersonUseCase;

    public PersonController(CreatePersonUseCase createPersonUseCase, UpdatePersonUseCase updatePersonUseCase, DeletePersonUseCase deletePersonUseCase) {
        this.createPersonUseCase = createPersonUseCase;
        this.updatePersonUseCase = updatePersonUseCase;
        this.deletePersonUseCase = deletePersonUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new person", security = @SecurityRequirement(name = "bearerAuth"))
    public Person create(@RequestBody Person person) throws PersonAlreadyExistsException {
        return createPersonUseCase.execute(person);
    }

    @GetMapping
    @Operation(summary = "Get all persons", security = @SecurityRequirement(name = "bearerAuth"))
    public Page<Person> getAll(Pageable pageable,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) Integer age,
                               @RequestParam(required = false) String cep) {
        // TODO Chamada do caso de uso para buscar pessoas
        return null;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a person", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Person> update(@PathVariable Long id, @RequestBody Person person) {
        Person updatedPerson = updatePersonUseCase.execute(id, person);
        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a person", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deletePersonUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}