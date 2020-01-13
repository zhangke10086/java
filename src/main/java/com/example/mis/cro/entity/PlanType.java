package com.example.mis.cro.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "mistaskplantype")
@Data
public class PlanType {

    @Id
    @Column(length = 36 ,name="id",nullable = false)
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
    @Column(length = 60 ,name = "code",nullable = false)
    private String code;
    @Column(length = 128 ,name = "name",nullable = false)
    private String name;
    @Column(length = 512 ,name = "note")
    private String note;


}


