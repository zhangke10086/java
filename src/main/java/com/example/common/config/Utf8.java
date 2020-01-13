package com.example.common.config;

import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.springframework.stereotype.Component;


@Component
@SuppressWarnings("deprecation")
public class Utf8 extends MySQL5InnoDBDialect{
    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }

}
