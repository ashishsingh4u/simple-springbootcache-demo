package com.techienotes.mapper;

import com.techienotes.dto.BookDto;
import com.techienotes.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper extends DefaultMapper {

    BookDto bookToBookDto(Book book);

    Book bookDtoToBook(BookDto book);
}
