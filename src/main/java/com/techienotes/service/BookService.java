package com.techienotes.service;

import com.techienotes.dto.BookDto;
import com.techienotes.entity.Book;

public interface BookService {
    Book addBook(BookDto book);

    Book updateBook(BookDto book);

    Book getBook(long id);

    String deleteBook(long id);
}
