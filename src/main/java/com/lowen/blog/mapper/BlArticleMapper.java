package com.lowen.blog.mapper;

import com.lowen.blog.model.BlArticle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlArticle record);

    BlArticle selectByPrimaryKey(Integer id);

    List<BlArticle> selectAll(@Param("categoryId") Integer categoryId);

    int updateByPrimaryKey(BlArticle record);

    int selectCount();
}