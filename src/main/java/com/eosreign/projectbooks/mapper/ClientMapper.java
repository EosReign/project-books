package com.eosreign.projectbooks.mapper;

import com.eosreign.projectbooks.dto.ClientDTO;
import com.eosreign.projectbooks.entity.Client;

public class ClientMapper {
    public static ClientDTO toDTO(Client entity) {
        ClientDTO dto = new ClientDTO();
        dto.setName(entity.getName());
        dto.setAddress(entity.getAddress());
        dto.setPhone(entity.getPhone());
        return dto;
    }

    public static Client toEntity(ClientDTO dto) {
        Client entity = new Client();
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setPhone(dto.getPhone());
        return entity;
    }
}
