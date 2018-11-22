package com.lowen.blog.controller;

import com.github.pagehelper.PageInfo;
import com.lowen.blog.model.BlArticle;
import com.lowen.blog.model.BlCategory;
import com.lowen.blog.model.BlProfile;
import com.lowen.blog.service.FrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class FrontController {

    private static final int PAGE_SIZE = 10;

    @Autowired
    private FrontService mFrontService;

    @GetMapping("/")
    public String main(Map<String, Object> model, @RequestParam(defaultValue = "1") String page) {
        int pageIndex = getPage(page);
        initBaseData(model);
        initArticleList(model, pageIndex, null);
        return "front/index";
    }

    @GetMapping("/article/list/{categoryId}")
    public String articleList(Map<String, Object> model, @PathVariable Integer categoryId) {
        return articleList(model, categoryId, 1);
    }

    @GetMapping("/article/list/{categoryId}/{page}")
    public String articleList(Map<String, Object> model, @PathVariable Integer categoryId, @PathVariable Integer page) {
        initBaseData(model);
        if (isValidCategory(categoryId)) {
            initArticleList(model, page, categoryId);
        } else {
            initArticleList(model, page, null);
        }
        return "front/index";
    }

    @GetMapping("/article/details/{id}")
    public String articleDetails(Map<String, Object> model, @PathVariable Integer id) {
        initBaseData(model);
        BlArticle blArticle = mFrontService.getArticleById(id);
        if (blArticle != null) {
            List<BlCategory> categories = mFrontService.getCategoriesByArticleId(blArticle.getArticleId());
            model.put("article", blArticle);
            model.put("articleCategories", categories);
        }
        return "front/article";
    }

    private int getPage(String page) {
        try {
            if (page != null) {
                return Integer.parseInt(page);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 1;
    }

    private boolean isValidCategory(Integer categoryId) {
        return mFrontService.getBlCategory(categoryId) != null;
    }

    private void initBaseData(Map<String, Object> model) {
        BlProfile blProfile = mFrontService.getBlProfile();
        List<BlCategory> blCategories = mFrontService.getCategoryList();
        model.put("blProfile", blProfile);
        model.put("blCategories", blCategories);
    }

    private void initArticleList(Map<String, Object> model, Integer page, Integer categoryId) {
        PageInfo<BlArticle> blArticles = mFrontService.getArticleList(page, PAGE_SIZE, categoryId);
        model.put("blArticles", blArticles);
        model.put("categoryId", categoryId);
    }
}
