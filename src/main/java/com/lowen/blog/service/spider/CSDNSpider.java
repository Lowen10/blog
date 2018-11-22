package com.lowen.blog.service.spider;

import com.lowen.blog.model.BlArticle;
import com.lowen.blog.model.BlCategory;
import com.lowen.blog.model.BlCategoryArticle;
import com.lowen.blog.utils.Constants;
import com.lowen.blog.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.*;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

@Service
public class CSDNSpider implements PageProcessor {

    private static final String KEY_CATEGORY_ID = "category_id";
    private static final String KEY_ARTICLE = "article";
    private static final String KEY_PAGE = "page";
    private static final String KEY_RETRY = "retry";

    private Site site = Site
            .me()
            .setDomain(Constants.CSDN_HOSTNAME)
            .setSleepTime(Constants.SLEEP_TIME)
            .setUserAgent(Constants.AGENT);

    @Autowired
    private CSDNPipeline mCSDNPipeline;

    @Override
    public void process(Page page) {
        if (page.getStatusCode() != 200) {
            System.out.println("加载失败:" + page.getUrl());
        }
        Html html = page.getHtml();
        String url = page.getUrl().get();
        Request request = page.getRequest();
        if (Constants.CSDN_HOME_URL.endsWith(url)) {
            //分类信息
            ArrayList<BlCategory> categories = new ArrayList<>();
            List<Selectable> selectables = html.xpath("//div[@id=\"asideCategory\"]//li").nodes();
            for (Selectable s : selectables) {
                String categoryUrl = s.xpath("//a/@href").get();
                String title = s.xpath("//span[@class=\"title\"]/text()").get();
                String countText = s.xpath("//span[@class=\"count\"]/text()").get();
                int count = Utils.filterInteger(countText);
                int categoryId = Utils.getLastIntegerParam(categoryUrl);
                BlCategory category = new BlCategory();
                category.setArticleCount(count);
                category.setCategory(title);
                category.setCategoryId(categoryId);
                categories.add(category);

                //获取分类文章列表
                Request categoryArticleRequest = getCategoryArticleRequest(categoryId, 1);
                page.addTargetRequest(categoryArticleRequest);
            }
            page.putField(Constants.KEY_CATEGORY, categories);
        } else if (url.startsWith(Constants.CSDN_CATEGORY_ARTICLE_URL)) {
            //文章分类列表
            int categoryId = (Integer) request.getExtra(KEY_CATEGORY_ID);
            int pageIndex = (Integer) request.getExtra(KEY_PAGE);

            boolean hasNextPage = false;
            Selectable htmlSelectable = html.xpath("//*[@class=\"article-item-box\" and @style!=\"display: none;\"]");
            List<Selectable> selectables = htmlSelectable.nodes();
            ArrayList<BlCategoryArticle> blCategoryArticles = new ArrayList<>();
            for (Selectable s : selectables) {
                hasNextPage = true;
                String articleType = s.xpath("//span[@class=\"article-type\"]/text()").get();
                String articleTitle = s.xpath("//h4/a/text()").get();
                String articleInfo = s.xpath("//[@class=\"content\"]/a/text()").get();
                String createDate = s.xpath("//span[@class=\"date\"]/text()").get();
                String readNum = s.xpath("//span[@class=\"read-num\"]/text()").get();
                String articleUrl = s.xpath("//[@class=\"content\"]/a/@href").get();
                Integer articleId = Utils.getLastIntegerParam(articleUrl);
                boolean isTop = false;
                if (s.xpath("//svg").nodes().size() != 0) {
                    isTop = true;
                }
                BlArticle blArticle = new BlArticle();
                blArticle.setIsTop(isTop ? 1 : 0);
                blArticle.setArticleType(articleType);
                blArticle.setTitle(articleTitle);
                blArticle.setIntroduction(articleInfo);
                blArticle.setArticleId(articleId);
                blArticle.setCreateTime(Utils.parseDate(createDate));
                blArticle.setReadNum(Utils.filterInteger(readNum));
                blArticle.setArticleUrl(articleUrl);

                BlCategoryArticle blCategoryArticle = new BlCategoryArticle();
                blCategoryArticle.setArticleId(articleId);
                blCategoryArticle.setCategoryId(categoryId);
                blCategoryArticles.add(blCategoryArticle);

                //获取文章详情
                Request articleRequest = new Request(articleUrl);
                articleRequest.putExtra(KEY_ARTICLE, blArticle);
                page.addTargetRequest(articleRequest);

            }
            if (blCategoryArticles.size() > 0) {
                //保存分类和内容
                page.putField(Constants.KEY_CATEGORY_ARTICLE_LIST, blCategoryArticles);
            }

            /**
             * 这里为了方便，如果当前请求成功，默认会继续请求下一页数据，如果请求失败，
             * 则停止，请求失败判断标准为解析文章列表失败
             */
            if (hasNextPage) {
                Request nextCategoryArticleRequest = getCategoryArticleRequest(categoryId, pageIndex + 1);
                page.addTargetRequest(nextCategoryArticleRequest);
            }
        } else if (url.startsWith(Constants.CSDN_ARTICLE_DETAILS_URL)) {
            String content = html.xpath("//div[@class=\"article_content\"]").get();
            if (content == null) {
                return;
            }

            BlArticle blArticle = (BlArticle) request.getExtra(KEY_ARTICLE);
            //文章详情
            //添加内容
            blArticle.setContent(content);

            page.putField(Constants.KEY_ARTICLE, blArticle);
        }
    }

    /**
     * 获取分类文章列表URL
     *
     * @param categoryId 分类Id
     * @param pageIndex  页码
     * @return
     */
    private Request getCategoryArticleRequest(int categoryId, int pageIndex) {
        String url = Constants.CSDN_CATEGORY_ARTICLE_URL + categoryId + "/" + pageIndex;
        Request request = new Request(url);
        request.putExtra(KEY_CATEGORY_ID, categoryId);
        request.putExtra(KEY_PAGE, pageIndex);
        return request;
    }

    @Override
    public Site getSite() {
        return site;
    }

    private SpiderListener mSpiderListener = new SpiderListener() {
        @Override
        public void onSuccess(Request request) {

        }

        @Override
        public void onError(Request request) {

        }
    };

    /**
     * 开始CSDN同步
     */
    public void startCSDNSync() {
        List<SpiderListener> spiderListeners = new ArrayList<>();
        spiderListeners.add(mSpiderListener);
        Spider.create(new CSDNSpider())
                .setExecutorService(Executors.newSingleThreadExecutor())
                .setSpiderListeners(spiderListeners)
                .thread(Constants.SLEEP_TIME)
                .addPipeline(mCSDNPipeline)
                .addUrl(Constants.CSDN_HOME_URL).run();
    }
}
