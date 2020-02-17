package com.ansonfoong.controllers;

import com.ansonfoong.models.Book;
import com.ansonfoong.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping(value="/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Book addBook(Book book) {
        System.out.println("Trying to add book...");
        Book newBook = new Book(book.getTitle(), book.getAuthor());
        try {
            this.bookRepository.save(newBook);
            return newBook;
        }
        catch(Exception error) {
            System.out.println(error.toString());
            return null;
        }
    }
    
    @GetMapping("/{id}")
    public Optional<Book> getBookById(@PathVariable("id") Long id) {
        System.out.println("ID: " + id);
        Optional<Book> optionalBook = this.bookRepository.findById(id);
        if(optionalBook.isPresent()) return optionalBook;
        else
            return optionalBook;
    }
    @GetMapping("/date/{date}")
    public void getBookByDate(@PathVariable("date") String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        try {
            LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
            System.out.println(dateTime.toLocalTime());
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    @GetMapping("/home")
    public String getHome() {
        return "home";
    }
}
