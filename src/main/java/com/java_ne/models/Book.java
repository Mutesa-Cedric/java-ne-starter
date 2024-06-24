package com.java_ne.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Book extends Base {

    @Column(unique = true, nullable = false)
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private int year;

    @ManyToOne()
    @JoinColumn(name = "created_by_id")
    private User createdBy;

}
