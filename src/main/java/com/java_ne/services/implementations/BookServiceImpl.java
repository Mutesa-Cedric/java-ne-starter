package com.java_ne.services.implementations;

import com.java_ne.dtos.book.CreateUpdateBook;
import com.java_ne.dtos.response.ApiResponse;
import com.java_ne.exceptions.BadRequestException;
import com.java_ne.exceptions.CustomException;
import com.java_ne.exceptions.NotFoundException;
import com.java_ne.models.Book;
import com.java_ne.models.User;
import com.java_ne.repositories.IBookRepository;
import com.java_ne.services.interfaces.BookService;
import com.java_ne.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final IBookRepository bookRepository;
    private final UserService userService;

    @Override
    public ResponseEntity<ApiResponse<Book>> createBook(CreateUpdateBook bookDTO) {
        try{
            User user = userService.getLoggedInUser();
            Book book = new Book();
            book.setCreatedBy(user);
            book.setTitle(bookDTO.getTitle());
            book.setAuthor(bookDTO.getAuthor());
            book.setYear(bookDTO.getYear());
            book.setIsbn(bookDTO.getIsbn());
            book.setPublisher(bookDTO.getPublisher());
            return ApiResponse.success("Book created successfully", HttpStatus.OK, bookRepository.save(book));
        }catch (Exception e) {
            throw new CustomException(e);
        }

    }

    @Override
    public ResponseEntity<ApiResponse<Book>> updateBook(CreateUpdateBook book, Long bookId) {
        try {
            Book book1 = bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException("Book not found"));
            if (!book1.getCreatedBy().getId().equals(userService.getLoggedInUser().getId())) {
                throw new BadRequestException("You are not allowed to update this book");
            }
            book1.setTitle(book.getTitle());
            book1.setAuthor(book.getAuthor());
            book1.setYear(book.getYear());
            return ApiResponse.success("Book updated successfully", HttpStatus.OK, bookRepository.save(book1));
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<Book>> deleteBook(Long bookId) {
        try {
            User user = userService.getLoggedInUser();
            Book book = bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException("The book was not found"));
            if (!book.getCreatedBy().getId().equals(user.getId())) {
                throw new BadRequestException("You are not allowed to delete this book");
            }
            bookRepository.delete(book);
            return ApiResponse.success("Book deleted successfully", HttpStatus.OK, book);
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<Book>> getBook(Long bookId) {
        try {
            return ApiResponse.success("Book retrieved successfully", HttpStatus.OK, bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException("Book not found")));
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<List<Book>>> getAllBooks() {
        try {
            return ApiResponse.success("Books retrieved successfully", HttpStatus.OK, bookRepository.findAll());
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<List<Book>>> getBooksByUser() {
        try {
            return ApiResponse.success("Books retrieved successfully", HttpStatus.OK, bookRepository.findAllByCreatedBy(userService.getLoggedInUser()));
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }
}
