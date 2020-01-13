package com.example.common.login.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.awt.*;

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
