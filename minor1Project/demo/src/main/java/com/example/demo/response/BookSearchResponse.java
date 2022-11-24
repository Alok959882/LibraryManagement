package com.example.demo.response;

import com.example.demo.models.Author;
import com.example.demo.models.Genre;
import com.example.demo.models.Student;
import com.example.demo.models.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookSearchResponse {

    private int id;


    private String name;


    private int cost;


    private Genre genre;
        //agar hame 1 se jyada property ignore karna hai to @JsonIgnoreProperties({"bookList","createdOn"}) add kar sakte hai.
    @JsonIgnoreProperties({"bookList","addedOn"})//bookList jo hai wo author ke me bookList name hi rahega.
    private Author author;

    private Student student;

    private List<Transaction> transactionList;

    private Date createdOn;

    private Date updatedOn;


}

