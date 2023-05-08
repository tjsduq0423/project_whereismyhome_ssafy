package com.whereismyhome.member.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.whereismyhome.board.entity.Board;
import com.whereismyhome.role.entity.MemberRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member implements UserDetails {
    @Id
    private String id;

    @Column(nullable = false, length = 250)
    private String password;

    @Column(nullable = false, length = 45)
    private String name;

    @Column(nullable = false, length = 45)
    private String email;

    @OneToMany(mappedBy = "member")
    private List<Board> boardList;

    @JsonBackReference
    @OneToOne(mappedBy = "member")
    private MemberRole roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.roles.getRole()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
