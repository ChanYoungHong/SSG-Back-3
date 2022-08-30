package com.spharosacademy.project.SSGBack.security.filter;

import com.spharosacademy.project.SSGBack.user.dto.request.UserInputDto;
import com.spharosacademy.project.SSGBack.user.dto.request.UserLoginDto;
import com.spharosacademy.project.SSGBack.user.dto.response.UserOutputDto;
import com.spharosacademy.project.SSGBack.user.entity.UserRole;
import com.spharosacademy.project.SSGBack.user.repo.UserRepository;
import com.spharosacademy.project.SSGBack.util.JwtTokenProvider;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import static org.springframework.security.config.Elements.JWT;


//public class ApiCheckFilter extends OncePerRequestFilter {
//
//    private AntPathMatcher antPathMatcher;
//    private String pattern;
//    private JwtTokenProvider jwtTokenProvider;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public ApiCheckFilter(String pattern) {
//        this.antPathMatcher = new AntPathMatcher();
//        this.pattern = pattern;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//
//        if(antPathMatcher.match(pattern, request.getRequestURI())) {
//
//            boolean checkHeader = checkAuthHeader(request);
//
//            if(checkHeader) {
//                filterChain.doFilter(request, response);
//                return;
//            } else {
//                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//
//                response.setContentType("application/json;charset=utf-8");
//                JSONObject json = new JSONObject();
//                String message = "토큰이 존재하지 않습니다.";
//                json.put("code", "403");
//                json.put("message", message);
//
//                PrintWriter out = response.getWriter();
//                out.print(json);
//                return;
//            }
//        }
//        filterChain.doFilter(request,response);
//    }
//
//    private boolean checkAuthHeader(HttpServletRequest request) {
//
//        boolean checkResult = false;
//        UserInputDto userInputDto = new UserInputDto();
//        UserLoginDto loginDto = new UserLoginDto();
//        UserOutputDto outputDto = new UserOutputDto();
//
//        String authHeader = request.getHeader("Authorization");
//
//       if(authHeader == null || !authHeader.startsWith("Bearer")){
//           checkResult = true;
//       }
//
//        return checkResult;
//    }
//
//}
