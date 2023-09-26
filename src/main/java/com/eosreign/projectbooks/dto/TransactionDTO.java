package com.eosreign.projectbooks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionDTO {
    private Date dateTake;
    private Date dateReturn;

    private Long clientId;

    private Long bookId;
    private boolean isClosed;
}
