package com.desafioserasaexp.api.dataprovider.service;

import com.desafioserasaexp.api.core.port.AddressService;
import com.desafioserasaexp.api.entity.Address;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AddressServiceImpl implements AddressService {

    private final RestTemplate restTemplate;

    public AddressServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Address getAddressByCep(String cep) {
        // Substitua a URL abaixo pela URL da API externa de endereço que você está usando
        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        // Faz a requisição à API externa
        Address address = restTemplate.getForObject(url, Address.class);

        // Retorna o objeto Address
        return address;
    }
}
