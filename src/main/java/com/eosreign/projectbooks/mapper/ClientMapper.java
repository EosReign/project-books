package com.eosreign.projectbooks.mapper;


import com.eosreign.projectbooks.dto.ClientDTO;
import com.eosreign.projectbooks.entity.Client;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class );

    ClientDTO toDTO(Client entity);

    Client toEntity(ClientDTO dto);
}
