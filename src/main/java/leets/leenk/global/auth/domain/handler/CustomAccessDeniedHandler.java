package leets.leenk.global.auth.domain.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import leets.leenk.global.auth.application.exception.ErrorCode;
import leets.leenk.global.auth.application.util.HandlerResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final HandlerResponseUtil handlerResponseUtil;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        handlerResponseUtil.setResponse(response, HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage(), ErrorCode.ACCESS_DENIED);
    }
}
