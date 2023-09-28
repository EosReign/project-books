package com.eosreign.projectbooks.mapper;

import com.eosreign.projectbooks.dto.TransactionDTO;
import com.eosreign.projectbooks.entity.Transaction;

public class TransactionMapper {
    public static TransactionDTO toDTO(Transaction entity) {
        TransactionDTO dto = new TransactionDTO();
        dto.setDateTake(entity.getDateTake());
        dto.setDateReturn(entity.getDateReturn());
        dto.setBookName(entity.getBook().getName());
        dto.setBookId(entity.getBook().getId());
        dto.setClientName(entity.getClient().getName());
        dto.setClientId(entity.getClient().getId());
        dto.setClosed(entity.isClosed());
        return dto;
    }

    public static Transaction toEntity(TransactionDTO dto) {
        Transaction entity = new Transaction();
        entity.setDateTake(dto.getDateTake());
        entity.setDateReturn(dto.getDateReturn());
        entity.setClosed(dto.isClosed());
        return entity;
    }

}
