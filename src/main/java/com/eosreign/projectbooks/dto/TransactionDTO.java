package com.eosreign.projectbooks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionDTO {
    private LocalDate dateTake;
    private LocalDate dateReturn;
    private String clientName;
    private Long clientId;
    private String bookName;
    private Long bookId;
    private boolean isClosed;
}
