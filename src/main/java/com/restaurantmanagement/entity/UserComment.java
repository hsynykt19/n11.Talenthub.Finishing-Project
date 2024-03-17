package com.restaurantmanagement.entity;


import javax.persistence.*;
import lombok.*;


import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_COMMENT")
public class UserComment implements Serializable {

    @Id
    @GeneratedValue(generator = "SEQ_COMMENT", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SEQ_COMMENT", sequenceName = "SEQ_COMMENT", allocationSize = 1)

    @Column(name = "ID")
    private Long id;

    @Column(name = "TEXT",nullable = false)
    private String text;

    @Column(name = "SCORE",nullable = false)
    private int score;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
}
