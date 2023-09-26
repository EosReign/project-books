package com.eosreign.projectbooks.dto;

import com.eosreign.projectbooks.entity.Book;
import com.eosreign.projectbooks.entity.Client;

import java.util.Date;

public class TransactionDTO {
    private Date dateTake;
    private Date dateReturn;

    private String clientName;

    private String bookName;
    private boolean isClosed;
}
