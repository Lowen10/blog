package com.lowen.blog.utils;

public class Constants {

    public static final String KEY_ARTICLE = "BlArticle";
    public static final String KEY_CATEGORY = "BlCategory";
    public static final String KEY_CATEGORY_ARTICLE = "BlCategoryArticle";
    public static final String KEY_CATEGORY_ARTICLE_LIST = "BlCategoryArticleList";

    private static final String CSDN_ACCOUNT = "micoxi";
//    private static final String CSDN_ACCOUNT = "yhl_jxy";

    private static final String CSDN_PROTOCOL = "https";
    public static final String CSDN_HOSTNAME = "blog.csdn.net";
    private static final String CSDN_CATEGORY_ARTICLE_PATH = "article/category/";
    private static final String CSDN_ARTICLE_DETAIL_PATH = "article/details/";

    /**
     * CSDN主页地址
     */
    public static final String CSDN_HOME_URL = CSDN_PROTOCOL + "://" + CSDN_HOSTNAME + "/" + CSDN_ACCOUNT;

    /**
     * 分类列表
     */
    public static final String CSDN_CATEGORY_ARTICLE_URL = CSDN_HOME_URL + "/" + CSDN_CATEGORY_ARTICLE_PATH;

    /**
     * 文章详情
     */
    public static final String CSDN_ARTICLE_DETAILS_URL = CSDN_HOME_URL + "/" + CSDN_ARTICLE_DETAIL_PATH;

    public static final String AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31";

    public static final int SLEEP_TIME = 1000;

    public static final String SESSION_KEY = "user";

}
