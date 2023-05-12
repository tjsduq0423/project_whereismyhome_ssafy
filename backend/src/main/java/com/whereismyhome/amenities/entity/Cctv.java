package com.whereismyhome.amenities.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cctv")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cctv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String usage;

    @Column
    private String lat;

    @Column
    private String lng;
}
