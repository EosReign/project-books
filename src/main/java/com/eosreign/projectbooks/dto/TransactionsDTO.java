package com.eosreign.projectbooks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionsDTO {
    private List<TransactionDTO> listDTO;
}
