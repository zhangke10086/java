package com.example.mis.cro.entity;

import com.example.common.auditorinfo.AbstractPublicFields;
import com.example.mis.project.entity.Project;
import com.example.mis.project.entity.ProjectWbs;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "misperiodtaskplan")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PeriodTaskPlan extends AbstractPublicFields {
    @Id
    @Column(length = 36 ,name="id",nullable = false)
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    private String id;//主键

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "billtypeid")
    private PlanType billtype;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "periodtypeid")
    private PeriodType periodtype;
    @Column(length = 60)
    private String billno;//计划编号
    @Column(length = 128)
    private String billname;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private java.sql.Date planstartdate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private java.sql.Date planenddate;
    @Column(columnDefinition = "char(1) default '0'")
    private Character billstate;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "periodid")
    private PeriodItem perioditem;
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "projectid")
    private Project project;
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "projectWbsid")
    private ProjectWbs projectWbs;
    @Column(length = 512)
    private String note;
    private String productionmode;
    private String orderkind;

}
