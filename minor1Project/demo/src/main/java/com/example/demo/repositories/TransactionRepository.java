package com.example.demo.repositories;

import com.example.demo.models.Book;
import com.example.demo.models.Student;
import com.example.demo.models.Transaction;
import com.example.demo.models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    Transaction findTopByBookAndStudentAndTransactionTypeOrderByTransactionDateDesc(Book book, Student student, TransactionType transactionType);

}
