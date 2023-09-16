package com.eosreign.projectbooks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookDTO {
    private String name;
    private String text;
    private String authorName;
    private Long author;
    private Long client;
    private Long publisher;

}
