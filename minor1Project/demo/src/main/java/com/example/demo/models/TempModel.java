package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TempModel implements Serializable {

    @Id
    private int id;

    @Id
    private String name;

    private Date createdOn;

}
