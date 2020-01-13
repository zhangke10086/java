/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.mis.place.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
@Table(name = "misplace")
public class place  {
  @Id
  @Column(length = 36 ,name="id")
  private String id;
  @Column(length = 60)
  private String code;
  @Column(length = 128)
  private String name;
  @ManyToOne
  @JoinColumn(name = "factoryid")
  private Factory factoryid;
  private Character state;
  @Column(length = 512)
  private String note;
  @Column(length = 36)
  private String sortpath;
  private Integer layer;
  private Character isdetail;



}
