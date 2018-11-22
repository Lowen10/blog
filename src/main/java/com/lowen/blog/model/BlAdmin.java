package com.lowen.blog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlAdmin {
    private Integer id;

    private String account;

    private String password;

    private Integer role;
}