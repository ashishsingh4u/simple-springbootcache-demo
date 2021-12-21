package com.techienotes.controller;

import com.techienotes.dto.BookDto;
import com.techienotes.entity.Book;
import com.techienotes.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/book")
    @Operation(summary = "addBook", description = "Method used to add book")
    public Book addBook(@RequestBody BookDto book) {
        return bookService.addBook(book);
    }

    @PutMapping("/book")
    @Operation(summary = "updateBook", description = "Method used to update book for given id")
    public Book updateBook(@RequestBody BookDto book) {
        return bookService.updateBook(book);
    }

    @GetMapping("/book/{id}")
    @Operation(summary = "getBook", description = "Method used to find book for given id")
    public Book getBook(@Parameter(name = "id", description = "id to find record", example = "1") @PathVariable long id) {
        return bookService.getBook(id);
    }

    @DeleteMapping("/book/{id}")
    @Operation(summary = "deleteBook", description = "Method used to delete book for given id")
    public String deleteBook(@Parameter(name = "id", description = "id to delete record", example = "1") @PathVariable long id) {
        return bookService.deleteBook(id);
    }
}
