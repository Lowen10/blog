package com.lowen.blog.service.spider;

import com.lowen.blog.mapper.BlArticleMapper;
import com.lowen.blog.mapper.BlCategoryArticleMapper;
import com.lowen.blog.mapper.BlCategoryMapper;
import com.lowen.blog.model.BlArticle;
import com.lowen.blog.model.BlCategory;
import com.lowen.blog.model.BlCategoryArticle;
import com.lowen.blog.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.ArrayList;
import java.util.Map;

@Service
public class CSDNPipeline implements Pipeline {

    @Autowired
    private BlCategoryMapper mBlCategoryMapper;
    @Autowired
    private BlArticleMapper mBlArticleMapper;
    @Autowired
    private BlCategoryArticleMapper mBlCategoryArticleMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {
        Map<String, Object> map = resultItems.getAll();
        ArrayList<BlCategory> blCategories = (ArrayList<BlCategory>) map.get(Constants.KEY_CATEGORY);
        if (blCategories != null && blCategories.size() != 0) {
            for (BlCategory category : blCategories) {
                mBlCategoryMapper.insert(category);
            }
        }

        BlArticle blArticle = (BlArticle) map.get(Constants.KEY_ARTICLE);
        if (blArticle != null) {
            mBlArticleMapper.insert(blArticle);
        }

        BlCategoryArticle blCategoryArticle = (BlCategoryArticle) map.get(Constants.KEY_CATEGORY_ARTICLE);
        if (blCategoryArticle != null) {
            mBlCategoryArticleMapper.insert(blCategoryArticle);
        }
        ArrayList<BlCategoryArticle> blCategoryArticles = (ArrayList<BlCategoryArticle>) map.get(Constants.KEY_CATEGORY_ARTICLE_LIST);
        if (blCategoryArticles != null) {
            for (BlCategoryArticle article : blCategoryArticles) {
                mBlCategoryArticleMapper.insert(article);
            }
        }
    }
}
