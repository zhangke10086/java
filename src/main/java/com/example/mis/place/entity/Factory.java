/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.mis.place.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "misfactory")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Factory  {
//FROM MOFMFACTORY A LEFT JOIN MOFMOrgType B
//ON A.FACTORYTYPEID=B.ID
  @Id
  @Column(length = 36 ,name="id")

  private String id;
  @Column(length = 60)
  private String code;
  @Column(length = 128)
  private String name;
  private Character state;
  @Column(length = 512)
  private String note;
  @Column(length = 36)
  private String sortpath;
  private Integer layer;
  private Character isdetail;
  private String type;
//  @ManyToOne(cascade = CascadeType.DETACH)
//  @JoinColumn(name = "companyid")
//  private Companyinfo_vw companyinfo;


}
