package com.example.demo.config;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.net.URI;
import java.util.Map;

public class AuthHandshakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        URI uri = request.getURI();
        String query = uri.getQuery(); // ví dụ: username=hoang

        if (query == null) {
            return false;
        }

        String username = null;
        for (String param : query.split("&")) {
            if (param.startsWith("username=")) {
                username = param.substring("username=".length());
                break;
            }
        }

        if (username != null && !username.isEmpty()) {
            attributes.put("username", username);
            return true;
        }

        return false;
    }


    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) {
        // Không cần làm gì ở đây
    }

    // Giả lập hàm kiểm tra token (bạn thay bằng logic thật)
    private boolean validateToken(String token) {
        // TODO: Xác thực token JWT hoặc token theo logic của bạn
        return token.equals("abc.def"); // ví dụ tạm token hợp lệ
    }

    // Giả lập lấy username từ token
    private String extractUsername(String token) {
        // TODO: Lấy username từ token (giải mã JWT...)
        return "hoang";
    }
}
