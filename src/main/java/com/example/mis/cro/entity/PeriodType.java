package com.example.mis.cro.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "misperiodtype")
@Data
public class PeriodType {
    @Id
    @Column(length = 36 ,name="id",nullable = false)
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
    @Column(length = 60 ,name="code",nullable = false)
    private String code;
    @Column(length = 128 ,name="name",nullable = false)
    private String name;
    @Column(name="span")
    private Integer span;
    @Column(length = 512 ,name="note")
    private String note;

}


