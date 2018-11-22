package com.lowen.blog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlCategoryArticle {
    private Integer id;

    private Integer categoryId;

    private Integer articleId;
}