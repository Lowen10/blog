package com.lowen.blog.controller;

import com.github.pagehelper.PageInfo;
import com.lowen.blog.controller.form.PasswordForm;
import com.lowen.blog.controller.form.ProfileForm;
import com.lowen.blog.controller.form.SyncForm;
import com.lowen.blog.controller.result.JsonResult;
import com.lowen.blog.model.BlAdmin;
import com.lowen.blog.model.BlArticle;
import com.lowen.blog.model.BlCategory;
import com.lowen.blog.model.BlProfile;
import com.lowen.blog.service.AdminService;
import com.lowen.blog.service.FrontService;
import com.lowen.blog.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@EnableAutoConfiguration
@RequestMapping("/admin")
public class AdminController {

    private static final int PAGE_SIZE = 20;

    @Autowired
    private AdminService mAdminService;
    @Autowired
    private FrontService mFrontService;

    @GetMapping("")
    public String index(@SessionAttribute(Constants.SESSION_KEY) BlAdmin blAdmin, Map<String, Object> model) {
        model.put("blAdmin", blAdmin);
        return "/admin/index";
    }

    /**
     * 登录也
     *
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    /**
     * 密码修改提交
     *
     * @param passwordForm 表单
     * @return
     */
    @PostMapping("/modifyPassword")
    @ResponseBody
    public JsonResult modifyPassword(@SessionAttribute(Constants.SESSION_KEY) BlAdmin blAdmin, @RequestBody PasswordForm passwordForm) {
        int count = mAdminService.updatePasswordByPrimaryKey(blAdmin.getId(), passwordForm.getNewPassword(), passwordForm.getOldPassword());
        JsonResult jsonResult = new JsonResult();
        if (count != 0) {
            jsonResult.setCode(200);
            jsonResult.setMessage("修改成功");
        } else {
            jsonResult.setCode(500);
            jsonResult.setMessage("修改失败");
        }
        return jsonResult;
    }

    @PostMapping("/syncToggle")
    @ResponseBody
    public JsonResult syncToggle(@RequestBody SyncForm syncForm) {
        JsonResult jsonResult = new JsonResult<>();
        jsonResult.setCode(200);
        jsonResult.setMessage("修改成功");
        return jsonResult;
    }

    @PostMapping("/syncTime")
    @ResponseBody
    public JsonResult syncTime(@RequestBody SyncForm syncForm) {
        JsonResult jsonResult = new JsonResult<>();
        jsonResult.setCode(200);
        jsonResult.setMessage("修改成功");
        return jsonResult;
    }

    @PostMapping("/syncStart")
    @ResponseBody
    public JsonResult syncStart() {
        JsonResult jsonResult = new JsonResult<>();
        jsonResult.setCode(200);
        jsonResult.setMessage("修改成功");
        return jsonResult;
    }

    /**
     * 欢迎页
     *
     * @param model
     * @return
     */
    @GetMapping("/welcome")
    public String welcome(@SessionAttribute(Constants.SESSION_KEY) BlAdmin blAdmin, Map<String, Object> model) {
        int articleCount = mFrontService.getArticleCount();
        int categoryCount = mFrontService.getCategoryCount();
        model.put("blAdmin", blAdmin);
        model.put("articleCount", articleCount);
        model.put("categoryCount", categoryCount);
        return "/admin/welcome";
    }

    /**
     * 密码修改页
     *
     * @return
     */
    @GetMapping("/password")
    public String memberPassword() {
        return "/admin/password";
    }

    /**
     * 简历页编辑
     *
     * @param model
     * @return
     */
    @GetMapping("/resume")
    public String resume(Map<String, Object> model) {
        return "/admin/resume";
    }

    /**
     * 个人信息
     *
     * @param model
     * @return
     */
    @GetMapping("/profile")
    public String profile(Map<String, Object> model) {
        BlProfile blProfile = mAdminService.selectProfile();
        model.put("blProfile", blProfile);
        return "/admin/profile";
    }

    /**
     * 个人信息修改
     *
     * @param profileForm
     * @return
     */
    @PostMapping("/profileModify")
    @ResponseBody
    public JsonResult profileModify(@RequestBody ProfileForm profileForm) {
        BlProfile profile = new BlProfile();
        profile.setIntroduce(profileForm.getIntroduce());
        profile.setName(profileForm.getName());
        profile.setNickname(profileForm.getNickname());
        profile.setQq(profileForm.getQq());
        profile.setSignature(profileForm.getSignature());
        profile.setWechat(profileForm.getWechat());
        profile.setEmail(profileForm.getEmail());
        profile.setHead(profileForm.getHead());
        profile.setCsdn(profileForm.getCsdn());
        profile.setGithub(profileForm.getGithub());
        int result = mAdminService.insertOrUpdateProfile(profile);
        JsonResult jsonResult = new JsonResult();
        if (result == 0) {
            jsonResult.setCode(400);
            jsonResult.setMessage("修改失败");
        } else {
            jsonResult.setCode(200);
            jsonResult.setMessage("修改成功");
        }
        return jsonResult;
    }

    /**
     * 文章列表
     *
     * @param model
     * @param categoryId 文章分类
     * @param page       页码
     * @return
     */
    @GetMapping("/article-list")
    public String articleList(Map<String, Object> model, @RequestParam(defaultValue = "0", required = false) Integer categoryId,
                              @RequestParam(defaultValue = "1", required = false) Integer page) {
        PageInfo<BlArticle> blArticles = mFrontService.getArticleList(page, PAGE_SIZE, categoryId);
        List<BlCategory> blCategories = mFrontService.getCategoryList();
        model.put("blCategories", blCategories);
        model.put("articlePage", blArticles);
        model.put("categoryId", categoryId);
        return "/admin/article-list";
    }

    /**
     * 分类列表页
     *
     * @param model
     * @return
     */
    @GetMapping("/category-list")
    public String categoryList(Map<String, Object> model) {
        List<BlCategory> blCategories = mFrontService.getCategoryList();
        model.put("blCategories", blCategories);
        return "/admin/category-list";
    }

    /**
     * 文章同步页
     *
     * @return
     */
    @GetMapping("/article-sync")
    public String articleSync() {
        return "/admin/article-sync";
    }
}