package com.example.common.login.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "misuser")
@Data
public class User {
    @Id
    @Column(length = 60 ,name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(length = 60 ,name = "code",unique = true)
    private String code;
    private String name;
    private String password;
    private String state;
    private String email;
    private String type;
    private String description;
    private String token;
    private String province;
    private String city;
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date birthday;
    private String note;
    private String area;
    //角色与用户建立多对多关联
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
    }, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    public List<Role> roleList = new ArrayList<>();
}
