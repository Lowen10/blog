package com.lowen.blog.config;

import com.lowen.blog.model.BlAdmin;
import com.lowen.blog.utils.Constants;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class SessionInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        BlAdmin blAdmin = (BlAdmin) session.getAttribute(Constants.SESSION_KEY);
        if (blAdmin == null) {
            response.sendRedirect("/auth");
            return false;
        } else {
            return true;
        }
    }

}
