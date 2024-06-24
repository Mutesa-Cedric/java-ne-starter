package com.java_ne.services.interfaces;

import com.java_ne.dtos.book.CreateUpdateBook;
import com.java_ne.dtos.response.ApiResponse;
import com.java_ne.models.Book;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {
    public ResponseEntity<ApiResponse<Book>> createBook(CreateUpdateBook book);

    public ResponseEntity<ApiResponse<Book>> updateBook(CreateUpdateBook book, Long bookId);

    public ResponseEntity<ApiResponse<Book>> deleteBook(Long bookId);

    public ResponseEntity<ApiResponse<Book>> getBook(Long bookId);

    public ResponseEntity<ApiResponse<List<Book>>> getAllBooks();

    public ResponseEntity<ApiResponse<List<Book>>> getBooksByUser();
}
