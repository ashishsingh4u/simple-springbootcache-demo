package com.techienotes.controller;

import com.techienotes.dto.BookDto;
import com.techienotes.entity.Book;
import com.techienotes.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Book> addBook(@RequestBody BookDto book) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.addBook(book));
    }

    @PostMapping("/book2")
    @Operation(summary = "addBook", description = "Method used to add book")
    public ResponseEntity<Void> addBook2(@RequestBody BookDto book) {
        bookService.addBook(book);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/book")
    @Operation(summary = "updateBook", description = "Method used to update book for given id")
    public ResponseEntity<Book> updateBook(@RequestBody BookDto book) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBook(book));
    }

    @GetMapping("/book/{id}")
    @Operation(summary = "getBook", description = "Method used to find book for given id")
    public ResponseEntity<Book> getBook(@Parameter(name = "id", description = "id to find record", example = "1") @PathVariable long id) {
        var book = bookService.getBook(id);
        var header = new HttpHeaders();
        if (book == null) {
            header.add("desc", "Book not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(header).build();
        }
        header.add("desc", "Book Found");
        return ResponseEntity.status(HttpStatus.OK).headers(header).body(bookService.getBook(id));
    }

    @DeleteMapping("/book/{id}")
    @Operation(summary = "deleteBook", description = "Method used to delete book for given id")
    public ResponseEntity<String> deleteBook(@Parameter(name = "id", description = "id to delete record", example = "1") @PathVariable long id) {
        return ResponseEntity.ok(bookService.deleteBook(id));
    }
}
