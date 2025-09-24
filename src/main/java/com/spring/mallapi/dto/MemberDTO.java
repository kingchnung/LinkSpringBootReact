package com.spring.mallapi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class MemberDTO extends User {
    private static final long serialVersionUID = 1L;

    private String email;
    private String pw;
    private String nickname;
    private boolean social;

    private List<String> roleNames = new ArrayList<>();


    public MemberDTO(String email, String pw, String nickname, boolean social, List<String> roleNames) {
        // SimpleGrantedAuthority: Spring Security의 권한 클래스 - ROLE_ 접두어를 붙여 권한으로 등록 (ROLE_USER)
        super(email, pw, roleNames.stream().map(str ->
                new SimpleGrantedAuthority("ROLE_" + str))
                .collect(Collectors.toList()));
        this.email = email;
        this.pw = pw;
        this.nickname = nickname;
        this.social = social;
        this.roleNames = roleNames;
    }

    public Map<String, Object> getClaims() {
        Map<String, Object> dataMap = new HashMap<>();

        dataMap.put("email", email);
        dataMap.put("pw", pw);
        dataMap.put("nickname", nickname);
        dataMap.put("social", social);
        dataMap.put("roleNames", roleNames);

        return dataMap;
    }
}
