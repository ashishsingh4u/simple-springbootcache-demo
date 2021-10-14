package com.techienotes.services;

import com.techienotes.dto.BookDto;
import com.techienotes.entity.Book;
import com.techienotes.mapper.BookMapper;
import com.techienotes.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@EnableScheduling
@Slf4j
public class BookServiceImpl implements BookService {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public Book addBook(BookDto book) {
        log.info("adding book with id - {}", book.getId());
        var bookEntity = bookMapper.bookDtoToBook(book);
        return bookRepository.save(bookEntity);
    }

    @Override
    @CachePut(cacheNames = "books", key = "#book.id")
    public Book updateBook(BookDto book) {
        var bookEntity = bookMapper.bookDtoToBook(book);
        bookRepository.updateAddress(book.getId(), book.getName());
        log.info("book updated with new name");
        return bookEntity;
    }

    @Override
    @Cacheable(cacheNames = "books", key = "#id")
    public Book getBook(long id) {
        log.info("fetching book from db");
        Optional<Book> book = bookRepository.findById(id);
        return book.orElseGet(Book::new);
    }

    @Override
    @CacheEvict(cacheNames = "books", key = "#id")
    public String deleteBook(long id) {
        bookRepository.deleteById(id);
        return "Book deleted";
    }

    @Scheduled(fixedRate = 50000)
    public void evictAllCaches() {
        log.info("Evicting all cache");
        cacheManager.getCacheNames().forEach(
                cacheName -> Objects.requireNonNull(cacheManager.getCache(cacheName)).clear()
        );
    }
}
