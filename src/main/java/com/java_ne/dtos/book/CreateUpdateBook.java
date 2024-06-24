package com.java_ne.dtos.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateUpdateBook {
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private int year;
}
