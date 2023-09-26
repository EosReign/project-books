package com.eosreign.projectbooks.mapper;

import com.eosreign.projectbooks.dto.ClientDTO;
import com.eosreign.projectbooks.dto.ClientsDTO;
import com.eosreign.projectbooks.entity.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientsMapper {
    public static ClientsDTO toDTO(List<Client> arrEntity) {
        ClientsDTO dto = new ClientsDTO();
        List<ClientDTO> arrDTO = new ArrayList<>();
        for (Client entity: arrEntity) {
            ClientDTO cell = new ClientDTO();
            cell.setName(entity.getName());
            cell.setAddress(entity.getAddress());
            cell.setPhone(entity.getPhone());
            arrDTO.add(cell);
        }
        dto.setListDTO(arrDTO);
        return dto;
    }
}
