package com.example.demo.controller;

import com.example.demo.exceptions.TransactionServiceException;
import com.example.demo.services.TxnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class TransactionController {
    @Autowired
    TxnService transactionServices;

    @PostMapping("/transaction/issue")
    public String issueTxn(@RequestParam("studentId") int studentId,
                           @RequestParam("bookId") int bookId) throws TransactionServiceException {
        return transactionServices.issueTxn(studentId, bookId);

    }

    @PostMapping("/transaction/return")
    public String returnTxn(@RequestParam("studentId") int studentId,
                            @RequestParam("bookId") int bookId) throws TransactionServiceException {
        return transactionServices.returnTxn(studentId, bookId);

    }
}
