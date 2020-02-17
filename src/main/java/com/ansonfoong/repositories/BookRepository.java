package com.ansonfoong.repositories;

import com.ansonfoong.models.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    Book findBookById(Long id);
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
}
