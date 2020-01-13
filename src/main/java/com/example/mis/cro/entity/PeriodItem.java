package com.example.mis.cro.entity;

import com.example.common.auditorinfo.AbstractPublicFields;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "misperiod")
@Data
public class PeriodItem extends AbstractPublicFields {

    @Id
    @Column(length = 36 ,name="id",nullable = false)
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
    @Column(length = 60 ,name="name")
    private String name;
    @OneToOne(cascade = CascadeType.DETACH)// 分离状态对mps操作不会处理Inventory
    @JoinColumn(name="periodtypeid")
    private PeriodType periodtype;
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date begindate;
    @JsonFormat(pattern="yyyy-MM-dd" ,  timezone="GMT+8")
    private Date enddate;
    @Column(length = 512 ,name="note")
    private String note;
    private Integer torder;
}


