package com.example.mis.cro.entity;

import com.example.common.auditorinfo.AbstractPublicFields;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "misperiodtaskplanitems")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PeriodTaskPlanItems extends AbstractPublicFields {
    @Id
    @Column(length = 36, name = "id", nullable = false)

    private String id;
    @Column(length = 60)
    private String orderno;
    @Column(length = 60)
    private String opnum;
    @Column(length = 128)
    private String opname;
    @Column(length = 1024)
    private String flexfieldid;
    @Column(length = 1024)
    private String flexfieldcode;
    @Column(columnDefinition = "nvarchar2(2000)")
    private String flexfieldname;
    @Column(length = 60)
    private String batch;
    @Column(length = 60)
    private String sn;
    @Column(columnDefinition = "number(20,8) default 0")
    private Double qty;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private java.sql.Date planstartdate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @JoinColumn(name = "sourcetype")
    private java.sql.Date planenddate;
    @Column(length = 36)
    private String sourcetype;
    @Column(length = 36)
    private String sourceid;
    @Column(length = 36)
    private String sourceno;
    @Column(length = 4000)
    private String signmessage;
    @Column(length = 4000)
    private String cerbase64string;
    @Column(length = 512)
    private String note;
    @Column(length = 36)
    private String team;

}
