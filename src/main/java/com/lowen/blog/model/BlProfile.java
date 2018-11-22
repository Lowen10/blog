package com.lowen.blog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlProfile {
    private Integer id;

    private String name;

    private String nickname;

    private String introduce;

    private String qq;

    private String wechat;

    private String signature;

    private String email;

    private String head;

    private String csdn;

    private String github;

    private String resume;
    /**
     * 数据库临时字段
     */
    private int count;
}