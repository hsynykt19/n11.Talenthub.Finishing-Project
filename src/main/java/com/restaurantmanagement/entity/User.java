package com.restaurantmanagement.entity;


import javax.persistence.*;

import lombok.*;

@ToString
@Generated
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(generator = "SEQ_USER", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SEQ_USER", sequenceName = "SEQ_USER", allocationSize = 1)

    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", length = 1000, nullable = false)
    private String name;

    @Column(name = "SUR_NAME", length = 1000, nullable = false)
    private String surName;

    @Column(name = "LATITUDE")
    private double latitude;

    @Column(name = "LONGITUDE")
    private double longitude;


}
