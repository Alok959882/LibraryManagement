package com.example.demo.repositories;


import com.example.demo.models.Author;
import com.example.demo.models.Book;
import com.example.demo.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

List<Book> findByName(String name);
List<Book> findBookByAuthorName(String authorName);
List<Book> findByGenre(Genre genre);
}
