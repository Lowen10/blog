package com.lowen.blog.controller;


import com.lowen.blog.controller.form.UserLoginForm;
import com.lowen.blog.model.BlAdmin;
import com.lowen.blog.service.AdminService;
import com.lowen.blog.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AdminService mAdminService;

    @GetMapping("")
    public String login() {
        return "admin/login";
    }

    @PostMapping(value = "/login")
    public String loginAuth(@Valid UserLoginForm userLoginForm, BindingResult bindingResult, Map<String, Object> model, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {

        }
        BlAdmin blAdmin = mAdminService.login(userLoginForm.getUsername(), userLoginForm.getPassword());
        if (blAdmin == null) {
            return "redirect:/auth";
        } else {
            HttpSession session = request.getSession();
            session.setAttribute(Constants.SESSION_KEY, blAdmin);
            return "redirect:/admin";
        }
    }

    @GetMapping(value = "/unlogin")
    public String loginAuth(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(Constants.SESSION_KEY);
        return "redirect:/auth";
    }
}
