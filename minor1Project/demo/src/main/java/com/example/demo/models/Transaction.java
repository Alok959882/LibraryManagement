package com.example.demo.models;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String externalTxnId;

    @Enumerated
    private TransactionType transactionType;

    private double payment;

    @ManyToOne
    @JoinColumn
    private Book book;

    @ManyToOne
    @JoinColumn
    private Student student;

    @CreationTimestamp
    private Date transactionDate;





}
