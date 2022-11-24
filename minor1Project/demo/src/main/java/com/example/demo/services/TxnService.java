package com.example.demo.services;

import com.example.demo.exceptions.TransactionServiceException;
import com.example.demo.models.Book;
import com.example.demo.models.Student;
import com.example.demo.models.Transaction;
import com.example.demo.models.TransactionType;
import com.example.demo.repositories.TransactionRepository;
import com.example.demo.request.BookFilterType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TxnService {

    private static Logger logger= LoggerFactory.getLogger(TxnService.class);
    @Autowired
    StudentService studentService;
    @Autowired
    BookService bookService;
    @Autowired
    TransactionRepository transactionRepository;
    @Value("${book.return.due_date}")
    int number_of_days;
    @Transactional
    public String issueTxn(int studentId, int bookId) throws TransactionServiceException {
    /*
student is a valid entity
book is present and is available.
create a transaction saving in txn table
Make the book unvailable.
 */
        /*try {
            logger.info("Issue request came : studentId:{} and bookId is:{}",studentId,bookId);
            Thread.sleep(1000);
            logger.info("After sleeping  request came : studentId:{} and bookId is:{}",studentId,bookId);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        Student student=studentService.findStudentByStudentId(studentId);
        if(student==null){
            throw  new TransactionServiceException(" Student Not Present");
        }
       List<Book> books= bookService.findBook(BookFilterType.BOOK_ID,String.valueOf(bookId));
        if (books.size()!=1 && books==null||books.get(0).getStudent()!=null){
            throw new TransactionServiceException("Book Not Present in the Library");
        }
        Transaction transaction=Transaction.builder().externalTxnId(UUID.randomUUID().toString())
                .transactionType(TransactionType.ISSUE)
                .payment(books.get(0).getCost())
                .book(books.get(0))
                .student(student)
                .build();
        transactionRepository.save(transaction);

        books.get(0).setStudent(student);
        bookService.createOrUpdate(books.get(0));

        return transaction.getExternalTxnId();


    }

    public String returnTxn(int studentId, int bookId) throws TransactionServiceException {
/*
student validity
book issued to the particular student.
calculate the fine
create a transaction if not done.
make the book available
 */

        Student student=studentService.findStudentByStudentId(studentId);
        if(student==null){
            throw  new TransactionServiceException(" Student Not Present");
        }
        List<Book> books= bookService.findBook(BookFilterType.BOOK_ID,String.valueOf(bookId));
        if (books.size()!=1 && books==null){
            throw new TransactionServiceException("Book Not Present in the Library");
        }
        if(books.get(0).getStudent().getId()!=studentId){
            throw new TransactionServiceException("Book is not assigned to this student.");
        }
        // Finding the original issue Txn
        //select * from transaction where book_id=? and student_id=?and transactionType=? order by transaction_date desc;

        Transaction issueTxn=transactionRepository.findTopByBookAndStudentAndTransactionTypeOrderByTransactionDateDesc(books.get(0),student,TransactionType.ISSUE);
        Transaction transaction=Transaction.builder()
                .transactionType(TransactionType.RETURN)
                .externalTxnId(UUID.randomUUID().toString())
                .book(books.get(0))
                .student(student)
                .payment(calculateFine(issueTxn))
                .build();
        transactionRepository.save(transaction);
        books.get(0).setStudent(null);
        bookService.createOrUpdate(books.get(0));
        return transaction.getExternalTxnId();

    }
    private double calculateFine(Transaction issueTxn){

        long issueTime=issueTxn.getTransactionDate().getTime();
        long returnTime=System.currentTimeMillis();
        long diff=returnTime-issueTime;
        long daysPassed=TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS);
        if (daysPassed>=number_of_days) {
            return (daysPassed - number_of_days) * 1.0;
        }
        else {
            return 0;
        }
    }
}
