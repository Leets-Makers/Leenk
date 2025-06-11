package leets.leenk.global.auth.application.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import leets.leenk.global.common.exception.ErrorCodeInterface;
import leets.leenk.global.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class HandlerResponseUtil {

    private final ObjectMapper objectMapper;

    public void setResponse(HttpServletResponse response, int status, String errorMessage, ErrorCodeInterface errorCode) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String message = objectMapper.writeValueAsString(CommonResponse.error(errorCode, errorMessage));
        response.getWriter().write(message);
    }
}
