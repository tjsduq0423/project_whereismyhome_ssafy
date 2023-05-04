package com.whereismyhome.bookmark.entity;

import com.whereismyhome.houseinfo.entity.HouseInfo;
import com.whereismyhome.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "bookmark")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookMark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "houseinfo_aptcode")
    private HouseInfo houseInfo;

}
