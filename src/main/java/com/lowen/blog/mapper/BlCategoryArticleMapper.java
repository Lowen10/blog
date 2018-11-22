package com.lowen.blog.mapper;

import com.lowen.blog.model.BlCategoryArticle;

import java.util.List;

public interface BlCategoryArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlCategoryArticle record);

    BlCategoryArticle selectByPrimaryKey(Integer id);

    List<BlCategoryArticle> selectAll();

    int updateByPrimaryKey(BlCategoryArticle record);
}