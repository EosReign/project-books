package com.eosreign.projectbooks.mapper;

import com.eosreign.projectbooks.dto.BookDTO;
import com.eosreign.projectbooks.entity.Book;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class );
    BookDTO toDTO(Book entity);
    Book toEntity(BookDTO dto);
}
