package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(unique = true,nullable = false)
    private String contact;

    private String address;

    @Column(unique = true)
    private String email;

    @Enumerated(value = EnumType.STRING)
    private AccountStatus accountStatus;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    @OneToMany(mappedBy = "student")
    private List<Book> bookList;

    @OneToMany(mappedBy = "student")
    private List<Transaction> transactionList;

    @OneToOne
    @JoinColumn
    @JsonIgnoreProperties({"student", "admin", "password"})
    private MyUser myUser;
}
