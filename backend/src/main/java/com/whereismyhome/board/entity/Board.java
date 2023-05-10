package com.whereismyhome.board.entity;

import com.whereismyhome.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column
    private String content;

    @CreatedDate
    @Column
    private LocalDateTime createtime = LocalDateTime.now();

    @LastModifiedDate
    @Column
    private LocalDateTime modifiedtime;

    @Column
    private String responseContent;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
