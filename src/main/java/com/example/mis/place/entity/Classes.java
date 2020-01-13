package com.example.mis.place.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Table(name = "misclasses")
@Entity
public class Classes {
    @Id
    @Column(length = 36 ,name="id",nullable = false)
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
    @Column(length = 60 ,name="name")
    private String name;
    @Column(length = 60 ,name="code")
    private String code;
    private String note;
    @ManyToOne
    @JoinColumn(name = "placeid")
    private place place;
}
