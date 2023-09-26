package com.eosreign.projectbooks.mapper;

import com.eosreign.projectbooks.dto.BookDTO;
import com.eosreign.projectbooks.dto.BooksDTO;
import com.eosreign.projectbooks.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BooksMapper {
    public static BooksDTO toDTO(List<Book> arrEntity) {
        BooksDTO dto = new BooksDTO();
        List<BookDTO> arrDTO = new ArrayList<>();
        for (Book entity: arrEntity) {
            BookDTO cell = new BookDTO();
            cell.setName(entity.getName());
            cell.setText(entity.getText());
            cell.setAuthorName(entity.getAuthorName());
            arrDTO.add(cell);
        }
        dto.setListDTO(arrDTO);
        return dto;
    }
}
