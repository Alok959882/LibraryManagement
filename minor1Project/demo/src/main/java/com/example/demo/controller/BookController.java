package com.example.demo.controller;

import com.example.demo.models.Book;
import com.example.demo.request.BookCreateRequest;
import com.example.demo.request.BookFilterType;
import com.example.demo.response.BookSearchResponse;
import com.example.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/book")
    public void createBook(@Valid @RequestBody BookCreateRequest bookCreateRequest){
        bookService.createOrUpdate(bookCreateRequest);
    }
    // GEt filter fnality / search
    @GetMapping("/books/search")
    public List<BookSearchResponse> findBook(@RequestParam ("filterType")BookFilterType bookFilterType, @
                               RequestParam ("value") String value){
       return bookService.
               findBook(bookFilterType,value)
               .stream().map(book ->book.to() ).collect(Collectors.toList());

    }
}
