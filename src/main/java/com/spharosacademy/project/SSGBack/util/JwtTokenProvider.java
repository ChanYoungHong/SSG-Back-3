package com.spharosacademy.project.SSGBack.util;

import com.spharosacademy.project.SSGBack.user.repo.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Base64;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtTokenProvider implements AuthenticationProvider {

    private String secretKey = "charlie12345";

    // 유효시간 1시간
    private long tokenValidTime = 1L;

    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;

    // secretKey를 Base64로 인코딩하는 것.
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // id -> User의 pk 의미
    public String createToken(String userId, String role) {

        // JWT payload에 저장되는 정보단위, 여기서 user를 식별하는 값을 넣는다.
        Claims claims = Jwts.claims().setSubject(String.valueOf(userRepository.findByUserId(userId).get().getId()));
        claims.put("role", role);
        Date now = new Date();

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + tokenValidTime))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUserId(token));
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), "",
            userDetails.getAuthorities());
    }

    // String id 안에 hcy9883 유저 아이디 들어있음.
    public Authentication getUser(String id) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(id);
        System.out.println(userDetailsService.loadUserByUsername(id));
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), "",
            userDetails.getAuthorities());
    }

    public String getUserId(String token) {

        return userRepository.findById(Long.valueOf(getUserPk(token))).get().getUserId();
    }

    // 토큰에서 회원 정보 추출
    // getSubject 사용함으로 -> 1이 나오게 됨.
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // Request의 Header에서 token 값을 가져온다 "Authorization"
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    public String customResolveToken() {
        HttpServletRequest request = ((ServletRequestAttributes)
            RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader("Authorization");
    }

    public boolean validateToken(String jwtToken) {

        // 토큰이 만료됐는지 여부를 확인해주는 부분이다.
        // 현재 시각보다 만료가 먼저 됐을 경우에 예외를 발생시킨다.
        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
        return !claims.getBody().getExpiration().before(new Date());
    }

    @Override
    public Authentication authenticate(Authentication authentication)
        throws AuthenticationException {
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
