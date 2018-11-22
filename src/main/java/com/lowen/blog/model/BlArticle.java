package com.lowen.blog.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BlArticle {

    private Integer id;

    private String title;

    private Integer readNum;

    private Integer status;

    private Date updateTime;

    private Date createTime;

    private String introduction;

    private Integer isTop;

    private String articleUrl;

    private String articleType;

    private Integer articleId;

    private String content;
}