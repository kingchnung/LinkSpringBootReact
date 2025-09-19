package com.spring.mallapi.member.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString(exclude = "memberRoleList")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_member")
public class Member {
    @Id
    private String email;
    private String pw;
    private String nickname;
    private boolean social;

//  엔티티 클래스의 컬렌션을 매핑할 때 사용
    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private List<MemberRole> memberRoleList = new ArrayList<>();

    public void addRole(MemberRole memberRole) {
        memberRoleList.add(memberRole);
    }

    public void clearRole() {
        memberRoleList.clear();
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    public void changePw(String pw) {
        this.pw = pw;
    }

    public void changeSocial(boolean social) {
        this.social = social;
    }
}
