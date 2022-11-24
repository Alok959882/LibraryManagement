package com.example.demo.services;

import com.example.demo.models.Author;
import com.example.demo.models.Book;
import com.example.demo.models.Genre;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BookRepository;
import com.example.demo.request.BookCreateRequest;
import com.example.demo.request.BookFilterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;
    public void createOrUpdate(BookCreateRequest bookCreateRequest){
        Book book=bookCreateRequest.to();
        createOrUpdate(book);
    }

    public void  createOrUpdate(Book book){
        Author author=book.getAuthor();
        //find the author with the given email exists in db or not.??
        //if exists,don't save else save in Db first.
        Author authorFromDb=authorRepository.findByEmail(author.getEmail());
        if(authorFromDb==null) {
            authorFromDb = authorRepository.save(author);//this has a primary key.
        }


        //select * from author where email =?
        book.setAuthor(authorFromDb);
        bookRepository.save(book);
    }
    public List<Book> findBook(BookFilterType bookFilterType,String value){
        switch (bookFilterType){
            case NAME:
                return bookRepository.findByName(value);

            case AUTHOR_NAME:
                return bookRepository.findBookByAuthorName(value);
            case GENRE:
                return bookRepository.findByGenre(Genre.valueOf(value));
            case BOOK_ID:
                return bookRepository.findAllById(Collections.singletonList(Integer.parseInt(value)));
            default:
                return new  ArrayList<>();
        }
    }

}
