package com.spharosacademy.project.SSGBack.security.handler;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.spharosacademy.project.SSGBack.security.domain.Code;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private void setResponse(HttpServletResponse response, Code exceptionCode) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        JSONObject responseJson = new JSONObject();
        responseJson.put("message", exceptionCode.getMessage());
        responseJson.put("code", exceptionCode.getCode());

        response.getWriter().print(responseJson);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       org.springframework.security.access.AccessDeniedException accessDeniedException)
        throws IOException, ServletException {

        Code code;
        code = Code.PERMISSION_DENIED;
        setResponse(response, code);
    }
}
