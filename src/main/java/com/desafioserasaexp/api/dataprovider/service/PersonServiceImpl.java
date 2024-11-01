package com.desafioserasaexp.api.dataprovider.service;

import com.desafioserasaexp.api.core.port.PersonService;
import com.desafioserasaexp.api.entity.Person;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Override
    public String calculateScoreDescription(Person person) {
        int score = person.getScore();
        if (score >= 0 && score <= 200) {
            return "Insuficiente";
        } else if (score >= 201 && score <= 500) {
            return "Inaceitável";
        } else if (score >= 501 && score <= 700) {
            return "Aceitável";
        } else if (score >= 701 && score <= 1000) {
            return "Recomendável";
        } else {
            return "Score inválido";
        }
    }
}