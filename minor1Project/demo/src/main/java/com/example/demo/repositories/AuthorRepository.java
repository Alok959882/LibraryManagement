package com.example.demo.repositories;

import com.example.demo.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {

    /*
    1. Native query
    2. JPQL query
    3. without query
     */
    Author findByEmail(String email);
    //find all the authors above the age of 30. living in india and name starts with A.
    List<Author> findByAgeGreaterThanEqualAndCountryAndNameStartingWith
    (int age,String country,String prefix);
    //select * from author where age>=30 and country=?2 and like?3%


                //          or a.author_email= ?1")
    @Query(value = "select a from author a where a.email= :author_email",nativeQuery = true)//it is for custom function, it depends on JPA.
    public Author getAuthorGivenEmailIdNativeQuery(String author_email);

    @Query(value = "select a from Author a where a.email= ?1")
    public Author getAuthorGivenEmailIdJpql(String author_email);

    //find all the authors in india.
    @Query(value = "select a from author a where a.land= ?1",nativeQuery = true)
    public List<Author> getAuthorsByCountryNativeQuery(String country);

    @Query(value = "select a from Author a where a.country= ?1")
    public List<Author> getAuthorsByCountryJpql(String country);

}
