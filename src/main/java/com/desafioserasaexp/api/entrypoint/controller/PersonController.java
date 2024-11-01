package com.desafioserasaexp.api.entrypoint.controller;

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

    // TODO Injeção dos casos de uso

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new person", security = @SecurityRequirement(name = "bearerAuth"))
    public Person create(@RequestBody Person person) {
        // TODO Chamada do caso de uso para criar pessoa
        return null;
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
        // TODO Chamada do caso de uso para atualizar pessoa
        return null;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a person", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        // TODO Chamada do caso de uso para deletar pessoa
        return null;
    }
}