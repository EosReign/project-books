package com.eosreign.projectbooks.mapper;

import com.eosreign.projectbooks.dto.BookDTO;
import com.eosreign.projectbooks.entity.Book;

public class BookMapper {
    public static BookDTO toDTO(Book entity) {
        BookDTO dto = new BookDTO();
        dto.setName(entity.getName());
        dto.setText(entity.getText());
        dto.setAuthorName(entity.getAuthorName());
        return dto;
    }

    public static Book toEntity(BookDTO dto) {
        Book entity = new Book();
        entity.setName(dto.getName());
        entity.setText(dto.getText());
        entity.setAuthorName(dto.getAuthorName());
        return entity;
    }
}
