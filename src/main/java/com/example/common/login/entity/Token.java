package com.example.common.login.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Table(name = "mistoken")
@Entity
public class Token {
    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
    @Column(length = 8000)
    private String Token;
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "userid")
    private User user;
}
