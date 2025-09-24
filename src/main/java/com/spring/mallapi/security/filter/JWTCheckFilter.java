package com.spring.mallapi.security.filter;

import com.google.gson.Gson;
import com.spring.mallapi.dto.MemberDTO;
import com.spring.mallapi.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * JWTCheckFilter는 Spring Security의 OncePerRequestFilter를 상속받아
 * 요청 당 한 번만 실행되는 필터이다.
 * JWT 토큰이 포함된 요청을 가로채어 유효성을 검사하고,
 * 인증 정보를 SecurityContext에 저장하여 이후 요청 처리에서 인증된 사용자로 인식되게 한다.
 */

@Slf4j
public class JWTCheckFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("----------------------------------");
//        filterChain.doFilter(request, response);

        String authHeaderString = request.getHeader("Authorization");

        try {
            // Bearer accestoken...
            String accessToken = authHeaderString.substring(7);
            Map<String, Object> claims = JWTUtil.validateToken(accessToken);
            log.info("JWT claims: {}", claims);
//            filterChain.doFilter(request, response);
            log.info("-----------------------------------");

            String email = (String) claims.get("email");
            String pw = (String) claims.get("pw");
            String nickname = (String) claims.get("nickname");
            Boolean social = (Boolean) claims.get("social");

            @SuppressWarnings("unchecked")
            List<String> roleNames = (List<String>) claims.get("roleNames");
            MemberDTO memberDTO = new MemberDTO(email, pw, nickname, social.booleanValue(), roleNames);
            log.info("-------------------------------------");
            log.info("memberDTO: {}", memberDTO);
            log.info("memberDTO.getAuthorities() : {}", memberDTO.getAuthorities());
            log.info("-------------------------------------");

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberDTO, pw, memberDTO.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error("JWT Check Error..............");
            log.error(e.getMessage());

            Gson gson = new Gson();
            String msg = gson.toJson(Map.of("error", "ERROR_ACCESS_TOKEN"));
            response.setContentType("application/json");
            PrintWriter printWriter = response.getWriter();
            printWriter.println(msg);
            printWriter.close();
        }
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        if(request.getMethod().equals("OPTIONS")) {
            return true;
        }

        String path = request.getRequestURI();
        log.info("Check URI ................{}", path);

//        api/member/ 경로의 호출은 체크하지 않음
        if(path.startsWith("/api/member/")) {
            return true;
        }

        return false;
    }
}
