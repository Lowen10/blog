package com.lowen.blog.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lowen.blog.mapper.BlArticleMapper;
import com.lowen.blog.mapper.BlCategoryMapper;
import com.lowen.blog.mapper.BlProfileMapper;
import com.lowen.blog.model.BlArticle;
import com.lowen.blog.model.BlCategory;
import com.lowen.blog.model.BlProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FrontService {

    @Autowired
    private BlArticleMapper mBlArticleMapper;
    @Autowired
    private BlCategoryMapper mBlCategoryMapper;
    @Autowired
    private BlProfileMapper mBlProfileMapper;

    public BlProfile getBlProfile() {
        return mBlProfileMapper.selectProfile();
    }

    public List<BlCategory> getCategoryList() {
        return mBlCategoryMapper.selectAll();
    }

    public BlArticle getArticleById(Integer id) {
        return mBlArticleMapper.selectByPrimaryKey(id);
    }

    public BlCategory getBlCategory(Integer categoryId) {
        return mBlCategoryMapper.selectByCategoryId(categoryId);
    }

    public List<BlCategory> getCategoriesByArticleId(Integer articleId) {
        return mBlCategoryMapper.selectCategoriesByArticleId(articleId);
    }

    public PageInfo<BlArticle> getArticleList(Integer page, Integer size, Integer categoryId) {
        PageHelper.startPage(page, size);
        List<BlArticle> blArticles = mBlArticleMapper.selectAll(categoryId);
        return new PageInfo<>(blArticles);
    }

    public int getCategoryCount() {
        return mBlCategoryMapper.selectCount();
    }

    public int getArticleCount() {
        return mBlArticleMapper.selectCount();
    }
}
