package com.desafioserasaexp.api.core.port;

import com.desafioserasaexp.api.entity.Address;

public interface AddressService {

    Address getAddressByCep(String cep);
}