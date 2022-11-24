package com.example.demo.models;

import com.example.demo.response.BookSearchResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String name;


    private int cost;


    @Enumerated(value = EnumType.STRING)//kyoki string type ordinal value store kara rahe hai.
    private Genre genre;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("bookList")//bookList jo hai wo author ke me bookList name hi rahega.
    private Author author;

    @ManyToOne
    @JoinColumn//bydefault yeh map kar dega student_id se yha main custome kiya hai to student_name se map kar dega.
    private Student student;

    @OneToMany(mappedBy = "book")
    private List<Transaction> transactionList;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    public BookSearchResponse to(){
        return BookSearchResponse.builder()
                .id(id)
                .name(name)
                .author(author)
                .cost(cost)
                .genre(genre)
                .student(student)
                .transactionList(transactionList)
                .createdOn(createdOn)
                .updatedOn(updatedOn).
                build();
        //BookResponse me jitne attribute hai un sabko yaha pass karna hai tabhi return karega. ab isko map kar denge booksearch ki api me.
    }

}
