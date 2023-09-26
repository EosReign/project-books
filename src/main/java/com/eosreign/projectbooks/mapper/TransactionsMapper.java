package com.eosreign.projectbooks.mapper;

import com.eosreign.projectbooks.dto.ClientsDTO;
import com.eosreign.projectbooks.dto.TransactionDTO;
import com.eosreign.projectbooks.dto.TransactionsDTO;
import com.eosreign.projectbooks.entity.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionsMapper {
    public static TransactionsDTO toDTO(List<Transaction> arrEntity) {
        TransactionsDTO dto = new TransactionsDTO();
        List<TransactionDTO> arrDTO = new ArrayList<>();
        for (Transaction entity: arrEntity) {
            TransactionDTO cell = TransactionMapper.toDTO(entity);
            arrDTO.add(cell);
        }
        dto.setListDTO(arrDTO);
        return dto;
    }
}
