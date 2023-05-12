package com.whereismyhome.amenities.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.geolatte.geom.Point;

@Entity
@Table(name = "school")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String lat;

    @Column
    private String lng;

    @Column
    private Point localPoint;
}
