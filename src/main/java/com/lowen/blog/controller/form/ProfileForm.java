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
public class ProfileForm {
    private String name;
    @NotEmpty(message = "昵称不能为空")
    private String nickname;
    private String wechat;
    private String qq;
    @NotEmpty(message = "邮箱不能为空")
    private String email;
    @NotEmpty(message = "签名不能为空")
    private String signature;
    @NotEmpty(message = "简介码不能为空")
    private String introduce;
    private String github;
    private String csdn;
    private String head;
}
