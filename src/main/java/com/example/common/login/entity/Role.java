package com.example.common.login.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Table(name = "misrole")
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 100)
    private String name;
    @Column(length = 200)
    private String note;
}
