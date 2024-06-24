package com.java_ne.controllers;

import com.java_ne.dtos.book.CreateUpdateBook;
import com.java_ne.dtos.response.ApiResponse;
import com.java_ne.models.Book;
import com.java_ne.services.interfaces.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/book")
@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('USER')")
public class BookController {
    private final BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Book>> createBook(@RequestBody CreateUpdateBook book) {
        System.out.println("about to create a book");
        return bookService.createBook(book);
    }

    @PutMapping("/update/{bookId}")
    public ResponseEntity<ApiResponse<Book>> updateBook(@RequestBody CreateUpdateBook book, @PathVariable Long bookId) {
        return bookService.updateBook(book, bookId);
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<ApiResponse<Book>> deleteBook(@PathVariable Long bookId) {
        return bookService.deleteBook(bookId);
    }

    @GetMapping("/id/{bookId}")
    public ResponseEntity<ApiResponse<Book>> getBook(@PathVariable Long bookId) {
        return bookService.getBook(bookId);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Book>>> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/user")
    public ResponseEntity<ApiResponse<List<Book>>> getBooksByUser() {
        return bookService.getBooksByUser();
    }
}
