package com.lowen.blog.controller.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordForm {

    @NotEmpty(message = "旧密码不能为空")
    private String oldPassword;

    @NotEmpty(message = "新密码不能为空")
    private String newPassword;
}
