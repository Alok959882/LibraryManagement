package com.example.demo.request;


import com.example.demo.models.Author;
import com.example.demo.models.Book;
import com.example.demo.models.Genre;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookCreateRequest {

    private String name;

    @NotNull
    private Author author;

    @Positive
    private int cost;

    @NotNull
    private Genre genre;

    public Book to(){
        return Book.builder()
                .cost(this.cost)
                .genre(this.genre)
                .name(this.name)
                .author(this.author)
                .build();
    }
}
