package com.java_ne.repositories;

import com.java_ne.models.Book;
import com.java_ne.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBookRepository extends JpaRepository<Book,Long> {
    List<Book> findAllByCreatedBy(User user);
}
