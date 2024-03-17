package com.restaurantmanagement.entity;

import javax.persistence.*;

import lombok.*;

import java.io.Serializable;

@ToString
@Generated
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "RESTAURANT")
public class Restaurant implements Serializable {

    @Id
    @GeneratedValue(generator = "SEQ_RESTAURANT", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SEQ_RESTAURANT", sequenceName = "SEQ_RESTAURANT", allocationSize = 1)

    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", length = 1000, nullable = false)
    private String name;

    @Column(name = "LATITUDE")
    private double latitude;

    @Column(name = "LONGITUDE")
    private double longitude;

    @Column(name = "SCORE")
    private double score;

}
