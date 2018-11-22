package com.lowen.blog.mapper;

import com.lowen.blog.model.BlCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlCategory record);

    int selectCount();

    BlCategory selectByPrimaryKey(Integer id);

    BlCategory selectByCategoryId(@Param("categoryId") Integer categoryId);

    List<BlCategory> selectAll();

    List<BlCategory> selectCategoriesByArticleId(@Param("articleId") Integer articleId);

    int updateByPrimaryKey(BlCategory record);
}